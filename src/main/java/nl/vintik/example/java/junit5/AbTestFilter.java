package nl.vintik.example.java.junit5;

import nl.vintik.example.java.junit5.parametarized.Feature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class AbTestFilter implements Filter {

    private static Logger LOG = Logger.getLogger(AbTestFilter.class.getName());

    static final String ABTEST_COOKIE_NAME = "abtest_cookie";
    static final String ABTEST_REQUEST_PARAM_NAME = "abtest";
    private static final String USER_AGENT_HEADER_KEY = "User-Agent";

    private static final int HOUR_IN_SECONDS = 3600;
    private static final String SEPARATOR = "#";

    private AbTestsContext abTestsContext;
    private DeviceService deviceService;
    private ToggleService toggleService;


    public void init(
            AbTestsContext abTestsContext,
            DeviceService deviceService,
            ToggleService baasAdapter
    ) {
        this.abTestsContext = abTestsContext;
        this.deviceService = deviceService;
        this.toggleService = baasAdapter;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        try {
            if (!isFilteredPage(httpServletRequest)
                    && !isUserAgentBot(httpServletRequest)
                    && abTestsContext.getActiveVariantNames().isEmpty()
                    && !toggleService.isEnabled(Feature.DISABLE_AB_TEST)) {
                String[] requestParameterValues = httpServletRequest.getParameterValues(ABTEST_REQUEST_PARAM_NAME);

                Map<AbTestCase, AbTestVariant> activeVariants = Collections.emptyMap();
                if (requestParameterValues != null && requestParameterValues.length > 0) {
                    activeVariants = abTestsContext.setActiveVariantsForUser(requestParameterValues);
                    createAbtCookie(httpServletRequest, httpServletResponse, activeVariants.values());
                } else {
                    final Optional<Cookie> abtCookie = getAbtCookie(httpServletRequest);
                    final Optional<String[]> variantsInCookie = abtCookie.map(this::getVariantIdsFromAbtCookie);
                    if (hasAbtCookie(httpServletRequest)) {
                        variantsInCookie.ifPresent(abTestsContext::setActiveVariantsForUser);

                    } else {
                        String[] variantsFromCookie = variantsInCookie.orElse(new String[]{});
                        Map<AbTestCase, AbTestVariant> determinedVariants = abTestsContext
                                .determineVariantsForUser(variantsFromCookie);
                        activeVariants = abTestsContext.setActiveVariantsForUser(determinedVariants);
                        createAbtCookie(httpServletRequest, httpServletResponse, activeVariants.values());
                    }
                }
                createAbtCookie(httpServletRequest, httpServletResponse, activeVariants.values());
            }
        } catch (Throwable e) {
            LOG.severe("Error in AB test filter");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private boolean isUserAgentBot(HttpServletRequest request) {
        if (request != null) {
            String userAgent = request.getHeader(USER_AGENT_HEADER_KEY);
            return deviceService.getDeviceTypeByUserAgent(userAgent).isSeoAgent();
        }
        return false;
    }

    private boolean isFilteredPage(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return servletPath != null && (servletPath.startsWith("/static") || servletPath.startsWith("/internal"));
    }

    private Optional<Cookie> getAbtCookie(HttpServletRequest request) {
        return Optional.ofNullable(WebUtils.getCookie(request, ABTEST_COOKIE_NAME));
    }

    private boolean hasAbtCookie(HttpServletRequest request) {
        return WebUtils.getCookie(request, ABTEST_COOKIE_NAME) != null;
    }

    private String[] getVariantIdsFromAbtCookie(Cookie cookie) {
        return cookie.getValue().split(SEPARATOR);
    }

    private static void createAbtCookie(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Collection<AbTestVariant> activeVariants
    ) {


        final String value = activeVariants.stream()
                .map(AbTestVariant::getId)
                .filter(id -> !AbTestCase.DEFAULT_VARIANT.equals(id))
                .collect(Collectors.joining(SEPARATOR));

        Cookie newAbtCookie = new Cookie(ABTEST_COOKIE_NAME,
                StringUtils.defaultIfBlank(value, "true"));

        newAbtCookie.setPath("/");
        newAbtCookie.setMaxAge(HOUR_IN_SECONDS);
        newAbtCookie.setSecure(httpServletRequest.isSecure());
        httpServletResponse.addCookie(newAbtCookie);
    }

}

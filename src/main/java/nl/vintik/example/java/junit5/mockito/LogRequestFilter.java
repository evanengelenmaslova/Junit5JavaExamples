package nl.vintik.example.java.junit5.mockito;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.net.HttpHeaders;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class LogRequestFilter implements Filter {

    static final String REQUEST_URL = "REQUEST_URL";
    static final String REQUEST_REFERER_URL = "REQUEST_REFERER_URL";
    static final String JSESSIONID_COOKIE = "JSESSIONID";

    static final String JSESSION_ID = "jsession_id";

    static final String SESSION_ID = "sessionId";

    private SessionIdContext sessionIdContext;

    static HashMap<String, String> MDC = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        sessionIdContext = applicationContext.getBean(SessionIdContext.class);
    }

    public void init(SessionIdContext sessionIdContext) {
        this.sessionIdContext = sessionIdContext;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;

                final Map<String, Optional<String>> context = ImmutableMap.<String, Optional<String>>builder()
                        .put(REQUEST_URL, getFullRequestUrl(httpRequest))
                        .put(REQUEST_REFERER_URL, getReferer(httpRequest))
                        .put(JSESSION_ID, getJSessionId(httpRequest))
                        .put(SESSION_ID, sessionIdContext.getSessionId())
                        .build();

                setMdc(context);
            }
        } catch (Throwable ex) {
            //ignore
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            unsetMdc();
        }

    }

    @Override
    public void destroy() {

    }

    private static void setMdc(final Map<String, Optional<String>> context) {
        context.entrySet().stream()
                .filter(it -> it.getValue().isPresent())
                .forEach(e -> MDC.put(e.getKey(), e.getValue().get()));
    }

    private static void unsetMdc() {
        ImmutableList.of(REQUEST_URL, REQUEST_REFERER_URL, JSESSION_ID, SESSION_ID)
                .forEach(MDC::remove);
    }

    private static Optional<String> getFullRequestUrl(final HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();

        if (request.getQueryString() != null) {
            requestUrl = requestUrl + "?" + request.getQueryString();

        }
        return Optional.of(requestUrl);
    }

    private static Optional<String> getReferer(final HttpServletRequest request) {
        return Optional.ofNullable(StringUtils.trimToNull(request.getHeader(HttpHeaders.REFERER)));
    }

    private Optional<String> getJSessionId(final HttpServletRequest httpRequest) {
        return Optional.ofNullable(WebUtils.getCookie(httpRequest, JSESSIONID_COOKIE))
                .map(Cookie::getValue);
    }
}

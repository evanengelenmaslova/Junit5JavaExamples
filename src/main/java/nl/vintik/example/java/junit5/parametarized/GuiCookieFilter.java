package nl.vintik.example.java.junit5.parametarized;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class GuiCookieFilter implements Filter {
    public static final String GUI_NAME = "GUI";

    private static final int LENGTH = 32;
    private static final String RANGE = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int DURATION = 60 * 60 * 24 * 365;

    private CookieHelper cookieHelper;
    private BiFunction<Integer, String, String> randomizer;

    @Override
    public void init(FilterConfig filterConfig) {
        this.randomizer = RandomStringUtils::random;
        this.cookieHelper = new CookieHelper();
    }

    public void init(BiFunction<Integer, String, String> randomizer, CookieHelper cookieHelper) {
        this.randomizer = randomizer;
        this.cookieHelper = cookieHelper;
    }

    @Override
    public void doFilter(final ServletRequest reqParam, final ServletResponse respParam, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) reqParam;
        HttpServletResponse response = (HttpServletResponse) respParam;
        try {
            Cookie cookie = WebUtils.getCookie(request, GUI_NAME);

            if (!isValid(cookie)) {
                final String cookieValue = this.randomizer.apply(LENGTH, RANGE);

                cookie = createCookie(request, GUI_NAME, cookieValue, DURATION);
                request.setAttribute(GUI_NAME, cookieValue);
            } else {
                final String cookieValue = cookieHelper.updateCookie(cookie.getValue());

                cookie = createCookie(request, GUI_NAME, cookieValue, DURATION);
            }

            response.addCookie(cookie);
        } catch (Throwable ex) {
            //ignore
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Nonnull
    private Cookie createCookie(@Nonnull HttpServletRequest request, @Nonnull String name, @Nullable String value, @Nullable Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(request.getServerName());
        cookie.setPath("/");
        cookie.setSecure(request.isSecure());
        cookie.setMaxAge(maxAge);

        return cookie;
    }

    private boolean isValid(Cookie cookie) {
        return Optional.ofNullable(cookie)
                .map(Cookie::getValue)
                .map(v -> !StringUtils.isBlank(v) && v.length() >= 8)
                .orElse(false);
    }
}

package nl.vintik.example.java.junit5;

import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

import static nl.vintik.example.java.junit5.LogRequestFilter.MDC;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogRequestFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private SessionIdContext mockSessionIdContext;

    private MockFilterChain mockFilterChain;

    private LogRequestFilter filter;

    @BeforeEach
    public void setup() {
        filter = new LogRequestFilter();
        filter.init(mockSessionIdContext);

        mockFilterChain = new MockFilterChain(new HttpServlet() {
        }, filter);

        when(mockSessionIdContext.getSessionId()).thenReturn(Optional.empty());
        when(request.getRequestURL()).thenReturn(new StringBuffer());
        when(request.getCookies()).thenReturn(null);
    }


    @Test
    public void shouldAddRequestUrlWithQueryStringToMdc() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://request"));
        when(request.getQueryString()).thenReturn("a=b&c=d");

        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertEquals("http://request?a=b&c=d", MDC.get(LogRequestFilter.REQUEST_URL));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);
        assertNull(MDC.get(LogRequestFilter.REQUEST_URL));
    }

    @Test
    public void shouldAddRefererToMdcWhenRefererProvided() throws Exception {
        when(request.getHeader(eq(HttpHeaders.REFERER))).thenReturn("http://referer");

        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertEquals("http://referer", MDC.get(LogRequestFilter.REQUEST_REFERER_URL));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);
        assertNull(MDC.get(LogRequestFilter.REQUEST_REFERER_URL));
    }

    @Test
    public void shouldNotAddRefererToMdcIfRefererIsNotProvided() throws Exception {
        when(request.getHeader(eq(HttpHeaders.REFERER))).thenReturn(null);

        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertNull(MDC.get(LogRequestFilter.REQUEST_REFERER_URL));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);
        assertNull(MDC.get(LogRequestFilter.REQUEST_REFERER_URL));
    }

    @Test
    public void shouldAddJsessionIdToMdcOnNormalCase() throws Exception {
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("JSESSIONID", "sessionId")});
        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertEquals("sessionId", MDC.get(LogRequestFilter.JSESSION_ID));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);
        assertNull(MDC.get(LogRequestFilter.JSESSION_ID));
    }

    @Test
    public void shouldNotAddJsessionIdToMdcIfNotProvided() throws Exception {

        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertNull(MDC.get(LogRequestFilter.JSESSION_ID));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);
        assertNull(MDC.get(LogRequestFilter.JSESSION_ID));
    }

    @Test
    public void shouldAddSessionIdToMdcInNormalCase() throws Exception {
        final String sessionId = UUID.randomUUID().toString();
        when(mockSessionIdContext.getSessionId()).thenReturn(Optional.of(sessionId));


        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertEquals(sessionId, MDC.get(LogRequestFilter.SESSION_ID));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);
        assertNull(MDC.get(LogRequestFilter.SESSION_ID));
    }

    @Test
    public void shouldNotAddSessionIdToMdcIfRetrievalFailed() throws Exception {
        when(mockSessionIdContext.getSessionId()).thenReturn(Optional.empty());
        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertNull(MDC.get(LogRequestFilter.SESSION_ID));
                    }
                });

        mockFilterChain.doFilter(request, response);

        mockFilterChain.reset();
        filter.doFilter(request, response, mockFilterChain);

        assertNull(MDC.get(LogRequestFilter.SESSION_ID));
    }

    @Test
    public void shouldAddAndRemoveMdcWhenDoFilterThrowsException() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://request"));
        when(request.getHeader(eq(HttpHeaders.REFERER))).thenReturn("http://referer");
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("JSESSIONID", "sessionId")});

        final String sessionId = UUID.randomUUID().toString();
        when(mockSessionIdContext.getSessionId()).thenReturn(Optional.of(sessionId));

        mockFilterChain = new MockFilterChain(new HttpServlet() {
        },
                filter,
                new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                        assertEquals("http://request", MDC.get(LogRequestFilter.REQUEST_URL));
                        assertEquals("http://referer", MDC.get(LogRequestFilter.REQUEST_REFERER_URL));
                        assertEquals("sessionId", MDC.get(LogRequestFilter.JSESSION_ID));
                        assertEquals(sessionId, MDC.get(LogRequestFilter.SESSION_ID));

                        throw new IllegalStateException("some exception");
                    }
                });

        boolean thrown = false;
        try {
            mockFilterChain.doFilter(request, response);
        } catch (IllegalStateException e) {
            thrown = true;
        }

        assertTrue(thrown, "Exception not propagated by filter");

        assertNull(MDC.get(LogRequestFilter.REQUEST_URL));
        assertNull(MDC.get(LogRequestFilter.REQUEST_REFERER_URL));
        assertNull(MDC.get(LogRequestFilter.JSESSION_ID));
        assertNull(MDC.get(LogRequestFilter.SESSION_ID));
    }
}

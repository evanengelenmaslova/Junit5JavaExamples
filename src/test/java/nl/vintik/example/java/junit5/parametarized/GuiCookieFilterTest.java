package nl.vintik.example.java.junit5.parametarized;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static nl.vintik.example.java.junit5.parametarized.GuiCookieFilter.GUI_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class GuiCookieFilterTest {

    @Nested
    @ExtendWith(MockitoExtension.class)
    public static class GuiCookieFilterRegularTest {

        private GuiCookieFilter filterUnderTest;

        private MockHttpServletResponse response;
        private MockHttpServletRequest request;

        @Mock
        private FilterChain filterChain;
        @Mock
        private CookieHelper cookieHelper;

        private int randomizerInvocation = 0;
        private BiFunction<Integer, String, String> randomizer = (a, b) -> String.format("%d-%s-%d", a, b, randomizerInvocation++);

        @BeforeEach
        public void setup() {
            request = new MockHttpServletRequest();
            response = new MockHttpServletResponse();

            randomizerInvocation = 0;
            request.setRequestURI("/nl/");

            filterUnderTest = new GuiCookieFilter();
            filterUnderTest.init(randomizer, cookieHelper);
        }

        @Test
        public void invalidCookieAlsoSetsRequestAttribute() throws IOException, ServletException {
            filterUnderTest.doFilter(request, response, filterChain);

            assertNotNull(request.getAttribute(GUI_NAME));
        }

        @Test
        public void doTestRandomizer() {
            assertEquals(this.randomizer.apply(1, "b"), "1-b-0");
            assertEquals(this.randomizer.apply(1, "b"), "1-b-1");
        }

        @Test
        public void doFilterEmpty() throws IOException, ServletException {
            filterUnderTest.doFilter(request, response, filterChain);

            assertEquals("32-0123456789abcdefghijklmnopqrstuvwxyz-0", response.getCookie(GUI_NAME).getValue());
            verify(filterChain, times(1)).doFilter(request, response); // show must go on
        }

        @Test
        public void doFilterWhenSetDontUpdate() throws IOException, ServletException {
            filterUnderTest.doFilter(request, response, filterChain);

            String cookieValue = response.getCookie(GUI_NAME).getValue();
            Cookie[] cookie = new Cookie[]{new Cookie(GUI_NAME, cookieValue)};

            when(cookieHelper.updateCookie(cookieValue)).thenReturn(cookieValue);

            MockHttpServletRequest secondRequest = new MockHttpServletRequest();
            MockHttpServletResponse secondResponse = new MockHttpServletResponse();
            secondRequest.setCookies(cookie);

            filterUnderTest.doFilter(secondRequest, secondResponse, filterChain);

            assertEquals("32-0123456789abcdefghijklmnopqrstuvwxyz-0", response.getCookie(GUI_NAME).getValue());
            assertEquals("32-0123456789abcdefghijklmnopqrstuvwxyz-0", secondResponse.getCookie(GUI_NAME).getValue());

            verify(filterChain, times(1)).doFilter(request, response);
            verify(filterChain, times(1)).doFilter(secondRequest, secondResponse);
            verify(cookieHelper, times(1)).updateCookie(cookieValue);
        }
    }

    @Nested
    public static class GuiCookieFilterParameterizedTest {

        private GuiCookieFilter filterUnderTest;

        private MockHttpServletResponse response;
        private MockHttpServletRequest request;

        private FilterChain filterChain = mock(FilterChain.class);
        private CookieHelper removeIPAddressFromBUI = new CookieHelper();

        private int randomizerInvocation = 0;
        private BiFunction<Integer, String, String> randomizer = (a, b) -> String.format("%d-%s-%d", a, b, randomizerInvocation++);

        @BeforeEach
        public void setup() {
            request = new MockHttpServletRequest();
            response = new MockHttpServletResponse();

            randomizerInvocation = 0;

            request.setRequestURI("/nl/");

            filterUnderTest = new GuiCookieFilter();
            filterUnderTest.init(randomizer, removeIPAddressFromBUI);
        }

        public static Stream<Arguments> data() {
            List<Arguments> testCases = new ArrayList<>();

            // The test case from the other test class
            testCases.add(Arguments.of("131.211.85.1.74893927343", "131.211.85.1.74893927343"));
            testCases.add(Arguments.of("vynxua247pfc69r4jlk04lq13ycfvw52", "vynxua247pfc69r4jlk04lq13ycfvw52"));
            testCases.add(Arguments.of("1234567780kjhkjhadsfkadsf", "1234567780kjhkjhadsfkadsf"));
            return testCases.stream();
        }

        @ParameterizedTest
        @MethodSource("data")
        public void doFilterWhenSetDontUpdate(String inputGUI, String expectedOutputGUI) throws IOException, ServletException {
            Cookie[] cookie = new Cookie[]{new Cookie(GUI_NAME, inputGUI)};
            request.setCookies(cookie);

            filterUnderTest.doFilter(request, response, filterChain);

            assertEquals(expectedOutputGUI, response.getCookie(GUI_NAME).getValue());
            verify(filterChain, times(1)).doFilter(request, response);
        }
    }
}

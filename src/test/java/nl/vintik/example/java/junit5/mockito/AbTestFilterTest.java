package nl.vintik.example.java.junit5.mockito;

import nl.vintik.example.java.junit5.DeviceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static nl.vintik.example.java.junit5.mockito.AbTestFilter.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbTestFilterTest {

    @Mock
    private FilterChain mockFilterChain;
    @Mock
    private AbTestsContext mockAbTestsContext;
    @Mock
    private DeviceService mockDeviceService;
    @Mock
    private ToggleService mockToggleService;

    private AbTestFilter abTestFilter = new AbTestFilter();

    @BeforeEach
    public void setUp() {
        when(mockDeviceService.getDeviceTypeByUserAgent(any())).thenReturn(DeviceType.DESKTOP);
        when(mockToggleService.isEnabled(any())).thenReturn(false);
        abTestFilter.init(mockAbTestsContext, mockDeviceService, mockToggleService);
    }

    @Test
    public void shouldReturnVariantFromRequestParameter() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String[] variantIds = new String[]{"1", "2"};
        request.setParameter(ABTEST_REQUEST_PARAM_NAME, variantIds);
        when(mockAbTestsContext.setActiveVariantsForUser(variantIds)).thenReturn(createActiveVariantsMap(variantIds));

        abTestFilter.doFilter(request, response, mockFilterChain);

        assertArrayEquals(variantIds,
                Arrays.stream(response.getCookie(ABTEST_COOKIE_NAME).getValue().split(SEPARATOR)).sorted().toArray());
    }

    @Test
    public void shouldReturnNewVariantsIfAbtSessionIsNotSet() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String[] existingVariantIds = new String[]{};
        String[] expectedVariantIds = new String[]{"1", "2"};
        Map<AbTestCase, AbTestVariant> activeVariantsMap = createActiveVariantsMap(expectedVariantIds);
        when(mockAbTestsContext.determineVariantsForUser(existingVariantIds)).thenReturn(activeVariantsMap);
        when(mockAbTestsContext.setActiveVariantsForUser(activeVariantsMap)).thenReturn(activeVariantsMap);

        abTestFilter.doFilter(request, response, mockFilterChain);

        verify(mockAbTestsContext).setActiveVariantsForUser(activeVariantsMap);
        assertArrayEquals(expectedVariantIds,
                Arrays.stream(response.getCookie(ABTEST_COOKIE_NAME).getValue().split(SEPARATOR)).sorted().toArray());
    }

    @Test
    public void shouldCatchExceptionFromAbTests() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String[] existingVariantIds = new String[]{};
        String[] expectedVariantIds = new String[]{"1", "2"};
        Map<AbTestCase, AbTestVariant> activeVariantsMap = createActiveVariantsMap(expectedVariantIds);
        when(mockAbTestsContext.determineVariantsForUser(existingVariantIds)).thenThrow(new RuntimeException("test"));

        abTestFilter.doFilter(request, response, mockFilterChain);

        verify(mockFilterChain, times(1)).doFilter(request, response);

    }

    private Map<AbTestCase, AbTestVariant> createActiveVariantsMap(String[] ids) {
        Map<AbTestCase, AbTestVariant> variantMap = new HashMap<>();
        int index = 0;
        for (String id : ids) {
            AbTestVariant abtAbTestVariant = createAbtAbTestVariant(id);
            variantMap.put(
                    createAbtAbTestCase(Integer.toString(index), abtAbTestVariant),
                    abtAbTestVariant);
            index++;
        }
        return variantMap;
    }

    private AbTestVariant createAbtAbTestVariant(String id) {
        return new AbTestVariant(id);
    }

    private AbTestCase createAbtAbTestCase(String id, AbTestVariant variant) {
        return new AbTestCase(id, "name",
                Collections.singletonList(variant));
    }
}

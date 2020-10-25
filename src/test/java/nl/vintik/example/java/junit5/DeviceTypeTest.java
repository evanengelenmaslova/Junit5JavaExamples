package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeviceTypeTest {

    @Test
    public void shouldIndicateSEODeviceTypeWhenSeo() {
        assertTrue(DeviceType.SEO.isSeo(), "Expected SEO is Seo");
        assertTrue(DeviceType.SEO_MOBILE.isSeo(), "Expected SEO MOBILE is Seo");
    }

    @Test
    public void shouldIndicateNotSEODeviceTypeWhenDesktop() {
        assertFalse(DeviceType.DESKTOP.isSeo(), "Expected SEO is Seo");
    }
}
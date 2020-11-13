package nl.vintik.example.java.junit5.spring;

import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Simple spring example
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CacheConfiguration.class)
@DisplayName("Simple spring example test")
public class CacheConfigurationTest {

    @Autowired
    private List<Cache> caches;

    @Test
    public void shouldBootUpSpringConfig() {
        assertNotEquals(0, caches.size());
    }

    @Test
    @DisplayName("Should override configuration, tested with 50K and 200")
    public void shouldOverrideCacheConfiguration() {
        assertEquals(50_000, CacheConfiguration.size("maxElementsInMemory|50000", 200));
        assertEquals(200, CacheConfiguration.size("maxElementsInMemories|50000", 200));
    }

    @Test
    @Disabled("Ignored test example")
    public void shouldOverrideCacheConfiguration_ignored() {
        assertEquals(100_000, CacheConfiguration.size("maxElementsInMemory|100000", 200));
        assertEquals(200, CacheConfiguration.size("maxElementsInMemories|50000", 200));
    }
}
package nl.vintik.example.java.junit5.spring;

import com.github.benmanes.caffeine.cache.Cache;
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
public class CacheConfigurationTest {

    @Autowired
    private List<Cache> caches;

    @Test
    public void shouldBootUpSpringConfig() {
        assertNotEquals(0, caches.size());
    }

    @Test
    public void shouldOverrideCacheConfiguration() {
        assertEquals(50_000, CacheConfiguration.size("maxElementsInMemory|50000", 200));
        assertEquals(200, CacheConfiguration.size("maxElementsInMemories|50000", 200));
    }
}
package nl.vintik.example.java.junit5.spring;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Simple spring example
 */
public class CacheConfiguration {

    @Bean
    public Cache abTestCache(@Value("${abTestCache:}") String override) {
        return Caffeine.newBuilder()
                .maximumSize(size(override, 2))
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    static long size(String override, long defaultValue) {
        try {
            List<String> strings = Splitter.on(CharMatcher.anyOf("|;")).omitEmptyStrings().splitToList(override);

            for (int i = 0; i < strings.size(); i++) {
                if (strings.get(i).equals("maxElementsInMemory")) {
                    return Long.parseLong(strings.get(i + 1));
                }
            }
            return defaultValue;
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed override: " + override, e);
        }
    }
}

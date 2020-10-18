package nl.vintik.example.java.junit5.parametarized;

import nl.vintik.example.java.junit5.Toggle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Parameterised test applies tests to  all defined features in Feature class
 */
public class FeatureTest {

    public static Stream<Arguments> data() {
        return Arrays.stream(Feature.values()).map(Arguments::of); // this test explicitly doesnt test operation switches as they have names that are too long
    }

    @ParameterizedTest
    @MethodSource("data")
    public void shouldContainKeyswithLengthBetween5And35Inclusive(Toggle toggle) {
        assertTrue(toggle.name().length() >= 5);
        assertTrue(toggle.name().length() <= 35);
    }
}

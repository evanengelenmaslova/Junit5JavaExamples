package nl.vintik.example.java.junit5.parametarized;

import nl.vintik.example.java.junit5.mockito.Toggle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Parameterised test applies tests to  all defined features in Feature class
 */
public class FeatureTest {

    @ParameterizedTest(name = "#{index} - value of {0} should be between 5 and 35")
    @EnumSource(Feature.class)
    public void shouldContainKeysWithLengthBetween5And35Inclusive(Toggle toggle) {
        assertTrue(toggle.name().length() >= 5);
        assertTrue(toggle.name().length() <= 35);
    }
}

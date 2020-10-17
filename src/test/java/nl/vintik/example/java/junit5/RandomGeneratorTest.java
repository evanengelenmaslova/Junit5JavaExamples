package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomGeneratorTest {

    private RandomGenerator testedClass;

    @BeforeEach
    public void setup() {
        testedClass = new RandomGenerator();
    }

    @Test
    public void getRandomInt_shouldThrowExceptionWhenUpperBoundIsNotAPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> testedClass.getRandomInt(0));
    }

    @Test
    public void getRandomInt_shouldGetRandomIntWhenUpperBoundIsValid() {
        final int upperBound = 2;
        assertTrue(testedClass.getRandomInt(upperBound) < upperBound);
    }
}
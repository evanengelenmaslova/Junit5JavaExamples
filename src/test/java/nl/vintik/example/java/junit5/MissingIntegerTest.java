package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissingIntegerTest {

    /**
     * example & negative_only
     */
    @Test
    public void shouldReturn1WhenAllNegatives() {
        int[] A = {-3, -1, -2};
        assertEquals(1, MissingInteger.solution(A));
    }

    /**
     * positive_only
     */
    @Test
    public void shouldReturn1When1Missing() {
        int[] A = {2, 4, 6, 8};
        assertEquals(1, MissingInteger.solution(A));
    }

    /**
     * positive_only
     */
    @Test
    public void shouldReturnMaxPlusOneWhenNoneMissing() {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(9, MissingInteger.solution(A));
    }

    /**
     * extreme_single
     */
    @Test
    public void shouldReturn1WhenZero() {
        int[] A = {0};
        assertEquals(1, MissingInteger.solution(A));
    }

    /**
     * extreme_single 2
     */
    @Test
    public void shouldReturn2WhenOne() {
        int[] A = {1};
        assertEquals(2, MissingInteger.solution(A));
    }


    /**
     * extreme_min_max_value
     */
    @Test
    public void shouldExtremeMinMax_value() {
        int[] A = {-1_000_000, 1_000_000};
        assertEquals(1, MissingInteger.solution(A));
    }


    /**
     * chaotic + many -1, 1, 2, 3 (with minus)
     */
    @Test
    public void shouldChaoricManyWithMinus() {
        int[] A = {-1, 0, -1, 1, 2, 3, 1, 2, 3, -4};
        assertEquals(4, MissingInteger.solution(A));
    }

    /**
     * chaotic sequences length=10005 (without minus)
     */
    @Test
    public void shouldChaoricManyWithoutMinus() {
        int[] A = {1, 2, 3, 1, 2, 3, -4};
        assertEquals(4, MissingInteger.solution(A));
    }

    /**
     * simple test
     */
    @Test
    public void shouldReturnMissing3() {
        int[] A = {4, 1, 5, 6, 2};
        assertEquals(3, MissingInteger.solution(A));
    }


}

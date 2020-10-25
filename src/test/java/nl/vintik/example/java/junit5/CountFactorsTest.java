package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountFactorsTest {

    /**
     * For example, given N = 24, the function should return 8, because 24 has 8
     * factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of
     * 24.
     */
    @Test
    public void testExample() {
        assertEquals(8, CountFactors.solution(24));
    }

    @Test
    public void testSimple() {
        assertEquals(4, CountFactors.solution(8));
    }

    @Test
    public void testPrime() {
        assertEquals(2, CountFactors.solution(13));
    }

    @Test
    public void testOne() {
        assertEquals(1, CountFactors.solution(1));
    }

    @Test
    public void testExtremeLarge() {
        assertEquals(2, CountFactors.solution(2_147_483_647));
    }

    @Test
    public void testSquares1() {
        assertEquals(5, CountFactors.solution(16));
    }

    @Test
    public void testSquares2() {
        assertEquals(9, CountFactors.solution(36));
    }

    @Test
    public void testMedium() {
        assertEquals(45, CountFactors.solution(4_999_696));
    }

    @Test
    public void testExtremeMax() {
        assertEquals(135, CountFactors.solution(2147_395_600));

    }

}

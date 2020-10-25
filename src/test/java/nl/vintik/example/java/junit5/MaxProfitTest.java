package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxProfitTest {

    /**
     * A[0] = 23171 A[1] = 21011 A[2] = 21123 A[3] = 21366 A[4] = 21013 A[5] =
     * 21367 the function should return 356.
     *
     * @throws Exception
     */
    @Test
    public void testExample() throws Exception {
        int[] A = {23171, 21011, 21123, 21366, 21013, 21367};
        assertEquals(356, MaxProfit.solution(A));
    }

    @Test
    public void testExtremeEmpty1() throws Exception {
        int[] A = {};
        assertEquals(0, MaxProfit.solution(A));
    }


    @Test
    public void testExtremeEmpty2() throws Exception {
        int[] A = null;
        assertEquals(0, MaxProfit.solution(A));
    }

    @Test
    public void testExtremeLow() throws Exception {
        int[] A = {0, 0};
        assertEquals(0, MaxProfit.solution(A));
    }

    @Test
    public void testExtremeSmall() throws Exception {
        int[] A = {100};
        assertEquals(0, MaxProfit.solution(A));
    }

    @Test
    public void testNoProfit() throws Exception {
        int[] A = {100, 90, 80, 70, 60};
        assertEquals(0, MaxProfit.solution(A));
    }
}

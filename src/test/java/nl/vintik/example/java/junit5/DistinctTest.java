package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Write a function
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns the number of
 * distinct values in array A.
 * <p>
 * For example, given array A consisting of six elements such that:
 * <p>
 * A[0] = 2 A[1] = 1 A[2] = 1 A[3] = 2 A[4] = 3 A[5] = 1 the function should
 * return 3, because there are 3 distinct values appearing in array A, namely 1,
 * 2 and 3.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000]; each element of array A is an
 * integer within the range [−1,000,000..1,000,000].
 *
 * @author evanengelen
 */
public class DistinctTest {

    @Test
    public void testSimple() {
        int[] A = {2, 1, 1, 2, 3, 1};
        assertEquals(3, Distinct.solution(A));
    }

    @Test
    public void testExtremeEmpty() {
        int[] A = {};
        assertEquals(0, Distinct.solution(A));
    }

    @Test
    public void testExtremeOneValue() {
        int[] A = {1, 1, 1, 1, 1, 1};
        assertEquals(1, Distinct.solution(A));
    }

    @Test
    public void testExtremeSingle() {
        int[] A = {2};
        assertEquals(1, Distinct.solution(A));
    }

    @Test
    public void testExtremeNegative() {
        int[] A = {-1, -3, -3, -4, -7, -8, -9};
        assertEquals(6, Distinct.solution(A));
    }

    @Test
    public void testExtremeBigValues() {
        int[] A = {-1_000_000, -3, -3, -4, -7, -8, -9, 1, 3, 4, 6, 1_000_000};
        assertEquals(11, Distinct.solution(A));
    }

}

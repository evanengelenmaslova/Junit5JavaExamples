package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinMaxDivisionTest {

    /**
     * For example, you are given integers K = 3, M = 5 and array A such that:
     * <p>
     * A[0] = 2 A[1] = 1 A[2] = 5 A[3] = 1 A[4] = 2 A[5] = 2 A[6] = 2 The array
     * can be divided, for example, into the following blocks:
     * <p>
     * [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15; [2], [1, 5, 1, 2],
     * [2, 2] with a large sum of 9; [2, 1, 5], [], [1, 2, 2, 2] with a large
     * sum of 8; [2, 1], [5, 1], [2, 2, 2] with a large sum of 6. The goal is to
     * minimize the large sum. In the above example, 6 is the minimal large sum.
     * <p>
     * <p>
     * N and K are integers within the range [1..100,000]; M is an integer
     * within the range [0..10,000]; each element of array A is an integer
     * within the range [0..M].
     */
    @Test
    public void testExample() {
        int K = 3;
        int M = 5;
        int[] A = {2, 1, 5, 1, 2, 2, 2};

        assertEquals(6, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testNsameasK() {
        int K = 3;
        int M = 3;
        int[] A = {1, 2, 3};

        assertEquals(3, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testMoreBlocksThanNumbers() {
        int K = 4;
        int M = 3;
        int[] A = {1, 2, 3};

        assertEquals(3, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testExtremeSmallArray() {
        int K = 100;
        int M = 0;
        int[] A = {0};

        assertEquals(0, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testExtremeLargeNumbers() {
        int K = 2;
        int M = 10_000;
        int[] A = {10_000, 10_000, 10_000, 10_000};

        assertEquals(20_000, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testExtremeDifference1() {
        int K = 3;
        int M = 10_000;
        int[] A = {1, 1, 1, 1, 1, 1, 1, 10_000, 10_000};

        assertEquals(10_000, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testExtremeDifference2() {
        int K = 6;
        int M = 10_000;
        int[] A = {5_000, 3_000, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10_000, 10_000};

        assertEquals(10_000, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testOneBlock() {
        int K = 1;
        int M = 6;
        int[] A = {1, 3, 6};

        assertEquals(10, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testEndBigger() {
        int K = 3;
        int M = 6;
        int[] A = {1, 0, 0, 2, 6, 6, 6};

        assertEquals(9, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testStartBigger() {
        int K = 3;
        int M = 6;
        int[] A = {6, 6, 6, 1, 0, 0, 2,};

        assertEquals(9, MinMaxDivision.solution(K, M, A));
    }

    @Test
    public void testMiddleBigger() {
        int K = 3;
        int M = 6;
        int[] A = {1, 0, 0, 2, 6, 6, 6, 0, 3, 2, 1, 0};

        assertEquals(12, MinMaxDivision.solution(K, M, A));
    }

}

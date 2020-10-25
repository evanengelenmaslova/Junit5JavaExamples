package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DominatorTest {

    /**
     * For example, given array A such that
     * <p>
     * A[0] = 3 A[1] = 4 A[2] = 3 A[3] = 2 A[4] = 3 A[5] = -1 A[6] = 3 A[7] = 3
     * the function may return 0, 2, 4, 6 or 7, as explained above.
     */
    @Test
    public void testExample() {
        int[] A = {3, 4, 3, 2, 3, -1, 3, 3};
        assertEquals(7, Dominator.solution(A));
    }

    @Test
    public void testExtremeEmpty1() {
        int[] A = {};
        assertEquals(-1, Dominator.solution(A));
    }

    @Test
    public void testExtremeSmall() {
        int[] A = {1};
        assertEquals(0, Dominator.solution(A));
    }

    @Test
    public void testExtremeSmall2() {
        int[] A = {1, 2};
        assertEquals(-1, Dominator.solution(A));
    }

    @Test
    public void testExtremeSmall3() {
        int[] A = {1, 1};
        assertEquals(1, Dominator.solution(A));
    }

    @Test
    public void testExtremeEmpty2() {
        int[] A = null;
        assertEquals(-1, Dominator.solution(A));
    }

    @Test
    public void testNoDominatorEdgeCase() {
        int[] A = {3, 4, 1, 2, 3, -1, 3, 3};
        assertEquals(-1, Dominator.solution(A));
    }

    @Test
    public void testNoDominatorSimple() {
        int[] A = {3, 4, 1, 2, 10, -1, 8, 9};
        assertEquals(-1, Dominator.solution(A));
    }

}

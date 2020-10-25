package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassingCarsTest {

    /**
     * A[0] = 0 A[1] = 1 A[2] = 0 A[3] = 1 A[4] = 1 We have five pairs of
     * passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).
     */
    @Test
    public void simple_example() {
        int[] A = {0, 1, 0, 1, 1};
        assertEquals(5, PassingCars.solution(A));
    }

    /**
     * { 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1 } passing cars: 26
     */
    @Test
    public void simple_example2() {
        int[] A = {1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1};
        assertEquals(26, PassingCars.solution(A));
    }

    @Test
    public void extreme_small1() {
        int[] A = {1};
        assertEquals(0, PassingCars.solution(A));
    }

    @Test
    public void extreme_small2() {
        int[] A = {0};
        assertEquals(0, PassingCars.solution(A));
    }

    @Test
    public void extreme_onlyones() {
        int[] A = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        assertEquals(0, PassingCars.solution(A));
    }

    @Test
    public void extreme_onlyzeros() {
        int[] A = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertEquals(0, PassingCars.solution(A));
    }

}

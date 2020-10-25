package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TapeEquilibriumTest {

    @Test
    public void shouldReturn1ForExampleTestCase() {
        int[] A = {3, 1, 2, 4, 3};
        assertEquals(1, TapeEquilibrium.solution(A));
    }

    @Test
    public void shouldReturn0ForTestCaseWithNegatives() {
        int[] A = {-3, 3, 1, 2, 4, 3, -4};
        assertEquals(0, TapeEquilibrium.solution(A));
    }

    @Test
    public void shouldReturn6ForTestCaseWith2Elements() {
        int[] A = {2, 8};
        assertEquals(6, TapeEquilibrium.solution(A));
    }

}

package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FishTest {

    @Test
    public void testExample() {
        int[] A = {4, 3, 2, 1, 5};
        int[] B = {0, 1, 0, 0, 0};
        assertEquals(2, Fish.solution(A, B));
    }

    @Test
    public void testExtremeLarge() {
        int[] A = {0, 4, 3, 2, 1, 5, 1_000_000_000};
        int[] B = {0, 1, 0, 0, 0, 1, 1};
        assertEquals(4, Fish.solution(A, B));
    }

    @Test
    public void testOnlyOneFish() {
        int[] A = {4};
        int[] B = {0};
        assertEquals(1, Fish.solution(A, B));
    }

    @Test
    public void testOnlyOneFish2() {
        int[] A = {4};
        int[] B = {1};
        assertEquals(1, Fish.solution(A, B));
    }

    @Test
    public void testOnlyTwoFish() {
        int[] A = {4, 6};
        int[] B = {1, 0};
        assertEquals(1, Fish.solution(A, B));
    }

    @Test
    public void testOnlyTwoFish2() {
        int[] A = {6, 4};
        int[] B = {1, 0};
        assertEquals(1, Fish.solution(A, B));
    }

    @Test
    public void testOnlyTwoFishReverse() {
        int[] A = {4, 6};
        int[] B = {0, 1};
        assertEquals(2, Fish.solution(A, B));
    }

    @Test
    public void testOnlyTwoFishReverse2() {
        int[] A = {6, 4};
        int[] B = {0, 1};
        assertEquals(2, Fish.solution(A, B));
    }

    @Test
    public void testOnlyUpstreamFish() {
        int[] A = {4, 56, 7, 8};
        int[] B = {0, 0, 0, 0};
        assertEquals(4, Fish.solution(A, B));
    }

    @Test
    public void testOnlyDownstreamFish() {
        int[] A = {4, 56, 7, 8};
        int[] B = {1, 1, 1, 1};
        assertEquals(4, Fish.solution(A, B));
    }

    @Test
    public void testExtremeSeparation() {
        int[] A = {5, 3, 1, 0, 2, 6};
        int[] B = {1, 1, 1, 0, 0, 0};
        assertEquals(1, Fish.solution(A, B));
    }

}

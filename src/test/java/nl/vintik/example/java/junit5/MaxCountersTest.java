package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MaxCountersTest {

    @Test
    public void test() {
        int[] testArray = {3, 4, 4, 6, 1, 4, 4};

        assertArrayEquals(new int[]{3, 2, 2, 4, 2}, MaxCounters.solution(5, testArray));
    }

}

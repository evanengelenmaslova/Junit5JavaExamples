package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static nl.vintik.example.java.junit5.BinaryGap.solution;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryGapTest {

    @Test
    public void shouldReturn1ForInt20() {
        assertEquals(1, solution(20));
    }

    @Test
    public void shouldReturn4ForInt529() {
        assertEquals(4, solution(529));
    }

    @Test
    public void shouldReturn0ForInt15() {
        assertEquals(0, solution(15));
    }

    @Test
    public void shouldReturn2ForInt9() {
        assertEquals(2, solution(9));
    }

    @Test
    public void shouldReturn0ForInt0() {
        assertEquals(0, solution(0));
    }

}

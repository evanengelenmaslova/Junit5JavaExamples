package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrogJmpTest {

    @Test
    public void shouldNotJumeWhenYSameAsX() {
        assertEquals(0, FrogJmp.solution(1, 1, 1));
    }

    /**
     * X = 10 Y = 85 D = 30 the function should return 3
     */
    @Test
    public void shouldJump3TimesWhenX10Y85D30() {
        assertEquals(3, FrogJmp.solution(10, 85, 30));
    }

}

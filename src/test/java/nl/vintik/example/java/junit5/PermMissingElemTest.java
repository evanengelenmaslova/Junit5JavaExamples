package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermMissingElemTest {

    @Test
    public void shouldFindMissing4InArray1235() {
        int[] testedArray = {2, 3, 1, 5};
        assertEquals(4, PermMissingElem.solution(testedArray));
    }

    @Test
    public void shouldFindMissing9InLongArray() {
        int[] testedArray = {2, 3, 1, 5, 8, 4, 6, 10, 7};
        assertEquals(9, PermMissingElem.solution(testedArray));
    }

    @Test
    public void shouldFindMissingLast() {
        int[] testedArray = {1, 2, 3, 4, 5};
        assertEquals(6, PermMissingElem.solution(testedArray));
    }

    @Test
    public void shouldFindMissing1WhenEmptyArray() {
        int[] testedArray = {};
        assertEquals(1, PermMissingElem.solution(testedArray));
    }

    @Test
    public void shouldFindMissingElementAtTheEndOfArray() {
        int[] testedArray = {1, 2, 3, 4, 5, 7};
        assertEquals(6, PermMissingElem.solution(testedArray));
    }

    @Test
    public void shouldFindMissing2When1InArray() {
        int[] testedArray = {1};
        assertEquals(2, PermMissingElem.solution(testedArray));
    }

    @Test
    public void shouldFindMissing3WhenTwoElementsInArray() {
        int[] testedArray = {1, 2};
        assertEquals(3, PermMissingElem.solution(testedArray));
    }

}

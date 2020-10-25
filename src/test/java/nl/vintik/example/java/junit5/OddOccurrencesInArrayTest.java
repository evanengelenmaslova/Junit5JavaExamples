package nl.vintik.example.java.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OddOccurrencesInArrayTest {

    @Test
    public void shouldReturnUniqueElementInTheMiddle() {
        int[] testedArray = {9, 3, 9, 3, 9, 7, 9};
        int result = OddOccurrencesInArray.solution(testedArray);
        assertEquals(7, result);
    }

    @Test
    public void shouldReturnUniqueElementAtTheEnd() {
        int[] testedArray = {9, 3, 9, 3, 9, 10, 9};
        int result = OddOccurrencesInArray.solution(testedArray);
        assertEquals(10, result);
    }

    @Test
    public void shouldReturnUniqueElementAtTheStart() {
        int[] testedArray = {9, 3, 9, 3, 9, 1, 9};
        int result = OddOccurrencesInArray.solution(testedArray);
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnOnlyElementOnOneElementInArray() {
        int[] testedArray = {9};
        int result = OddOccurrencesInArray.solution(testedArray);
        assertEquals(9, result);
    }
}

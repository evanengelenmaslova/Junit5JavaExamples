import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Junit5CheatSheet {

    //Exceptions

    @Test// was @Test(expected = IllegalArgumentException.class)
    void shouldRaiseAnException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
    }

    @Test// was @Test(expected = IllegalArgumentException.class, message = "a message")
    void shouldRaiseAnExceptionwthMessage() throws Exception {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    //Parameterised tests

    static Stream<Arguments> dataSource() {
        List<Arguments> testCases = new ArrayList<>();
        testCases.add(Arguments.of(1, 6));
        testCases.add(Arguments.of(2, 7));
        testCases.add(Arguments.of(3, 8));
        return testCases.stream();
    }

    @ParameterizedTest
    @MethodSource("dataSource")
    void shouldAddTo5(int input, int expected) {
        assertEquals(expected, addTo5(input), () -> String.format("should %d to 5 and get %d"));
    }

    @ParameterizedTest
    @EnumSource(Month.class)
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    private int addTo5(int input) {
        return input + 5;
    }


}
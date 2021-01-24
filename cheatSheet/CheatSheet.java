import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Junit5CheatSheet {

    //Set up & tear down

    //was @BeforeClass
    @BeforeAll
    static void beforeAll(){
        //run before all tests
    }

    //was @Before
    @BeforeEach
    void setUp(){
        //run before each test
    }

    //was @Before
    @AfterEach
    void tearDown(){
        //run after each test
    }

    //was @AfterClass
    @AfterAll
    static void afterAll(){
        //run after all tests
    }

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

    @ParameterizedTest(name = "#{index} - should add {0} to 5 and get {1}")
    @MethodSource("dataSource")
    void shouldAddTo5(int input, int expected) {
        assertEquals(expected, addTo5(input));
    }

    @ParameterizedTest(name = "#{index} - value of {0} should be between 1 and 12")
    @EnumSource(Month.class)
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    private int addTo5(int input) {
        return input + 5;
    }


}
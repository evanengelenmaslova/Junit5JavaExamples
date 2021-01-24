import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Junit5CheatSheet {

    //Exceptions

    @Test// was @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseAnException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
    }

    @Test// was @Test(expected = IllegalArgumentException.class, message = "a message")
    public void shouldRaiseAnException() throws Exception {
        Throwable exception =  assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    //Parameterised tests

}
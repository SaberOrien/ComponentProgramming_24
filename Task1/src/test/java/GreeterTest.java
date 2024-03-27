import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.example.Greeter;
import org.example.MessageFormatter;
import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void testGreetWithNull() {
        MessageFormatter formatter = new MessageFormatter();
        Greeter greeter = new Greeter(formatter);

        String result = greeter.greet(null);
        assertEquals("", result);
    }

    @Test
    void testGreetWithEmptyString() {
        MessageFormatter formatter = new MessageFormatter();
        Greeter greeter = new Greeter(formatter);

        String result = greeter.greet("");
        assertEquals("", result);
    }

    @Test
    void testGreetWithStudentString() {
        MessageFormatter formatter = new MessageFormatter();
        Greeter greeter = new Greeter(formatter);

        String result = greeter.greet("Student");
        assertEquals("Student", result);
    }

    @Test
    void testGreetSameString() {
        MessageFormatter formatter = new MessageFormatter();
        Greeter greeter = new Greeter(formatter);

        String who = "Student";
        String result = greeter.greet(who);

        assertSame(who, result);
    }
}

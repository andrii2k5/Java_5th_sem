import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAdd() throws InvalidInputException {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtract() throws InvalidInputException {
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    void testMultiply() throws InvalidInputException {
        assertEquals(12, calculator.multiply(4, 3));
    }

    @Test
    void testDivide() throws InvalidInputException {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Test
    void testInvalidInput() {
        assertThrows(InvalidInputException.class, () -> calculator.add(Double.NaN, 5));
    }
}

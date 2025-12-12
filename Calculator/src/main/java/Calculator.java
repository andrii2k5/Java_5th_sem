public class Calculator {

    public double add(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        if (b == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a / b;
    }

    private void validateInput(double a, double b) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new InvalidInputException("Input values must be valid numbers!");
        }
    }
}

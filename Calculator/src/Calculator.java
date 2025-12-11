public class Calculator {

    public double add(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validate(a, b);
        if (b == 0) {
            throw new ArithmeticException("Помилка: ділення на нуль!");
        }
        return a / b;
    }

    private void validate(double a, double b) throws InvalidInputException {
        if (a < 0 || b < 0) {
            throw new InvalidInputException("Вхідні дані не можуть бути від’ємними!");
        }
    }
}

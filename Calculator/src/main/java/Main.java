import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Enter first number: ");
            double a = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double b = scanner.nextDouble();

            System.out.print("Enter operation (+, -, *, /): ");
            String op = scanner.next();

            double result;

            switch (op) {
                case "+" -> result = calculator.add(a, b);
                case "-" -> result = calculator.subtract(a, b);
                case "*" -> result = calculator.multiply(a, b);
                case "/" -> result = calculator.divide(a, b);
                default -> throw new InvalidInputException("Unknown operation: " + op);
            }

            System.out.println("Result: " + result);

        } catch (InvalidInputException | ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Request processing finished.");
        }
    }
}

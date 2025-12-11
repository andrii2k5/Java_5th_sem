import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = scanner.nextDouble();

            System.out.print("Введіть друге число: ");
            double b = scanner.nextDouble();

            System.out.print("Оберіть операцію (+, -, *, /): ");
            String op = scanner.next();

            double result = 0;

            switch (op) {
                case "+":
                    result = calculator.add(a, b);
                    break;
                case "-":
                    result = calculator.subtract(a, b);
                    break;
                case "*":
                    result = calculator.multiply(a, b);
                    break;
                case "/":
                    result = calculator.divide(a, b);
                    break;
                default:
                    System.out.println("Невірна операція!");
                    return;
            }

            System.out.println("Результат: " + result);

        } catch (InvalidInputException e) {
            System.out.println("Помилка вводу: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Арифметична помилка: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Помилка: введено не число!");
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        } finally {
            System.out.println("Обробка завершена.");
        }
    }
}

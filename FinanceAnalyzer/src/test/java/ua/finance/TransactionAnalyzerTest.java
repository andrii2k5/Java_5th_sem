package ua.finance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.finance.core.TransactionAnalyzer;
import ua.finance.model.Transaction;

import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        List<Transaction> list = Arrays.asList(
                new Transaction("01-01-2024", 1000.0, "Salary"),
                new Transaction("05-01-2024", -250.0, "Groceries"),
                new Transaction("10-01-2024", -1200.0, "Rent"),
                new Transaction("15-02-2024", -50.0, "Coffee"),
                new Transaction("20-02-2024", 200.0, "Refund")
        );

        double expected = 1000.0 - 250.0 - 1200.0 - 50.0 + 200.0;
        double result = TransactionAnalyzer.calculateTotalBalance(list);

        Assertions.assertEquals(expected, result, 0.0001, "Невірний розрахунок загального балансу");
    }

    @Test
    public void testCountTransactionsByMonth() {
        List<Transaction> list = Arrays.asList(
                new Transaction("01-01-2024", 1000.0, "Salary"),
                new Transaction("05-01-2024", -250.0, "Groceries"),
                new Transaction("10-01-2024", -1200.0, "Rent"),
                new Transaction("15-02-2024", -50.0, "Coffee"),
                new Transaction("20-02-2024", 200.0, "Refund")
        );

        int countJan = TransactionAnalyzer.countTransactionsByMonth(list, "01-2024");
        int countFeb = TransactionAnalyzer.countTransactionsByMonth(list, "02-2024");

        Assertions.assertEquals(3, countJan, "Кількість транзакцій за 01-2024 має бути 3");
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за 02-2024 має бути 2");
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> list = Arrays.asList(
                new Transaction("01-01-2024", -10.0, "A"),
                new Transaction("02-01-2024", -100.0, "B"),
                new Transaction("03-01-2024", -50.0, "C"),
                new Transaction("04-01-2024", -500.0, "D"),
                new Transaction("05-01-2024", 200.0, "Income")
        );

        List<Transaction> top = TransactionAnalyzer.findTopExpenses(list);

        Assertions.assertFalse(top.isEmpty());
        Assertions.assertEquals(-500.0, top.get(0).getAmount(), "Перший елемент має бути найбільшою витратою (найменше число)");
    }
}

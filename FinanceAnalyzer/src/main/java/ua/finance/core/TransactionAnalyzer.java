package ua.finance.core;

import ua.finance.model.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        return (int) transactions.stream()
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), formatter);
                    String my = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                    return my.equals(monthYear);
                })
                .count();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Transaction findMaxExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findMinExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }
}

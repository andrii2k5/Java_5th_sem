package ua.finance.core;

import ua.finance.model.Transaction;

import java.util.List;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        topExpenses.forEach(t ->
                System.out.println(t.getDescription() + ": " + t.getAmount())
        );
    }
}

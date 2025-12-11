package ua.finance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.finance.core.TransactionCSVReader;
import ua.finance.model.Transaction;

import java.net.URL;
import java.util.List;

public class TransactionCSVReaderTest {

    @Test
    public void testReadCSVFromTestResources() {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("pr2_test.csv");
        Assertions.assertNotNull(resource, "Не знайдено pr2_test.csv у src/test/resources");

        String fileUrl = resource.toString();

        List<Transaction> list = TransactionCSVReader.readTransactions(fileUrl);

        Assertions.assertFalse(list.isEmpty(), "Список транзакцій не повинен бути порожнім");
        Transaction first = list.get(0);
        Assertions.assertNotNull(first.getDate(), "Дата першої транзакції не повинна бути null");
        Assertions.assertNotNull(first.getDescription(), "Опис не повинен бути null");
    }
}

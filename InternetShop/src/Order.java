import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private static int orderCounter = 1;

    private int orderNumber;
    private List<Product> products;
    private double totalPrice;
    private String status;
    private LocalDateTime orderDate;

    public Order(Cart cart) {
        this.orderNumber = orderCounter++;
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
        this.orderDate = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Геттери
    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // Метод для виведення інформації про замовлення
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        StringBuilder sb = new StringBuilder();

        sb.append("==============================\n");
        sb.append("ЗАМОВЛЕННЯ №").append(orderNumber).append("\n");
        sb.append("==============================\n");
        sb.append("Дата замовлення: ").append(orderDate.format(formatter)).append("\n");
        sb.append("Статус: ").append(status).append("\n\n");
        sb.append("Товари:\n");

        for (int i = 0; i < products.size(); i++) {
            sb.append((i + 1)).append(". ").append(products.get(i).toString()).append("\n");
        }

        sb.append("\nЗагальна вартість: ").append(String.format("%.2f", totalPrice)).append(" грн\n");
        sb.append("==============================");

        return sb.toString();
    }

    public String toShortString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "Замовлення №" + orderNumber + " від " + orderDate.format(formatter) +
                " | Сума: " + String.format("%.2f", totalPrice) + " грн | Статус: " + status;
    }
}
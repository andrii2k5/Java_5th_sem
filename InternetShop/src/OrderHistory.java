import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Замовлення №" + order.getOrderNumber() + " додано до історії!");
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Order getOrderByNumber(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    public boolean updateOrderStatus(int orderNumber, String newStatus) {
        Order order = getOrderByNumber(orderNumber);
        if (order != null) {
            order.setStatus(newStatus);
            System.out.println("Статус замовлення №" + orderNumber + " змінено на '" + newStatus + "'");
            return true;
        } else {
            System.out.println("Замовлення №" + orderNumber + " не знайдено!");
            return false;
        }
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public int getOrderCount() {
        return orders.size();
    }

    public double getTotalSales() {
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    // Метод для виведення інформації про всі замовлення
    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return "Історія замовлень порожня";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("==============================\n");
        sb.append("ІСТОРІЯ ЗАМОВЛЕНЬ\n");
        sb.append("==============================\n");
        sb.append("Всього замовлень: ").append(orders.size()).append("\n");
        sb.append("Загальна сума: ").append(String.format("%.2f", getTotalSales())).append(" грн\n\n");

        for (Order order : orders) {
            sb.append(order.toShortString()).append("\n");
        }

        sb.append("==============================");
        return sb.toString();
    }
}
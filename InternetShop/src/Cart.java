import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Товар '" + product.getName() + "' додано до кошика!");
    }

    public void removeProduct(Product product) {
        if (products.remove(product)) {
            System.out.println("Товар '" + product.getName() + "' видалено з кошика!");
        } else {
            System.out.println("Товар не знайдено в кошику!");
        }
    }

    public boolean removeProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                products.remove(product);
                System.out.println("Товар '" + product.getName() + "' видалено з кошика!");
                return true;
            }
        }
        System.out.println("Товар з ID " + productId + " не знайдено в кошику!");
        return false;
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void clear() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    // Метод для виведення інформації про всі товари у кошику
    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній";
        }

        StringBuilder sb = new StringBuilder("Кошик містить:\n");
        for (int i = 0; i < products.size(); i++) {
            sb.append((i + 1)).append(". ").append(products.get(i).toString()).append("\n");
        }
        sb.append("Загальна вартість: ").append(String.format("%.2f", getTotalPrice())).append(" грн");
        return sb.toString();
    }
}
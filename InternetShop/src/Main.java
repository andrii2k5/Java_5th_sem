import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Статичні змінні для зберігання товарів та історії замовлень
    private static List<Product> allProducts = new ArrayList<>();
    private static OrderHistory orderHistory = new OrderHistory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ініціалізація товарів та категорій
        initializeProducts();

        Cart cart = new Cart();

        System.out.println("=== ЛАСКАВО ПРОСИМО ДО ІНТЕРНЕТ-МАГАЗИНУ ===");

        while (true) {
            showMainMenu();

            int choice = getIntInput(scanner, "Ваш вибір: ");

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    searchProducts(scanner);
                    break;
                case 3:
                    addProductToCart(scanner, cart);
                    break;
                case 4:
                    System.out.println("\n" + cart);
                    break;
                case 5:
                    removeProductFromCart(scanner, cart);
                    break;
                case 6:
                    makeOrder(cart);
                    break;
                case 7:
                    showOrderHistory();
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }

            System.out.println("\nНатисніть Enter для продовження...");
            scanner.nextLine();
        }
    }

    private static void initializeProducts() {
        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");
        Category computers = new Category(4, "Комп'ютери");
        Category gaming = new Category(5, "Ігри та розваги");

        // Створення товарів
        allProducts.add(new Product(1, "Ноутбук ASUS", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", computers));
        allProducts.add(new Product(2, "iPhone 15", 32999.50, "Смартфон з великим екраном та високою автономністю", smartphones));
        allProducts.add(new Product(3, "AirPods Pro", 8499.00, "Бездротові навушники з шумозаглушенням", accessories));
        allProducts.add(new Product(4, "Samsung Galaxy S24", 28999.00, "Потужний Android смартфон з чудовою камерою", smartphones));
        allProducts.add(new Product(5, "MacBook Air", 42999.00, "Легкий та потужний ноутбук від Apple", computers));
        allProducts.add(new Product(6, "PlayStation 5", 18999.00, "Ігрова консоль нового покоління", gaming));
        allProducts.add(new Product(7, "Планшет iPad", 15999.00, "Універсальний планшет для роботи та розваг", electronics));
        allProducts.add(new Product(8, "Мишка Logitech", 1299.00, "Безпровідна мишка для комп'ютера", accessories));
    }

    private static void showMainMenu() {
        System.out.println("\n==============================");
        System.out.println("         ГОЛОВНЕ МЕНЮ         ");
        System.out.println("==============================");
        System.out.println("1 - Переглянути список товарів");
        System.out.println("2 - Пошук товарів");
        System.out.println("3 - Додати товар до кошика");
        System.out.println("4 - Переглянути кошик");
        System.out.println("5 - Видалити товар з кошика");
        System.out.println("6 - Зробити замовлення");
        System.out.println("7 - Переглянути історію замовлень");
        System.out.println("0 - Вийти");
        System.out.println("==============================");
    }

    private static void showAllProducts() {
        System.out.println("\n=== КАТАЛОГ ТОВАРІВ ===");
        if (allProducts.isEmpty()) {
            System.out.println("Товари відсутні");
            return;
        }

        for (Product product : allProducts) {
            System.out.println(product);
        }
    }

    private static void searchProducts(Scanner scanner) {
        System.out.println("\n=== ПОШУК ТОВАРІВ ===");
        System.out.println("1 - Пошук за назвою");
        System.out.println("2 - Пошук за категорією");

        int searchType = getIntInput(scanner, "Оберіть тип пошуку: ");

        List<Product> searchResults = new ArrayList<>();

        switch (searchType) {
            case 1:
                System.out.print("Введіть назву товару (або частину назви): ");
                String searchName = scanner.nextLine().toLowerCase();

                for (Product product : allProducts) {
                    if (product.getName().toLowerCase().contains(searchName)) {
                        searchResults.add(product);
                    }
                }
                break;

            case 2:
                System.out.print("Введіть назву категорії: ");
                String searchCategory = scanner.nextLine().toLowerCase();

                for (Product product : allProducts) {
                    if (product.getCategory().getName().toLowerCase().contains(searchCategory)) {
                        searchResults.add(product);
                    }
                }
                break;

            default:
                System.out.println("Неправильний вибір!");
                return;
        }

        System.out.println("\n=== РЕЗУЛЬТАТИ ПОШУКУ ===");
        if (searchResults.isEmpty()) {
            System.out.println("Товари не знайдені");
        } else {
            System.out.println("Знайдено товарів: " + searchResults.size());
            for (Product product : searchResults) {
                System.out.println(product);
            }
        }
    }

    private static void addProductToCart(Scanner scanner, Cart cart) {
        System.out.println("\n=== ДОДАВАННЯ ТОВАРУ ДО КОШИКА ===");
        showAllProducts();

        int productId = getIntInput(scanner, "Введіть ID товару для додавання до кошика: ");

        Product productToAdd = findProductById(productId);
        if (productToAdd != null) {
            cart.addProduct(productToAdd);
        } else {
            System.out.println("Товар з ID " + productId + " не знайдено!");
        }
    }

    private static void removeProductFromCart(Scanner scanner, Cart cart) {
        System.out.println("\n=== ВИДАЛЕННЯ ТОВАРУ З КОШИКА ===");

        if (cart.isEmpty()) {
            System.out.println("Кошик порожній!");
            return;
        }

        System.out.println("Поточний вміст кошика:");
        System.out.println(cart);

        int productId = getIntInput(scanner, "\nВведіть ID товару для видалення з кошика: ");
        cart.removeProductById(productId);
    }

    private static void makeOrder(Cart cart) {
        System.out.println("\n=== ОФОРМЛЕННЯ ЗАМОВЛЕННЯ ===");

        if (cart.isEmpty()) {
            System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
            return;
        }

        System.out.println("Ваше замовлення:");
        System.out.println(cart);

        Order order = new Order(cart);
        orderHistory.addOrder(order);

        System.out.println("\n✅ ЗАМОВЛЕННЯ УСПІШНО ОФОРМЛЕНО! ✅");
        System.out.println(order);

        cart.clear();
        System.out.println("\nКошик очищено.");
    }

    private static void showOrderHistory() {
        System.out.println("\n=== ІСТОРІЯ ЗАМОВЛЕНЬ ===");

        if (orderHistory.isEmpty()) {
            System.out.println("Історія замовлень порожня.");
            return;
        }

        System.out.println(orderHistory);

        // Додаткові опції для роботи з історією
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nДодаткові дії:");
        System.out.println("1 - Переглянути детальну інформацію про замовлення");
        System.out.println("2 - Змінити статус замовлення");
        System.out.println("0 - Повернутися до головного меню");

        int choice = getIntInput(scanner, "Ваш вибір: ");

        switch (choice) {
            case 1:
                showOrderDetails(scanner);
                break;
            case 2:
                changeOrderStatus(scanner);
                break;
            case 0:
                return;
        }
    }

    private static void showOrderDetails(Scanner scanner) {
        int orderNumber = getIntInput(scanner, "Введіть номер замовлення: ");
        Order order = orderHistory.getOrderByNumber(orderNumber);

        if (order != null) {
            System.out.println("\n" + order);
        } else {
            System.out.println("Замовлення з номером " + orderNumber + " не знайдено!");
        }
    }

    private static void changeOrderStatus(Scanner scanner) {
        int orderNumber = getIntInput(scanner, "Введіть номер замовлення: ");

        System.out.println("Доступні статуси:");
        System.out.println("1 - Нове");
        System.out.println("2 - Обробляється");
        System.out.println("3 - Відправлено");
        System.out.println("4 - Доставлено");
        System.out.println("5 - Скасовано");

        int statusChoice = getIntInput(scanner, "Оберіть новий статус: ");
        String newStatus;

        switch (statusChoice) {
            case 1:
                newStatus = "Нове";
                break;
            case 2:
                newStatus = "Обробляється";
                break;
            case 3:
                newStatus = "Відправлено";
                break;
            case 4:
                newStatus = "Доставлено";
                break;
            case 5:
                newStatus = "Скасовано";
                break;
            default:
                System.out.println("Неправильний вибір статусу!");
                return;
        }

        orderHistory.updateOrderStatus(orderNumber, newStatus);
    }

    // Допоміжний метод для безпечного введення цілих чисел
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка! Введіть коректне число.");
            }
        }
    }

    // Допоміжний метод для пошуку товару за ID
    private static Product findProductById(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
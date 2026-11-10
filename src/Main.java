import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderManager manager = new OrderManager();

        System.out.println("=== Food Ordering System ===");

        String addAnotherCustomer;
        do {
            System.out.println("\n--- Entering Customer Order ---");
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Customer ID: ");
            String idInput = scanner.nextLine(); // Mendukung String atau angka
            System.out.print("Enter Order Code: ");
            String orderCode = scanner.nextLine();

            Customer<String> customer = new Customer<>(name, idInput);
            FoodOrder<String> order = new FoodOrder<>(orderCode, customer);

            String addAnotherItem;
            do {
                System.out.print("Enter Menu Name: ");
                String menuName = scanner.nextLine();

                System.out.println("Select Food Category:");
                System.out.println("1. FOOD");
                System.out.println("2. BEVERAGE");
                System.out.println("3. DESSERT");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // membersihkan buffer

                FoodCategory category;
                switch (choice) {
                    case 1: category = FoodCategory.FOOD; break;
                    case 2: category = FoodCategory.BEVERAGE; break;
                    case 3: category = FoodCategory.DESSERT; break;
                    default: category = FoodCategory.FOOD; break;
                }

                OrderItem item = new OrderItem(menuName, category, quantity);
                order.addItem(item);

                System.out.print("Add another item to this order? (y/n): ");
                addAnotherItem = scanner.nextLine();
            } while (addAnotherItem.equalsIgnoreCase("y"));

            manager.addOrder(order);

            System.out.print("Add another customer order? (y/n): ");
            addAnotherCustomer = scanner.nextLine();
        } while (addAnotherCustomer.equalsIgnoreCase("y"));

        // Menampilkan seluruh pesanan aktif
        manager.displayAllOrders();

        scanner.close();
    }
}
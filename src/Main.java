import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderManager manager = new OrderManager();
        int choice;

        do {
            System.out.println("\n=== Food Ordering System ===");
            System.out.println("1. Add Customer Order");
            System.out.println("2. Display Active Orders");
            System.out.println("3. Display Order Queue");
            System.out.println("4. Process Next Order");
            System.out.println("5. Undo Last Transaction");
            System.out.println("6. Display Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose menu: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // membersihkan buffer

            switch (choice) {
                case 1:
                    System.out.println("\n--- Entering Customer Order ---");
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Customer ID: ");
                    String idInput = scanner.nextLine();

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
                        int catChoice = scanner.nextInt();
                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // membersihkan buffer

                        FoodCategory category;
                        switch (catChoice) {
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
                    break;

                case 2:
                    manager.displayAllOrders();
                    break;

                case 3:
                    manager.displayOrderQueue();
                    break;

                case 4:
                    manager.processNextOrder();
                    break;

                case 5:
                    manager.undoLastTransaction();
                    break;

                case 6:
                    manager.displayTransactionHistory();
                    break;

                case 7:
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose between 1-7.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
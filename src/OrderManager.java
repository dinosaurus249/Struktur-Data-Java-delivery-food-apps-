import java.util.ArrayList;

public class OrderManager {
    private ArrayList<FoodOrder<?>> activeOrders;

    public OrderManager() {
        this.activeOrders = new ArrayList<>();
    }

    public void addOrder(FoodOrder<?> order) {
        activeOrders.add(order);
    }

    public void displayAllOrders() {
        System.out.println("\n=================================");
        System.out.println("=== ALL ACTIVE ORDERS ===");
        System.out.println("=================================");
        for (FoodOrder<?> order : activeOrders) {
            System.out.println("Order Code          : " + order.getOrderCode());
            System.out.println("Customer Name       : " + order.getCustomer().getName());
            System.out.println("Customer ID         : " + order.getCustomer().getId());
            System.out.println("Customer ID Type    : " + order.getCustomer().getId().getClass().getSimpleName());
            System.out.println("Ordered Items:");
            for (OrderItem item : order.getOrderedItems()) {
                System.out.println("  - " + item.getMenuName() + " x" + item.getQuantity() + " (" + item.getCategory() + ")");
            }
            System.out.println();
        }
    }
}
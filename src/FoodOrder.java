import java.util.LinkedList;

public class FoodOrder<T> {
    private String orderCode;
    private Customer<T> customer;
    private LinkedList<OrderItem> orderedItems;

    public FoodOrder(String orderCode, Customer<T> customer) {
        this.orderCode = orderCode;
        this.customer = customer;
        this.orderedItems = new LinkedList<>();
    }

    public void addItem(OrderItem item) {
        orderedItems.add(item);
    }

    public String getOrderCode() {
        return orderCode;
    }

    public Customer<T> getCustomer() {
        return customer;
    }

    public LinkedList<OrderItem> getOrderedItems() {
        return orderedItems;
    }
}
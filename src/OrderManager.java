import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class OrderManager {
    private ArrayList<FoodOrder<?>> activeOrders;
    private Queue<FoodOrder<?>> orderQueue;
    private Stack<FoodOrder<?>> transactionHistory;

    public OrderManager() {
        this.activeOrders = new ArrayList<>();
        this.orderQueue = new LinkedList<>();
        this.transactionHistory = new Stack<>();
    }

    public void addOrder(FoodOrder<?> order) {
        activeOrders.add(order);
        orderQueue.add(order); // Masuk ke antrean pemrosesan (FIFO)
        System.out.println("Order added successfully.");
        System.out.println("Order " + order.getOrderCode() + " is waiting in the processing queue.");
    }

    public void displayAllOrders() {
        if (activeOrders.isEmpty()) {
            System.out.println("\nNo active orders.");
            return;
        }
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

    // b. Menampilkan antrean pesanan saat ini (Read Queue)
    public void displayOrderQueue() {
        System.out.println("\n=== ORDER PROCESSING QUEUE ===");
        if (orderQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            int i = 1;
            for (FoodOrder<?> order : orderQueue) {
                System.out.println(i + ". " + order.getOrderCode() + " - " + order.getCustomer().getName());
                i++;
            }
        }
    }

    // c & d. Memproses pesanan berdasar FIFO dan menghapusnya dari active orders
    public void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("\nNo orders waiting in the queue.");
            return;
        }

        FoodOrder<?> processedOrder = orderQueue.poll(); // Ambil dari antrean depan
        activeOrders.remove(processedOrder); // Hapus dari daftar pesanan aktif
        transactionHistory.push(processedOrder); // Masuk ke Stack riwayat transaksi (LIFO)

        System.out.println("\n=== PROCESSING ORDER ===");
        System.out.println("Processing order: " + processedOrder.getOrderCode());
        System.out.println("Customer: " + processedOrder.getCustomer().getName());
        System.out.println("Order processed successfully.");
    }

    // c. Membatalkan transaksi terakhir (Undo / LIFO Stack)
    public void undoLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("\nNo transaction history to undo.");
            return;
        }

        FoodOrder<?> canceledOrder = transactionHistory.pop(); // Ambil dari Stack teratas
        activeOrders.add(canceledOrder); // Kembalikan ke active orders

        // Kembalikan ke posisi depan antrean (atau tambahkan kembali ke antrean)
        LinkedList<FoodOrder<?>> tempQueue = new LinkedList<>(orderQueue);
        orderQueue.clear();
        orderQueue.add(canceledOrder);
        orderQueue.addAll(tempQueue);

        System.out.println("\n=== UNDO TRANSACTION ===");
        System.out.println("Transaction for order " + canceledOrder.getOrderCode() + " has been undone and returned to queue/active orders.");
    }

    // b. Menampilkan riwayat pesanan yang telah diproses (Stack)
    public void displayTransactionHistory() {
        System.out.println("\n=== TRANSACTION HISTORY (STACK) ===");
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
        } else {
            int i = 1;
            for (FoodOrder<?> order : transactionHistory) {
                System.out.println(i + ". Order Code: " + order.getOrderCode() + " | Customer: " + order.getCustomer().getName());
                i++;
            }
        }
    }
}
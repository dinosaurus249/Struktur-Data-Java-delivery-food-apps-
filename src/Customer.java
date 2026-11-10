// Generic Class dengan tipe parameter T untuk ID Pelanggan
public class Customer<T> {
    private String name;
    private T id;

    public Customer(String name, T id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public T getId() {
        return id;
    }
}
package diaz;

public class SalesItem {
    private String name;
    private double price;
    private int quantity;

    public SalesItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f x %d = $%.2f", name, price, quantity, getTotal());
    }
}


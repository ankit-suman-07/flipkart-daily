package src.model;

public class Item {
    private String brand;
    private String category;
    private int price;
    private int quantity;

    public Item(String brand, String category, int price, int quantity) {
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public void addQuantity(int qty) {
        this.quantity += qty;
    }

    public String getBrand() { return brand; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return String.format("%s %s %d %d", brand, category, price, quantity);
    }
}

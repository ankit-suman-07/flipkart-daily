package src.model;

import java.util.Objects;

public class Item {
    private final String brand;
    private final String category;
    private final int price;
    private int quantity;

    public Item(String brand, String category, int price) {
        if (brand == null || category == null || brand.isEmpty() || category.isEmpty())
            throw new IllegalArgumentException("Brand and Category must be non-empty");
        if (price < 0) throw new IllegalArgumentException("Price must be >= 0");

        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = 0;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity to add must be > 0");
        this.quantity += qty;
    }

    @Override
    public String toString() {
        return brand + ", " + category + ", " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return brand.equalsIgnoreCase(item.brand) &&
                category.equalsIgnoreCase(item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand.toLowerCase(), category.toLowerCase());
    }
}

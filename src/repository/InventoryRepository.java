package src.repository;

import src.model.Item;

import java.util.*;

public class InventoryRepository {
    private final Map<String, Item> inventory = new HashMap<>();

    private String getKey(String brand, String category) {
        return brand.toLowerCase() + "#" + category.toLowerCase();
    }

    public Optional<Item> get(String brand, String category) {
        return Optional.ofNullable(inventory.get(getKey(brand, category)));
    }

    public void save(Item item) {
        inventory.put(getKey(item.getBrand(), item.getCategory()), item);
    }

    public Collection<Item> getAll() {
        return inventory.values();
    }
}

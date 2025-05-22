package src.repository;

import src.model.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InventoryRepository {
    private final Map<String, Item> inventoryMap = new HashMap<>();

    public void save(Item item) {
        String key = item.getBrand() + "#" + item.getCategory();
        inventoryMap.put(key, item);
    }

    public Optional<Item> get(String brand, String category) {
        return Optional.ofNullable(inventoryMap.get(brand + "#" + category));
    }

    public Collection<Item> getAll() {
        return inventoryMap.values();
    }
}

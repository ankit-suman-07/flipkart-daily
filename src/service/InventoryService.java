package src.service;

import src.model.Item;
import src.pojo.SearchRequest;
import src.repository.InventoryRepository;
import src.utils.FilterUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void addItem(String brand, String category, int price) {
        repository.save(new Item(brand, category, price, 0));
    }

    public void addInventory(String brand, String category, int quantity) {
        repository.get(brand, category).ifPresent(item -> item.addQuantity(quantity));
    }

    public List<Item> searchItems(SearchRequest request) {
        return FilterUtils.filterAndSort(repository.getAll(), request);
    }

    public void printInventory() {
        System.out.println("\nInventory :");
        Map<String, Map<String, Integer>> result = new TreeMap<>();
        for (Item item : repository.getAll()) {
            result.putIfAbsent(item.getBrand(), new TreeMap<>());
            result.get(item.getBrand()).put(item.getCategory(), item.getQuantity());
        }
        for (String brand : result.keySet()) {
            for (String category : result.get(brand).keySet()) {
                System.out.println(brand + " -> " + category + " -> " + result.get(brand).get(category));
            }
        }
    }


}

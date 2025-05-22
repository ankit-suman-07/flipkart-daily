package src.service;

import src.model.Item;
import src.pojo.SearchRequest;
import src.repository.InventoryRepository;
import src.utils.FilterUtils;

import java.util.*;

public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void addItem(String brand, String category, int price) {
        if (brand == null || category == null || brand.isEmpty() || category.isEmpty()) {
            System.out.println("Invalid brand or category");
            return;
        }
        if (price < 0) {
            System.out.println("Price must be >= 0");
            return;
        }

        if (repository.get(brand, category).isPresent()) {
            System.out.println("Item already exists: " + brand + ", " + category);
            return;
        }

        repository.save(new Item(brand, category, price));
        System.out.println("AddItem(" + brand + ", " + category + ", " + price + ")");
    }

    public void addInventory(String brand, String category, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be > 0");
            return;
        }
        Optional<Item> optionalItem = repository.get(brand, category);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.addQuantity(quantity);
            System.out.println("AddInventory(" + brand + ", " + category + ", " + quantity + ")");
        } else {
            System.out.println("Item not found: " + brand + ", " + category);
        }
    }

    public List<Item> searchItems(SearchRequest request) {
        List<Item> results = FilterUtils.filterAndSort(repository.getAll(), request);
        if (results.isEmpty()) {
            System.out.println("No items found for given criteria");
        } else {
            for (Item item : results) {
                System.out.println(item);
            }
        }
        return results;
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

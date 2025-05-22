package src.service;

import src.model.Item;
import src.pojo.SearchRequest;
import src.repository.InventoryRepository;
import src.utils.FilterUtils;

import java.util.List;

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
}

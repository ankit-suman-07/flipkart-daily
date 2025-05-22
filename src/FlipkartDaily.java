package src;

import src.model.Item;
import src.pojo.SearchRequest;
import src.repository.InventoryRepository;
import src.service.InventoryService;

import java.util.Arrays;
import java.util.List;

public class FlipkartDaily {
    public static void main(String[] args) {

        InventoryService service = new InventoryService(new InventoryRepository());

        // Add Items
        service.addItem("Amul", "Milk", 100);
        service.addItem("Amul", "Curd", 50);
        service.addItem("Nestle", "Milk", 60);
        service.addItem("Nestle", "Curd", 90);

        // Add Inventory
        service.addInventory("Amul", "Milk", 10);
        service.addInventory("Nestle", "Milk", 5);
        service.addInventory("Nestle", "Curd", 10);
        service.addInventory("Amul", "Milk", 10);
        service.addInventory("Amul", "Curd", 5);

        SearchRequest request = new SearchRequest();
        request.setCategory(Arrays.asList("Milk"));
        request.setOrderBy("price");
        request.setAsc(false);

//        List<Item> results = service.searchItems(request);
//        results.forEach(System.out::println);
        service.printInventory();
    }

}


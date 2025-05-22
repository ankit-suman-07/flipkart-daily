package src;

import src.pojo.SearchRequest;
import src.repository.InventoryRepository;
import src.service.InventoryService;

import java.util.*;

public class FlipkartDaily {
    public static void main(String[] args) {
        InventoryRepository repo = new InventoryRepository();
        InventoryService service = new InventoryService(repo);

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

        // Print Inventory summary
        service.printInventory();

        System.out.println();

        // Search with brand = Nestle
        Map<String, List<String>> filters1 = new HashMap<>();
        filters1.put("brand", List.of("Nestle"));
        SearchRequest req1 = new SearchRequest(filters1, null, "price", true);
        System.out.println("SearchItems(\"brand\"=[\"Nestle\"])");
        service.searchItems(req1).forEach(item -> System.out.println(item));

        System.out.println();

        // Search with category = Milk
        Map<String, List<String>> filters2 = new HashMap<>();
        filters2.put("category", List.of("Milk"));
        SearchRequest req2 = new SearchRequest(filters2, null, "price", true);
        System.out.println("SearchItems(\"category\"=[\"Milk\"])");
        service.searchItems(req2).forEach(item -> System.out.println(item));

        System.out.println();

        // Search with category = Milk, Order_By = price desc
        SearchRequest req3 = new SearchRequest(filters2, null, "price", false);
        System.out.println("SearchItems(\"category\"=[\"Milk\"], Order_By=[Price,desc])");
        service.searchItems(req3).forEach(item -> System.out.println(item));

        System.out.println();

        // Search with price range [70, 100]
        int[] priceRange = new int[]{70, 100};
        SearchRequest req4 = new SearchRequest(new HashMap<>(), priceRange, "price", true);
        System.out.println("SearchItems(\"price\"=[70, 100])");
        service.searchItems(req4).forEach(item -> System.out.println(item));

        System.out.println();

        // Search with category = Milk, price range [70,100], order by price desc
        SearchRequest req5 = new SearchRequest(filters2, priceRange, "price", false);
        System.out.println("SearchItems( [ \"category\"=[\"Milk\"],  \"price\"=[70, 100] ],  Order_By=[Price,desc] )");
        service.searchItems(req5).forEach(item -> System.out.println(item));
    }
}

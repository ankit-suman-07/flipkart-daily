package src.utils;

import src.model.Item;
import src.pojo.SearchRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FilterUtils {


    public static List<Item> filterAndSort(Iterable<Item> items, SearchRequest request) {
        Map<String, List<String>> filters = request.getFilters();
        int[] priceRange = request.getPriceRange();
        String orderBy = request.getOrderBy();
        boolean asc = request.isAsc();

        return StreamSupport.stream(items.spliterator(), false)
                .filter(item -> {
                    if (filters != null) {
                        if (filters.containsKey("brand") &&
                                !filters.get("brand").stream().anyMatch(b -> b.equalsIgnoreCase(item.getBrand()))) {
                            return false;
                        }
                        if (filters.containsKey("category") &&
                                !filters.get("category").stream().anyMatch(c -> c.equalsIgnoreCase(item.getCategory()))) {
                            return false;
                        }
                    }

                    if (priceRange != null) {
                        int from = priceRange[0];
                        int to = priceRange[1];
                        if ((from != -1 && item.getPrice() < from) || (to != -1 && item.getPrice() > to)) {
                            return false;
                        }
                    }
                    return true;
                })
                .sorted(getComparator(orderBy, asc))
                .collect(Collectors.toList());
    }


    private static Comparator<Item> getComparator(String orderBy, boolean asc) {
        Comparator<Item> comparator;

        if ("price".equalsIgnoreCase(orderBy)) {
            comparator = Comparator.comparingInt(Item::getPrice);
        } else if ("quantity".equalsIgnoreCase(orderBy) || "itemqty".equalsIgnoreCase(orderBy)) {
            comparator = Comparator.comparingInt(Item::getQuantity);
        } else {
            comparator = Comparator.comparing(Item::getBrand).thenComparing(Item::getCategory);
        }

        return asc ? comparator : comparator.reversed();
    }
}

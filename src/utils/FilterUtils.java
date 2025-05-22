package src.utils;

import src.constants.SearchConstants;
import src.model.Item;
import src.pojo.SearchRequest;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterUtils {
    public static List<Item> filterAndSort(Collection<Item> items, SearchRequest req) {
        Predicate<Item> predicate = item -> true;

        if (req.getBrand() != null && !req.getBrand().isEmpty()) {
            predicate = predicate.and(item -> req.getBrand().contains(item.getBrand()));
        }

        if (req.getCategory() != null && !req.getCategory().isEmpty()) {
            predicate = predicate.and(item -> req.getCategory().contains(item.getCategory()));
        }

        if (req.getPriceFrom() != null) {
            predicate = predicate.and(item -> item.getPrice() >= req.getPriceFrom());
        }

        if (req.getPriceTo() != null) {
            predicate = predicate.and(item -> item.getPrice() <= req.getPriceTo());
        }

        Comparator<Item> comparator = Comparator.comparing(Item::getPrice);
        if (SearchConstants.ORDER_BY_QUANTITY.equals(req.getOrderBy())) {
            comparator = Comparator.comparing(Item::getQuantity);
        }

        if (!req.isAsc()) {
            comparator = comparator.reversed();
        }

        return items.stream().filter(predicate).sorted(comparator).collect(Collectors.toList());
    }
}

package src.pojo;

import src.model.Item;

import java.util.List;

public class SearchResponse {
    private List<Item> items;

    public SearchResponse(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}

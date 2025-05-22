package src.pojo;

import java.util.List;
import java.util.Map;

public class SearchRequest {
    private Map<String, List<String>> filters;
    private int[] priceRange;
    private String orderBy;
    private boolean asc;

    public SearchRequest() {
        this.filters = null;
        this.priceRange = null;
        this.orderBy = "price";
        this.asc = true;
    }

    public SearchRequest(Map<String, List<String>> filters, int[] priceRange, String orderBy, boolean asc) {
        this.filters = filters;
        this.priceRange = priceRange;
        this.orderBy = orderBy;
        this.asc = asc;
    }


    

    public Map<String, List<String>> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, List<String>> filters) {
        this.filters = filters;
    }

    public int[] getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int[] priceRange) {
        this.priceRange = priceRange;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }


}

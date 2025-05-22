package src.pojo;

import java.util.List;

public class SearchRequest {
    private List<String> brand;
    private List<String> category;
    private Integer priceFrom;
    private Integer priceTo;
    private String orderBy;
    private boolean asc;

    public List<String> getBrand() { return brand; }
    public void setBrand(List<String> brand) { this.brand = brand; }

    public List<String> getCategory() { return category; }
    public void setCategory(List<String> category) { this.category = category; }

    public Integer getPriceFrom() { return priceFrom; }
    public void setPriceFrom(Integer priceFrom) { this.priceFrom = priceFrom; }

    public Integer getPriceTo() { return priceTo; }
    public void setPriceTo(Integer priceTo) { this.priceTo = priceTo; }

    public String getOrderBy() { return orderBy; }
    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }

    public boolean isAsc() { return asc; }
    public void setAsc(boolean asc) { this.asc = asc; }
}

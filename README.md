# Flipkart Daily

### Description: 
Flipkart wants to build a product to deliver groceries and daily essentials by next morning. In the initial release we want to build a browsing feature for Users where they can search for items from inventory using some filters and sorting criterias. This will give them the idea of how rich the inventory is before they can go ahead and place an order.

### Features:
- Define items in the inventory along with the price. Category & brand defines the item.   [category → milk, bread / brand → Amul milk or Britannia bread]
  - AddItem(category, brand, price)
- Add items to inventory with available quantity for each of them
  - AddInventory(category, brand, quantity)
- Search items in inventory using one of the filters like.
  - Brand or Category (search can be on multiple categories / brands)
  - Price range (price From , price To) etc. (from and to price parameters can be optional)
Searching should be extensible to support more filters in future.
- Searched items / results can be ordered using any of the below criteria.
  - Items with lowest price first (this would be default criteria)
  - Items with highest price first
  - Items with least no of of quantity

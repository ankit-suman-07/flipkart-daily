package src.utils;

import src.pojo.SearchRequest;
import src.service.InventoryService;

import java.util.*;

public class InputHandler {
    private final InventoryService service;

    public InputHandler(InventoryService service) {
        this.service = service;
    }

    public void start() {
        System.out.println("Inventory Management Console");
        System.out.println("Available commands:");
        System.out.println("AddItem <brand> <category> <price>");
        System.out.println("AddInventory <brand> <category> <quantity>");
        System.out.println("SearchItems [filter=value ...] [order_by=<field> asc|desc]");
        System.out.println("PrintInventory");
        System.out.println("Exit");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;
            if (line.isEmpty()) continue;

            try {
                processCommand(line);
            } catch (Exception e) {
                System.out.println("Error processing command: " + e.getMessage());
            }
        }
        System.out.println("Exiting...");
    }

    private void processCommand(String line) {
        String[] tokens = line.split("\\s+");
        String cmd = tokens[0].toLowerCase();

        switch (cmd) {
            case "additem":
                if (tokens.length != 4) {
                    System.out.println("Usage: AddItem <brand> <category> <price>");
                    return;
                }
                String brand = tokens[1];
                String category = tokens[2];
                int price = parseInt(tokens[3], -1);
                if (price < 0) {
                    System.out.println("Price must be a non-negative integer");
                    return;
                }
                service.addItem(brand, category, price);
                break;

            case "addinventory":
                if (tokens.length != 4) {
                    System.out.println("Usage: AddInventory <brand> <category> <quantity>");
                    return;
                }
                brand = tokens[1];
                category = tokens[2];
                int quantity = parseInt(tokens[3], -1);
                if (quantity <= 0) {
                    System.out.println("Quantity must be a positive integer");
                    return;
                }
                service.addInventory(brand, category, quantity);
                break;

            case "searchitems":
                // Parse key=value filters and optional order_by & asc/desc
                Map<String, List<String>> filters = new HashMap<>();
                int[] priceRange = null;
                String orderBy = null;
                boolean asc = true;

                for (int i = 1; i < tokens.length; i++) {
                    String part = tokens[i];
                    if (part.contains("=")) {
                        String[] kv = part.split("=", 2);
                        String key = kv[0].toLowerCase();
                        String val = kv[1];

                        if (key.equals("brand") || key.equals("category")) {
                            List<String> values = Arrays.asList(val.split(","));
                            filters.put(key, values);
                        } else if (key.equals("price")) {
                            // price=from,to
                            String[] range = val.split(",");
                            if (range.length == 2) {
                                int from = parseInt(range[0], -1);
                                int to = parseInt(range[1], -1);
                                priceRange = new int[]{from, to};
                            }
                        } else if (key.equals("order_by")) {
                            orderBy = val.toLowerCase();
                            // Next token could be asc/desc
                            if (i + 1 < tokens.length) {
                                String nextToken = tokens[i + 1].toLowerCase();
                                if (nextToken.equals("asc") || nextToken.equals("desc")) {
                                    asc = nextToken.equals("asc");
                                    i++;
                                }
                            }
                        }
                    }
                }

                SearchRequest req = new SearchRequest(filters, priceRange, orderBy, asc);
                service.searchItems(req);
                break;

            case "printinventory":
                service.printInventory();
                break;

            default:
                System.out.println("Unknown command: " + cmd);
        }
    }

    private int parseInt(String str, int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}




package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {

    private final String name;

    private List<Product> products;

    private static final Map<String, Warehouse> cachedWarehouses = new HashMap<>();

    private Warehouse(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public static Warehouse getInstance() {
        return new Warehouse(null);
    }

    public static Warehouse getInstance(String warehouseID) {
       // String formattedName = makeFirstCharUppercase(name);
        Warehouse warehouse = cachedWarehouses.get(warehouseID);
        if ( warehouse == null) {
            warehouse = new Warehouse(warehouseID);
            cachedWarehouses.put(warehouseID, warehouse);
        }
        return warehouse;
    }

    public boolean isEmpty() {
        return false;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Product addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        Product product = new Product(uuid, name, category, price);
        products.add(product);
        return product;
    }

    public void updateProductPrice(UUID productID, BigDecimal newPrice) {
        Optional<Product> productById = getProductById(productID);
        Product oldProduct = productById.get();
        removeProduct(oldProduct.getUuid());
        Product newProduct = new Product(oldProduct.getUuid(), oldProduct.name(), oldProduct.category(), newPrice);
        products.add(newProduct);
    }

    public Optional<Product> getProductById(UUID uuid) {
        return products.stream().filter(e -> e.getUuid().equals(uuid)).findFirst();
    }

    public List<Product> getChangedProducts() {
        return List.of();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return Map.of();
    }

    public List<Product> getProductsBy(Category category) {
        return products.stream()
                .filter(e -> e.category().equals(category))
                .toList();
    }
    private void removeProduct(UUID productID) {
        products = products.stream().filter(e -> !e.getUuid().equals(productID)).toList();
    }
}

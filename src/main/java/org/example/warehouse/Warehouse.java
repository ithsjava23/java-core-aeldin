package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        Warehouse warehouse = cachedWarehouses.get(warehouseID);
        if ( warehouse == null) {
            warehouse = new Warehouse(warehouseID);
            cachedWarehouses.put(warehouseID, warehouse);
        } else {
            warehouse.products = new ArrayList<>();
        }
        return warehouse;
    }

    public boolean isEmpty() {
       return products.isEmpty();


    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Product addProduct(UUID uuid, String name, Category category, BigDecimal price) {


        if (uuid == null) {
            uuid = UUID.randomUUID();
        }

        if (getProductById(uuid).isPresent()) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        Product product = new Product(uuid, name, category, (price == null) ? BigDecimal.ZERO : price);

        if (product.name() == null || product.name().isBlank()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (product.category() == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }

        products.add(product);

        return product;
    }

    public void updateProductPrice(UUID productID, BigDecimal newPrice) {
        Optional<Product> productById = getProductById(productID);


        if (productById.isPresent()) {
            Product oldProduct = productById.get();
            removeProduct(oldProduct.getUuid());
            Product newProduct = new Product(oldProduct.getUuid(), oldProduct.name(), oldProduct.category(), newPrice);

            newProduct.setChanged(true);

            List<Product> updatedProducts = new ArrayList<>(products);
            updatedProducts.add(newProduct);
            products = updatedProducts;

        } else {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
    }

    public Optional<Product> getProductById(UUID uuid) {
        return products.stream()
                .filter(e -> e.getUuid().equals(uuid))
                .findFirst();
    }

    public List<Product> getChangedProducts() {
        return products.stream()
                .filter(Product::isChanged)
                .collect(Collectors.toList());
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public List<Product> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }
    private void removeProduct(UUID productID) {
        products = products.stream()
                .filter(e -> !e.getUuid().equals(productID))
                .toList();

    }

}



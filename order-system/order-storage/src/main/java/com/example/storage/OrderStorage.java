package com.example.storage;

import com.example.processing.Product;
import java.util.ArrayList;
import java.util.List;

public class OrderStorage {

    private final List<Product> products = new ArrayList<>();

    public void add(Product p) {
        products.add(p);
    }

    public List<Product> getAll() {
        return products;
    }
}

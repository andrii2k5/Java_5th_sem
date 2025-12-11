package com.example.processing;

import java.util.function.Consumer;

public class OrderProcessor<T extends Product> {

    private final T product;

    public OrderProcessor(T product) {
        this.product = product;
    }

    public void process(Consumer<T> handler) throws InvalidOrderException {
        if (product.getPrice() <= 0) {
            throw new InvalidOrderException("Ціна товару недійсна!");
        }

        handler.accept(product);
    }

    public void printProductInfo() {
        System.out.println("Обробка: " + product.getName() + ", ціна: " + product.getPrice());
    }
}

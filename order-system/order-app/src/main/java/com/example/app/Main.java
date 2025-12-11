package com.example.app;

import com.example.processing.*;
import com.example.storage.OrderStorage;
import com.example.threading.OrderThreadManager;
import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();

        Electronics phone = Electronics.builder()
                .name(faker.commerce().productName())
                .price(faker.number().randomDouble(2, 200, 1500))
                .build();

        Clothing tshirt = Clothing.builder()
                .name(faker.commerce().productName())
                .price(faker.number().randomDouble(2, 10, 100))
                .build();

        OrderStorage storage = new OrderStorage();

        storage.add(phone);
        storage.add(tshirt);

        OrderProcessor<Electronics> p1 = new OrderProcessor<>(phone);
        OrderProcessor<Clothing> p2 = new OrderProcessor<>(tshirt);

        OrderThreadManager threadManager = new OrderThreadManager();

        try {
            p1.process(product ->
                    System.out.println("Lambda: електроніка → " + product.getName())
            );

            p2.process(product ->
                    System.out.println("Lambda: одяг → " + product.getName())
            );

        } catch (InvalidOrderException e) {
            System.out.println("Помилка замовлення: " + e.getMessage());
        }

        threadManager.runInThread(p1);
        threadManager.runInThread(p2);

        System.out.println("Система запущена.");
    }
}

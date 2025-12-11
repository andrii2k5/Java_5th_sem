package com.example.processing;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Clothing extends Product {

    @Builder
    public Clothing(String name, double price) {
        setName(name);
        setPrice(price);
    }
}

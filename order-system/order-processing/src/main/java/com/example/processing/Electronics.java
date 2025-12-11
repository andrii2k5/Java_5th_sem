package com.example.processing;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Electronics extends Product {

    @Builder
    public Electronics(String name, double price) {
        setName(name);
        setPrice(price);
    }
}

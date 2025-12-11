package com.example.threading;

import com.example.processing.OrderProcessor;
import com.example.processing.Product;

public class OrderThreadManager {

    public <T extends Product> void runInThread(OrderProcessor<T> processor) {
        Runnable task = processor::printProductInfo; // Method reference!
        new Thread(task).start();
    }
}

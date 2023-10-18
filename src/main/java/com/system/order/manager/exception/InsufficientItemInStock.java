package com.system.order.manager.exception;

public class InsufficientItemInStock extends RuntimeException {

    public InsufficientItemInStock() {
        super("Not enough item in Stock");
    }

    public InsufficientItemInStock(String message) {
        super("Not enough item in Stock: " + message);
    }


}

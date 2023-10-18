package com.system.order.manager.exception;

public class OrderAttributeClosedException extends RuntimeException{

    public OrderAttributeClosedException() {
        super("Order already cosed");
    }

    public OrderAttributeClosedException(String message) {
        super("Order already cosed: " + message);
    }
}

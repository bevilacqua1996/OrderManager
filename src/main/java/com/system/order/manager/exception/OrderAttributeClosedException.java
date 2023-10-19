package com.system.order.manager.exception;

public class OrderAttributeClosedException extends RuntimeException{

    public OrderAttributeClosedException() {
        super("Order already closed");
    }

    public OrderAttributeClosedException(String message) {
        super("Order already closed: " + message);
    }
}

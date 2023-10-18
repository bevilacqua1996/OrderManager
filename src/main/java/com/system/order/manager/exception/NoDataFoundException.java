package com.system.order.manager.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("No Data found");
    }

    public NoDataFoundException(String message) {
        super("No Data found: " + message);
    }
}

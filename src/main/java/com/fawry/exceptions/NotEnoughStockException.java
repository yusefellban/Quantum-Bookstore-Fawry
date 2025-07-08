package com.fawry.exceptions;

public class NotEnoughStockException extends IllegalStateException {
    public NotEnoughStockException(String message) {
        super(message);
    }
}

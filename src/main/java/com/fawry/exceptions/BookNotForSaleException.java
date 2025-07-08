package com.fawry.exceptions;

public class BookNotForSaleException extends IllegalStateException {
    public BookNotForSaleException(String message) {
        super(message);
    }
}

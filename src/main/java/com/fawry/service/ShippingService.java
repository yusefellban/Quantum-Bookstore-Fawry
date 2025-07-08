package com.fawry.service;


import com.fawry.model.Book;
import com.fawry.model.PaperBook;

public class ShippingService implements NotificationService {
    @Override
    public void send(Book book, String recipient) {
        System.out.println("Quantum book store: Shipping PaperBook '" + book.getTitle() + "' to " + recipient);
    }
}
package com.fawry.service;

import com.fawry.model.Book;
import com.fawry.model.EBook;

public class MailService implements NotificationService {
    @Override
    public void send(Book book, String recipient) {
        System.out.println("Quantum book store: Sending EBook '" + book.getTitle() + "' to " + recipient);
    }

}
package com.fawry.controller;

import com.fawry.exceptions.BookNotForSaleException;
import com.fawry.exceptions.BookNotFoundException;
import com.fawry.model.Book;
import com.fawry.service.BookstoreService;

public class BookController {
    private final BookstoreService service;

    public BookController(BookstoreService service) {
        this.service = service;
    }

    public void addBook(Book book) {
        service.addBook(book);
    }

    public void removeOldBooks(int years) {
        service.removeOutdatedBooks(years);
    }

    public void buyBook(String isbn, int quantity, String email, String address) {
        try {
            double paid = service.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Paid total - " + paid);
        } catch (Exception e) {
           throw new BookNotForSaleException(e.getMessage());
        }
    }
}
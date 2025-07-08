package com.fawry.repo;


import com.fawry.model.Book;

import java.util.*;

public class BookRepository {
    private final Map<String, Book> inventory = new HashMap<>();

    public void addBook(Book book) {
        inventory.put(book.getIsbn(), book);
        System.out.println("Quantum book store: Book added - " + book.getTitle());
    }

    public Book getBookByIsbn(String isbn) {
        return inventory.get(isbn);
    }

    public void removeBook(String isbn) {
        inventory.remove(isbn);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(inventory.values());
    }
}
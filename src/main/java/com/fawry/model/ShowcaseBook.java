package com.fawry.model;


public class ShowcaseBook extends Book {
    public ShowcaseBook(String isbn, String title, int year, double price, String author) {
        super(isbn, title, year, price, author);
    }

    @Override
    public boolean isPurchasable() { return false; }

    @Override
    public void deliver(String email, String address) {
        System.out.println("Quantum book store: Showcase books can't be delivered.");
    }
}

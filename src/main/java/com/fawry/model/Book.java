package com.fawry.model;


public abstract class Book {
    protected String isbn;
    protected String title;
    protected int publicationYear;
    protected double price;
    protected String author;

    public Book(String isbn, String title, int publicationYear, double price, String author) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.author = author;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public double getPrice() { return price; }
    public String getAuthor() { return author; }

    public abstract boolean isPurchasable();
    public abstract void deliver(String email, String address);
}
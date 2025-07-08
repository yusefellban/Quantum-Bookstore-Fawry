package com.fawry.model;


import com.fawry.service.NotificationService;
import com.fawry.service.ShippingService;

public class PaperBook extends Book {
    private int stock;
    private final NotificationService notificationService;
    public PaperBook(String isbn, String title, int year, double price, String author, int stock, NotificationService notificationService) {
        super(isbn, title, year, price, author);
        this.stock = stock;
        this.notificationService = notificationService;
    }

    public int getStock() { return stock; }
    public void reduceStock(int quantity) { stock -= quantity; }

    @Override
    public boolean isPurchasable() { return stock > 0; }

    @Override
    public void deliver(String email, String address) {
        System.out.println("Quantum book store: Shipping to " + address);
        notificationService.send(this, address);

    }
}
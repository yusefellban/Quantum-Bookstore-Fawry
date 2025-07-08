package com.fawry.model;


import com.fawry.service.MailService;
import com.fawry.service.NotificationService;

public class EBook extends Book {
    private final String fileType;
    private final NotificationService notificationService;
    public EBook(String isbn, String title, int year, double price, String author, String fileType, NotificationService notificationService) {
        super(isbn, title, year, price, author);
        this.fileType = fileType;
        this.notificationService = notificationService;
    }

    public String getFileType() { return fileType; }

    @Override
    public boolean isPurchasable() { return true; }

    @Override
    public void deliver(String email, String address) {
        System.out.println("Quantum book store: Sending to email " + email);
        notificationService.send(this, address);
    }
}
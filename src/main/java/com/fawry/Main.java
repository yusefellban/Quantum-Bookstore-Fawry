package com.fawry;

import com.fawry.model.*;
import com.fawry.repo.BookRepository;
import com.fawry.service.*;
import com.fawry.controller.BookController;

public class Main {
    public static void main(String[] args) {
        BookRepository repo = new BookRepository();
        BookstoreService service = new BookstoreService(repo);
        BookController controller = getBookController(service);

        controller.buyBook("ISBN001", 2, "buyer@fawry.com", "123 Elm Street");
        controller.buyBook("ISBN002", 1, "reader@fawry.com", "");
        controller.buyBook("ISBN003", 1, "test@fawry.com", "Nan"); // Should show error

        controller.removeOldBooks(10);
    }

    private static BookController getBookController(BookstoreService service) {
        BookController controller = new BookController(service);

        NotificationService mailService = new MailService();
        NotificationService shippingService = new ShippingService();

        Book b1 = new PaperBook("ISBN001", "Java Basics", 2010, 100.0, "Yousef", 10,mailService);
        Book b2 = new EBook("ISBN002", "Clean Code", 2015, 50.0, "Robert C. Martin", "PDF",shippingService);
        Book b3 = new ShowcaseBook("ISBN003", "Exclusive Preview", 2020, 0.0, "Anonymous");

        controller.addBook(b1);
        controller.addBook(b2);
        controller.addBook(b3);
        return controller;
    }
}
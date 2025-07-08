package com.fawry.service;

import com.fawry.exceptions.BookNotForSaleException;
import com.fawry.exceptions.BookNotFoundException;
import com.fawry.exceptions.NotEnoughStockException;
import com.fawry.model.Book;
import com.fawry.model.PaperBook;
import com.fawry.repo.BookRepository;

import java.time.Year;
import java.util.List;

public class BookstoreService {
    private final BookRepository bookRepo;

    public BookstoreService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(Book book) {
        bookRepo.addBook(book);
    }

    public void removeOutdatedBooks(int maxYears) {
        List<Book> allBooks = bookRepo.getAllBooks();
        int currentYear = Year.now().getValue();
        for (Book book : allBooks) {
            if (currentYear - book.getPublicationYear() > maxYears) {
                bookRepo.removeBook(book.getIsbn());
                System.out.println("Quantum book store: Removed outdated book - " + book.getTitle());
            }
        }
    }

    public double buyBook(String isbn, int quantity, String email, String address) {
        Book book = bookRepo.getBookByIsbn(isbn);

        if (book == null) throw new BookNotFoundException("Quantum book store: Book not found.");
        if (!book.isPurchasable()) throw new BookNotForSaleException("Quantum book store: Book not for sale.");

        if (book instanceof PaperBook paperBook) {
            if (paperBook.getStock() < quantity)
                throw new NotEnoughStockException("Quantum book store: Not enough stock.");
            paperBook.reduceStock(quantity);
        }

        double totalPrice = book.getPrice() * quantity;
        book.deliver(email, address);
        System.out.println("Quantum book store: Payment completed - Amount: " + totalPrice);
        return totalPrice;
    }
}

package com.fawry.service;

import com.fawry.exceptions.BookNotForSaleException;
import com.fawry.exceptions.BookNotFoundException;
import com.fawry.exceptions.NotEnoughStockException;
import com.fawry.model.Book;
import com.fawry.model.PaperBook;
import com.fawry.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookstoreServiceTest {

    private BookRepository bookRepository;
    private BookstoreService bookstoreService;

    @BeforeEach
    void setup() {
        bookRepository = mock(BookRepository.class);
        bookstoreService = new BookstoreService(bookRepository);
    }

    @Test
    void testAddBook() {
        Book book = mock(Book.class);
        bookstoreService.addBook(book);
        verify(bookRepository).addBook(book);
    }

    @Test
    void testRemoveOutdatedBooks() {
        Book book1 = mock(Book.class);
        when(book1.getPublicationYear()).thenReturn(2000);
        when(book1.getIsbn()).thenReturn("isbn1");
        when(book1.getTitle()).thenReturn("Old Book");

        Book book2 = mock(Book.class);
        when(book2.getPublicationYear()).thenReturn(2024);

        when(bookRepository.getAllBooks()).thenReturn(List.of(book1, book2));

        bookstoreService.removeOutdatedBooks(10);

        verify(bookRepository).removeBook("isbn1");
        verify(bookRepository, never()).removeBook(null);
    }

    @Test
    void testBuyBook_NotFound() {
        when(bookRepository.getBookByIsbn("isbn123")).thenReturn(null);

        assertThrows(BookNotFoundException.class, () ->
                bookstoreService.buyBook("isbn123", 1, "test@example.com", "Some Address")
        );
    }

    @Test
    void testBuyBook_NotForSale() {
        Book book = mock(Book.class);
        when(book.isPurchasable()).thenReturn(false);
        when(bookRepository.getBookByIsbn("isbn123")).thenReturn(book);

        assertThrows(BookNotForSaleException.class, () ->
                bookstoreService.buyBook("isbn123", 1, "test@example.com", "Some Address")
        );
    }

    @Test
    void testBuyPaperBook_NotEnoughStock() {
        PaperBook paperBook = mock(PaperBook.class);
        when(paperBook.getStock()).thenReturn(1);
        when(paperBook.getPrice()).thenReturn(100.0);
        when(paperBook.isPurchasable()).thenReturn(true);
        when(bookRepository.getBookByIsbn("isbn123")).thenReturn(paperBook);

        assertThrows(NotEnoughStockException.class, () ->
                bookstoreService.buyBook("isbn123", 5, "test@example.com", "Some Address")
        );
    }

    @Test
    void testBuyPaperBook_Success() {
        PaperBook paperBook = mock(PaperBook.class);
        when(paperBook.getStock()).thenReturn(10);
        when(paperBook.getPrice()).thenReturn(50.0);
        when(paperBook.isPurchasable()).thenReturn(true);
        when(bookRepository.getBookByIsbn("isbn123")).thenReturn(paperBook);

        double result = bookstoreService.buyBook("isbn123", 2, "test@example.com", "Address");

        verify(paperBook).reduceStock(2);
        verify(paperBook).deliver("test@example.com", "Address");
        assertEquals(100.0, result);
    }
}

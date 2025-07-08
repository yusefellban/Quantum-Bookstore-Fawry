package com.fawry.service;

import com.fawry.model.Book;

public interface NotificationService
{
    void send(Book book, String recipient);
}

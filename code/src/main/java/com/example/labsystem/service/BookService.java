package com.example.labsystem.service;

import com.example.labsystem.pojo.Book;

import java.util.List;

public interface BookService {
    public void addBookList(Book book);

    List<Book> getBookList(Book book);

    void deleteBook(Integer id);

    void updateBookList(Book book);
}

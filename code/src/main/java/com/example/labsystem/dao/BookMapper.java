package com.example.labsystem.dao;

import com.example.labsystem.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public void addBookList(Book book);

    List<Book> getBookList(Book book);

    void deleteBook(Integer id);

    void updateBookList(Book book);
}

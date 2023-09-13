package com.timife.prodatabase.dao;

import com.timife.prodatabase.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);
}

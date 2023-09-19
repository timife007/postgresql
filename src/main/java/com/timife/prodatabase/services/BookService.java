package com.timife.prodatabase.services;

import com.timife.prodatabase.domain.entities.BookEntity;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);
}

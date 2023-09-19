package com.timife.prodatabase.services.impl;

import com.timife.prodatabase.domain.entities.BookEntity;
import com.timife.prodatabase.repositories.BookRepository;
import com.timife.prodatabase.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}

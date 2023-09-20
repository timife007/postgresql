package com.timife.prodatabase.controllers;

import com.timife.prodatabase.domain.dtos.BookDto;
import com.timife.prodatabase.domain.entities.BookEntity;
import com.timife.prodatabase.mappers.Mapper;
import com.timife.prodatabase.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BooksController {

    private Mapper<BookEntity, BookDto> bookMapper;

    private BookService bookService;

    public BooksController(Mapper<BookEntity, BookDto> mapper, BookService bookService) {
        this.bookMapper = mapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);

    }

    @GetMapping(path = "/books")
    public List<BookDto> listBooks(){
        List<BookEntity> books = bookService.findAll();
        return books.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }
}

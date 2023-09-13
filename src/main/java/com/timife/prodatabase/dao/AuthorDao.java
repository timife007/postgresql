package com.timife.prodatabase.dao;

import com.timife.prodatabase.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long id);
}

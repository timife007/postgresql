package com.timife.prodatabase.services.impl;

import com.timife.prodatabase.domain.entities.AuthorEntity;
import com.timife.prodatabase.repositories.AuthorRepository;
import com.timife.prodatabase.services.AuthorService;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }
}

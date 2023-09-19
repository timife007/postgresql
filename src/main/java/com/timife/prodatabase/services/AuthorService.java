package com.timife.prodatabase.services;

import com.timife.prodatabase.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();
}

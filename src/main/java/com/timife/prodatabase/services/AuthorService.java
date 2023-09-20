package com.timife.prodatabase.services;

import com.timife.prodatabase.domain.entities.AuthorEntity;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);
}

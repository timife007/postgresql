package com.timife.prodatabase.services;

import com.timife.prodatabase.domain.dtos.AuthorDto;
import com.timife.prodatabase.domain.entities.AuthorEntity;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity save(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);

    void deleteAll();
}

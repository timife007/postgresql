package com.timife.prodatabase.services;

import com.timife.prodatabase.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author);
}

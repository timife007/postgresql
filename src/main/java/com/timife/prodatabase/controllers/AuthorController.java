package com.timife.prodatabase.controllers;

import com.timife.prodatabase.domain.dtos.AuthorDto;
import com.timife.prodatabase.domain.entities.AuthorEntity;
import com.timife.prodatabase.mappers.Mapper;
import com.timife.prodatabase.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    private final Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService service, Mapper<AuthorEntity, AuthorDto> authorMapper){
        this.authorService = service;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto){
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return authorMapper.mapTo(savedAuthorEntity);
    }
}

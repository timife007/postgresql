package com.timife.prodatabase;

import com.timife.prodatabase.domain.dtos.AuthorDto;
import com.timife.prodatabase.domain.dtos.BookDto;
import com.timife.prodatabase.domain.entities.AuthorEntity;
import com.timife.prodatabase.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Timothy")
                .age(50).build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Ifeoluwa")
                .age(26).build();
    }
    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Ademola")
                .age(24).build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("978-1-2345-6789-01")
                .title("The shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }
    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("978-1-2345-6789-02")
                .title("Beyond the Horizon")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("978-1-2345-6789-03")
                .title("The Last Ember")
                .authorEntity(authorEntity)
                .build();
    }

    public static AuthorEntity createTestAuthorEntityA(){
        return AuthorEntity
                .builder()
                .id(1L)
                .name("Timothy")
                .age(50).build();
    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .id(1L)
                .name("Timothy")
                .age(50).build();
    }



    public static BookEntity createTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Last Ember")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Last Ember")
                .author(author)
                .build();
    }
}

package com.timife.prodatabase;

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
}

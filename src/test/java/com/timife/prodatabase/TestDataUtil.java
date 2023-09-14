package com.timife.prodatabase;

import com.timife.prodatabase.domain.Author;
import com.timife.prodatabase.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Timothy")
                .age(1).build();
    }

    public static Book createTestBook() {
        return Book.builder().isbn("978-1-2345-6789-0")
                .title("The shadow in the Attic")
                .authorId(1L)
                .build();
    }
}

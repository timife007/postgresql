package com.timife.prodatabase;

import com.timife.prodatabase.domain.Author;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Timothy")
                .age(1).build();
    }
}

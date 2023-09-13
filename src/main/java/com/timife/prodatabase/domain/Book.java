package com.timife.prodatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    private String isbn;
    private String title;
    private Long authorId  ;
}

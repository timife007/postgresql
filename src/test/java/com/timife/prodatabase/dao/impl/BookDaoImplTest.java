package com.timife.prodatabase.dao.impl;

import com.timife.prodatabase.dao.impl.BookDaoImpl;
import com.timife.prodatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = Book.builder().isbn("978-1-2345-6789-0")
                .title("The shadow in the Attic")
                .authorId(1L)
                .build();

        underTest.create(book);
        verify(jdbcTemplate).
                update(eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                        eq("978-1-2345-6789-0"),
                        eq("The shadow in the Attic"),
                        eq(1L));
    }
}

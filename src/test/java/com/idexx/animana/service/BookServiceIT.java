package com.idexx.animana.service;

import com.idexx.animana.model.BookResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookServiceIT {

    @Autowired
    private BookService bookService;

    @Test
    void testAlbumServiceSucess(){
        BookResult result = bookService.getBooks("shakespeare").block();
        assertThat(result.getItems().size()).isEqualTo(5);
    }
}

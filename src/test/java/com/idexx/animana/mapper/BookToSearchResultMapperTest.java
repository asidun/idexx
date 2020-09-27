package com.idexx.animana.mapper;

import com.idexx.animana.model.Book;
import com.idexx.animana.model.BookItem;
import com.idexx.animana.model.SearchResultDto;
import com.idexx.animana.model.SearchResultType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookToSearchResultMapperTest {

    @Test
    void testBookToSearchResultMapperSuccess(){

        Book book = Book.builder().authors(Arrays.asList("Author1", "Author2")).title("Title").build();
        List<BookItem> bookItemsList = Collections.singletonList(BookItem.builder().volumeInfo(book).build());
        List<SearchResultDto> resultDto = BookToSearchResultMapper.INSTANCE.bookItemsToSearchResultDtos(bookItemsList);

        assertThat(resultDto.get(0).getTitle()).isEqualTo("Title");
        assertThat(resultDto.get(0).getAuthor()).isEqualTo("Author1, Author2");
        assertThat(resultDto.get(0).getType()).isEqualTo(SearchResultType.BOOK);


    }
}

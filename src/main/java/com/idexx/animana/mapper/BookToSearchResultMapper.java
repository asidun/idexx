package com.idexx.animana.mapper;

import com.idexx.animana.model.BookItem;
import com.idexx.animana.model.SearchResultDto;
import com.idexx.animana.model.SearchResultType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BookToSearchResultMapper {
    BookToSearchResultMapper INSTANCE = Mappers.getMapper(BookToSearchResultMapper.class);
    String EMPTY_STRING = "";
    String DELIMITER = ", ";

    List<SearchResultDto> bookItemsToSearchResultDtos(List<BookItem> bookItems);

    default SearchResultDto bookItemToSearchResultDto(BookItem bookItem) {
        SearchResultDto searchResultDto = new SearchResultDto();

        Optional<List<String>> optionalAuthors = Optional.ofNullable(bookItem.getVolumeInfo().getAuthors());
        List<String> authors = optionalAuthors.orElse(Collections.singletonList(EMPTY_STRING));

        searchResultDto.setAuthor(String.join(DELIMITER, authors));
        searchResultDto.setTitle(bookItem.getVolumeInfo().getTitle());
        searchResultDto.setType(SearchResultType.BOOK);
        return searchResultDto;
    }
}

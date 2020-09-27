package com.idexx.animana.mapper;

import com.idexx.animana.model.Album;
import com.idexx.animana.model.SearchResultDto;
import com.idexx.animana.model.SearchResultType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumToSearchResultMapper {
    AlbumToSearchResultMapper INSTANCE = Mappers.getMapper(AlbumToSearchResultMapper.class);

    List<SearchResultDto> albumsToSearchResultDtos(List<Album> albums);

    default SearchResultDto albumToSearchResultDto(Album album){
        SearchResultDto searchResultDto = new SearchResultDto();
        searchResultDto.setAuthor(album.getArtistName());
        searchResultDto.setTitle(album.getCollectionName());
        searchResultDto.setType(SearchResultType.ALBUM);
        return searchResultDto;
    }
}

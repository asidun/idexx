package com.idexx.animana.mapper;

import com.idexx.animana.model.Album;
import com.idexx.animana.model.SearchResultDto;
import com.idexx.animana.model.SearchResultType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AlbumToSearchResultMapperTest {

    @Test
    void testAlbumToSearchResultMapperSuccess(){
        List<Album> albumList = Collections.singletonList(Album.builder().artistName("Artist").collectionName("Track").build());
        List<SearchResultDto> resultDto = AlbumToSearchResultMapper.INSTANCE.albumsToSearchResultDtos(albumList);

        assertThat(resultDto.get(0).getTitle()).isEqualTo("Track");
        assertThat(resultDto.get(0).getAuthor()).isEqualTo("Artist");
        assertThat(resultDto.get(0).getType()).isEqualTo(SearchResultType.ALBUM);


    }
}

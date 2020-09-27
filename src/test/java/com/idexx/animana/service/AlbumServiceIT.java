package com.idexx.animana.service;

import com.idexx.animana.model.AlbumResult;
import com.idexx.animana.util.AlbumJsonParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlbumServiceIT {

    @Autowired
    AlbumService albumService;

    @Autowired
    AlbumJsonParser albumJsonParser;

    @Test
    void testAlbumServiceSucess(){
        String jsonString = albumService.getAlbums("abba").block();
        AlbumResult result = albumJsonParser.parseJsonStringToAlbumResult(jsonString);
        assertThat(result.getResults().size()).isEqualTo(5);
    }
}

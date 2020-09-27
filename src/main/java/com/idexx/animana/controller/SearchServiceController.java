package com.idexx.animana.controller;

import com.idexx.animana.mapper.AlbumToSearchResultMapper;
import com.idexx.animana.mapper.BookToSearchResultMapper;
import com.idexx.animana.model.AlbumResult;
import com.idexx.animana.model.BookResult;
import com.idexx.animana.model.SearchResultDto;
import com.idexx.animana.service.AlbumService;
import com.idexx.animana.service.BookService;
import com.idexx.animana.util.AlbumJsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/search")
@Slf4j
public class SearchServiceController {

    private final BookService bookService;
    private final AlbumService albumService;
    private final AlbumToSearchResultMapper albumMapper;
    private final BookToSearchResultMapper bookMapper;
    private final AlbumJsonParser albumJsonParser;


    public SearchServiceController(BookService bookService, AlbumService albumService,
                                   AlbumToSearchResultMapper albumMapper, BookToSearchResultMapper bookMapper, AlbumJsonParser albumJsonParser) {
        this.bookService = bookService;
        this.albumService = albumService;
        this.albumMapper = albumMapper;
        this.bookMapper = bookMapper;
        this.albumJsonParser = albumJsonParser;
    }

    @GetMapping("{searchTerm}")
    List<SearchResultDto> getAlbumsAndBooks(@PathVariable String searchTerm) {
        List<SearchResultDto> result = new ArrayList<>();
        log.debug("Get Albums and Books. SearchTerm: {}", searchTerm);

        Optional<String> appleApiResponse = albumService.getAlbums(searchTerm).blockOptional();
        if (appleApiResponse.isPresent()){
            AlbumResult albumResult = albumJsonParser.parseJsonStringToAlbumResult(appleApiResponse.get());
            result.addAll(
                    albumMapper.albumsToSearchResultDtos(albumResult.getResults())
            );
            log.debug("Get {} albums.", albumResult.getResults().size());
        }else{
            log.debug("Albums have not found.");
        }


        Optional<BookResult> googleApiResponse = bookService.getBooks(searchTerm).blockOptional();
        if (googleApiResponse.isPresent() && null != googleApiResponse.get().getItems()){
            result.addAll(
                    bookMapper.bookItemsToSearchResultDtos(googleApiResponse.get().getItems())
            );
            log.debug("Get {} books.", googleApiResponse.get().getItems().size());
        }else{
            log.debug("Books have not found.");
        }

        if(result.size() == 0){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Empty result. SearchTerm: " + searchTerm);
        }

        return result;
    }
}

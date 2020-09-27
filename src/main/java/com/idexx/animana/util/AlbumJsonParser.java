package com.idexx.animana.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexx.animana.model.AlbumResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlbumJsonParser {

    private final ObjectMapper objectMapper;

    public AlbumJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public AlbumResult parseJsonStringToAlbumResult(String string) {
        try {
            return objectMapper.readValue(string, AlbumResult.class);
        } catch (JsonProcessingException e) {
            log.error("Error parsing string to AlbumResult");
            throw new RuntimeException(e);
        }
    }
}

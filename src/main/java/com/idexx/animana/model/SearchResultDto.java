package com.idexx.animana.model;


import lombok.Data;

@Data
public class SearchResultDto {
    private String title;
    private String author;
    private SearchResultType type;
}

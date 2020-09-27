package com.idexx.animana.model;


import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class SearchResultDto implements Comparable {
    private String title;
    private String author;
    private SearchResultType type;

    @Override
    public int compareTo(@NotNull Object o) {
        SearchResultDto dto = (SearchResultDto) o;
        return title.compareToIgnoreCase(dto.getTitle());
    }
}

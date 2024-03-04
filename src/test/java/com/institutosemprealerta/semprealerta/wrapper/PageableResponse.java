package com.institutosemprealerta.semprealerta.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

@Getter
@Setter
public class PageableResponse<T> extends PageImpl<T> {
    private boolean first;
    private boolean last;
    private int totalPages;
    private int numberOfElements;

    @JsonCreator(mode = PROPERTIES )
    public PageableResponse(@JsonProperty("content") List<T> content,
        @JsonProperty("number") int number,
        @JsonProperty("size") int size,
        @JsonProperty("totalElements") int totalElements,
        @JsonProperty("last") boolean last,
        @JsonProperty("first") boolean first,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("numberOfElements") int numberOfElements,
        @JsonProperty("pageable") JsonNode pageable,
        @JsonProperty("sort") JsonNode sort) {
        super(content, PageRequest.of(number, size), totalElements);

        this.last = last;
        this.first = first;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;

    }
}
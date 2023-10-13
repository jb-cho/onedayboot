package com.yse.dev.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class BookLogCreateDTO {
    @NonNull
    @Positive
    private Integer bookId;

    @NonNull
    private String comment;

    private Integer page;
}

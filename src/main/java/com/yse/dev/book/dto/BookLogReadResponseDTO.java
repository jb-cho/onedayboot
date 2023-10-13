package com.yse.dev.book.dto;

import com.yse.dev.book.entity.BookLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookLogReadResponseDTO {
    private Integer bookLogId;
    private String comment;
    private Integer page;
    private LocalDateTime insertDateTime;

    private String displayComment;

    public BookLogReadResponseDTO fromBookLog(BookLog bookLog){
        this.bookLogId = bookLog.getBookLogId();
        this.comment = bookLog.getComment();
        this.page = bookLog.getPage();
        this.insertDateTime = bookLog.getInsertDateTime();

        this.displayComment = (this.comment == null ? "" : "(p." + String.valueOf(this.page) + ".)") + this.comment;

        return this;
    }

    public static BookLogReadResponseDTO BookLogFactory(BookLog bookLog){
        BookLogReadResponseDTO bookLogReadResponseDTO = new BookLogReadResponseDTO();
        bookLogReadResponseDTO.fromBookLog(bookLog);

        return bookLogReadResponseDTO;
    }
}

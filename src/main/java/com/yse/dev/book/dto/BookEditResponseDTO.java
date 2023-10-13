package com.yse.dev.book.dto;

import com.yse.dev.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BookEditResponseDTO {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime insertDateTime;

    public BookEditResponseDTO fromBook(Book book){
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.insertDateTime = book.getInsertDateTime();

        return this;
    }

    public static BookEditResponseDTO BookFactory(Book book){
        BookEditResponseDTO bookEditResponseDTO = new BookEditResponseDTO();
        bookEditResponseDTO.fromBook(book);

        return bookEditResponseDTO;
    }
}

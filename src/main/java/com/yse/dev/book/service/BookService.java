package com.yse.dev.book.service;

import com.yse.dev.book.dto.*;
import com.yse.dev.book.entity.Book;
import com.yse.dev.book.entity.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Integer insert(BookCreateDTO bookCreateDTO){
        Book book = Book.builder().title(bookCreateDTO.getTitle()).price(bookCreateDTO.getPrice()).build();

        this.bookRepository.save(book);
        return book.getBookId();
    }

    // read 메소드
    public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException{
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
        bookReadResponseDTO.fromBook(book);

        return bookReadResponseDTO;
    }

    // 수정 메소드
    public BookEditResponseDTO edit(Integer bookId) throws NoSuchElementException{
        Book book = this.bookRepository.findById(bookId).orElseThrow();

        return BookEditResponseDTO.BookFactory(book);
    }

    // update
    public void update(BookEditDTO bookEditDTO) throws NoSuchElementException{
        Book book = this.bookRepository.findById(bookEditDTO.getBookId()).orElseThrow();
        book = bookEditDTO.fill(book);
        this.bookRepository.save(book);
    }

    // delete
    public void delete(Integer bookId) throws NoSuchElementException{
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        this.bookRepository.delete(book);
    }

    // 책 목록 반환 기능
   public List<BookListResponseDTO> bookList(String title,Integer page){
        final int pageSize = 3;

        List<Book> books;

        if (page == null) {
            page = 0;
        } else {
            page -= 1;
        }

        if (title == null) {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "insertDateTime");
            books = this.bookRepository.findAll(pageable).toList();
        } else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Sort sort = Sort.by(Sort.Order.desc("insertDateTime"));
            pageable.getSort().and(sort);
            books = this.bookRepository.findByTitleContains(title,pageable);
        }

        return books.stream().map(book ->
                new BookListResponseDTO(book.getBookId(),book.getTitle())
        ).collect(Collectors.toList());
   }

}

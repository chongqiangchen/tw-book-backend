package com.tw.book.application;

import com.tw.book.controller.response.BookResponse;
import com.tw.book.domain.Book;
import com.tw.book.domain.BookCommand;
import com.tw.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookApplication {

    private final BookRepository bookRepository;

    public Long createBook(BookCommand command) {
        Book book = Book.create(command);
        book = bookRepository.save(book);
        return book.getId();
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAllBooks();
        return books.stream().map(BookResponse::create).collect(Collectors.toList());
    }

    public BookResponse getBook(Long id) {
        Book book = bookRepository.findBookById(id);
        return BookResponse.create(book);
    }

    public BookResponse updateBook(BookCommand command, Long id) {
        Book book = Book.create(command);
        book = bookRepository.updateBook(book, id);
        return BookResponse.create(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }
}

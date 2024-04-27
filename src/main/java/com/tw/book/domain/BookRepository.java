package com.tw.book.domain;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAllBooks();
    Book findBookById(Long id);
    Book updateBook(Book book, Long id);
    void deleteBook(Long id);
}

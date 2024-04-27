package com.tw.book.application;

import com.tw.book.controller.response.BookResponse;
import com.tw.book.domain.Book;
import com.tw.book.domain.BookCommand;
import com.tw.book.domain.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookApplicationTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookApplication bookApplication;

    private Book book;
    private BookCommand command;
    private final Long bookId = 1L;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(bookId)
                .title("test")
                .author("test")
                .publicationYear("1987")
                .isbn("test")
                .build();

        command = new BookCommand("test", "test", "1987", "test");
    }

    @Test
    void createBook() {
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Long resultId = bookApplication.createBook(command);

        assertEquals(book.getId(), resultId);
        verify(bookRepository).save(Mockito.any(Book.class));
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAllBooks()).thenReturn(List.of(book));

        List<BookResponse> bookResponses = bookApplication.getAllBooks();

        assertNotNull(bookResponses);
        assertEquals(1, bookResponses.size());
        assertEquals(bookId, bookResponses.get(0).getId());
        verify(bookRepository).findAllBooks();
    }

    @Test
    void getBook() {
        when(bookRepository.findBookById(bookId)).thenReturn(book);

        BookResponse bookResponse = bookApplication.getBook(bookId);

        assertNotNull(bookResponse);
        assertEquals(bookId, bookResponse.getId());
        verify(bookRepository).findBookById(bookId);
    }

    @Test
    void updateBook() {
        when(bookRepository.updateBook(any(Book.class), anyLong())).thenReturn(book);

        BookResponse bookResponse = bookApplication.updateBook(command, bookId);

        assertNotNull(bookResponse);
        assertEquals(bookId, bookResponse.getId());
        verify(bookRepository).updateBook(any(Book.class), any(Long.class));
    }

    @Test
    void deleteBook() {
        bookApplication.deleteBook(bookId);
        verify(bookRepository).deleteBook(bookId);
    }
}
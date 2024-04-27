package com.tw.book.infrastructure.repository;

import com.tw.book.domain.Book;
import com.tw.book.infrastructure.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Book book;
    private BookEntity bookEntity;

    @BeforeEach
    public void setUp() {
        book = Book.builder()
                .id(1L)
                .title("test")
                .author("test")
                .publicationYear("1987")
                .isbn("test")
                .build();

        bookEntity = BookEntity.builder()
                .title("test")
                .author("test")
                .publicationYear("1987")
                .isbn("test")
                .build();

        bookEntity = entityManager.persist(bookEntity);
    }

    @Test
    void save() {
        Book savedBook = bookJpaRepository.save(book);
        BookEntity savedBookEntity = entityManager.find(BookEntity.class, savedBook.getId());

        assertEquals(savedBook.getId(), savedBookEntity.toDomain().getId());
    }

    @Test
    void findAllBooks() {
        List<Book> books = bookJpaRepository.findAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(bookEntity.getId(), books.get(0).getId());
    }

    @Test
    void findBookById() {
        Book foundBook = bookJpaRepository.findBookById(bookEntity.getId());

        assertNotNull(foundBook);
        assertEquals(bookEntity.getId(), foundBook.getId());
    }

    @Test
    void updateBook() {
        Book updatedBook = Book.builder()
                .title("updated")
                .author("updated")
                .publicationYear("1987")
                .isbn("updated")
                .build();

        bookJpaRepository.updateBook(updatedBook, bookEntity.getId());

        BookEntity updatedBookEntity = entityManager.find(BookEntity.class, bookEntity.getId());

        assertEquals(bookEntity.getId(), updatedBookEntity.getId());
        assertEquals(updatedBook.getTitle(), updatedBookEntity.getTitle());
        assertEquals(updatedBook.getAuthor(), updatedBookEntity.getAuthor());
        assertEquals(updatedBook.getPublicationYear(), updatedBookEntity.getPublicationYear());
        assertEquals(updatedBook.getIsbn(), updatedBookEntity.getIsbn());
    }

    @Test
    void deleteBook() {
        bookJpaRepository.deleteBook(bookEntity.getId());
        BookEntity deletedBookEntity = entityManager.find(BookEntity.class, bookEntity.getId());
        assertNull(deletedBookEntity);
    }
}
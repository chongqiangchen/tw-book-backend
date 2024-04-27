package com.tw.book.infrastructure.repository;

import com.tw.book.domain.Book;
import com.tw.book.domain.BookRepository;
import com.tw.book.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface BookJpaRepository extends BookRepository, JpaRepository<BookEntity, Long> {
    @Override
    default Book save(Book book) {
        BookEntity entity = BookEntity.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .isbn(book.getIsbn())
                .build();
        BookEntity saved = save(entity);
        return saved.toDomain();
    }

    @Override
    default List<Book> findAllBooks() {
        List<BookEntity> bookEntities = findAll();
        return bookEntities.stream().map(BookEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    default Book findBookById(Long id) {
        return findById(id).map(BookEntity::toDomain).orElse(null);
    }

    @Override
    default Book updateBook(Book book, Long id) {
        BookEntity entity = BookEntity.builder()
                .id(id)
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .isbn(book.getIsbn())
                .build();
        entity.setId(id);
        BookEntity saved = save(entity);
        return saved.toDomain();
    }

    @Override
    default void deleteBook(Long id) {
        deleteById(id);
    }
}

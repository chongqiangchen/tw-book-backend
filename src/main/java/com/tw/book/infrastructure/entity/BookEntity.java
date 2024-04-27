package com.tw.book.infrastructure.entity;

import com.tw.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class BookEntity extends AbstractEntity {
    private String title;
    private String author;
    private String publicationYear;
    private String isbn;

    public Book toDomain() {
        return Book.builder()
                .id(getId())
                .title(title)
                .author(author)
                .publicationYear(publicationYear)
                .isbn(isbn)
                .build();
    }
}

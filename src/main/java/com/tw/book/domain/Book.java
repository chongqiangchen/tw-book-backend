package com.tw.book.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class Book {
    private Long id;
    private String title;
    private String author;
    private String publicationYear;
    private String isbn;

    public static Book create(BookCommand command) {
        return Book.builder()
                .title(command.getTitle())
                .author(command.getAuthor())
                .publicationYear(command.getPublicationYear())
                .isbn(command.getIsbn())
                .build();
    }
}

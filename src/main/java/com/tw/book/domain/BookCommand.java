package com.tw.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class BookCommand {
    private String title;
    private String author;
    private String publicationYear;
    private String isbn;
}

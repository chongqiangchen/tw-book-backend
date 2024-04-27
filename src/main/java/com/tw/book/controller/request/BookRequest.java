package com.tw.book.controller.request;

import com.tw.book.domain.BookCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotBlank(message = "not blank")
    private String title;
    @NotBlank(message = "not blank")
    private String author;
    @NotBlank(message = "not blank")
    private String publicationYear;
    @NotBlank(message = "not blank")
    private String isbn;

    public BookCommand toCommand() {
        return new BookCommand(title, author, publicationYear, isbn);
    }
}

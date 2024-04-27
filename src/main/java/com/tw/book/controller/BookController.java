package com.tw.book.controller;

import com.tw.book.common.Result;
import com.tw.book.controller.request.BookRequest;
import com.tw.book.application.BookApplication;
import com.tw.book.controller.response.BookResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("书籍接口文档")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookApplication bookApplication;

    @GetMapping
    public Result<List<BookResponse>> getBooks() {
        return Result.success(bookApplication.getAllBooks());
    }

    @GetMapping("/{id}")
    public Result<BookResponse> getBook(@PathVariable("id") Long id) {
        return Result.success(bookApplication.getBook(id));
    }

    @PostMapping
    public Result<Long> createBook(@Validated @RequestBody BookRequest bookRequest) {
        Long bookId = bookApplication.createBook(bookRequest.toCommand());
        return Result.success(bookId);
    }

    @PutMapping("/{id}")
    public Result<BookResponse> updateBook(@Validated @RequestBody BookRequest bookRequest, @PathVariable("id") Long id) {
        BookResponse bookResponse = bookApplication.updateBook(bookRequest.toCommand(), id);
        return Result.success(bookResponse);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteBook(@PathVariable("id") Long id) {
        bookApplication.deleteBook(id);
        return Result.success();
    }
}

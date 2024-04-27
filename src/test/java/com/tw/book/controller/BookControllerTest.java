package com.tw.book.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tw.book.application.BookApplication;
import com.tw.book.controller.request.BookRequest;
import com.tw.book.controller.response.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookApplication bookApplication;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<BookResponse> bookResponses;
    private BookResponse bookResponse;
    private BookRequest bookRequest;
    private Long bookId = 1L;

    @BeforeEach
    void setUp() {
        bookResponse = BookResponse.builder()
                .id(1L)
                .title("test")
                .author("test")
                .publicationYear("1987")
                .isbn("test")
                .build();
        bookRequest = BookRequest.builder()
                .title("test")
                .author("test")
                .publicationYear("1987")
                .isbn("test")
                .build();
        bookResponses = List.of(bookResponse);
    }

    @Test
    void getBooks() throws Exception {
        when(bookApplication.getAllBooks()).thenReturn(bookResponses);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data[0].id").value(bookResponse.getId()))
                .andExpect(jsonPath("$.data[0].title").value(bookResponse.getTitle()))
                .andExpect(jsonPath("$.data[0].author").value(bookResponse.getAuthor()))
                .andExpect(jsonPath("$.data[0].publicationYear").value(bookResponse.getPublicationYear()))
                .andExpect(jsonPath("$.data[0].isbn").value(bookResponse.getIsbn()));
    }

    @Test
    void getBook() throws Exception {
        when(bookApplication.getBook(bookId)).thenReturn(bookResponse);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data.id").value(bookResponse.getId()))
                .andExpect(jsonPath("$.data.title").value(bookResponse.getTitle()))
                .andExpect(jsonPath("$.data.author").value(bookResponse.getAuthor()))
                .andExpect(jsonPath("$.data.publicationYear").value(bookResponse.getPublicationYear()))
                .andExpect(jsonPath("$.data.isbn").value(bookResponse.getIsbn()));
    }

    @Test
    void createBook() throws Exception {
        when(bookApplication.createBook(bookRequest.toCommand())).thenReturn(bookId);

        String bookRequestJson = objectMapper.writeValueAsString(bookRequest);

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(bookRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data").value(bookId));
    }

    @Test
    void updateBook() throws Exception {
        when(bookApplication.updateBook(any(), anyLong())).thenReturn(bookResponse);

        String bookRequestJson = objectMapper.writeValueAsString(bookRequest);

        mockMvc.perform(put("/books/1")
                .contentType("application/json")
                .content(bookRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data.id").value(bookResponse.getId()))
                .andExpect(jsonPath("$.data.title").value(bookResponse.getTitle()))
                .andExpect(jsonPath("$.data.author").value(bookResponse.getAuthor()))
                .andExpect(jsonPath("$.data.publicationYear").value(bookResponse.getPublicationYear()))
                .andExpect(jsonPath("$.data.isbn").value(bookResponse.getIsbn()));
    }

    @Test
    void deleteBook() {
        assertDoesNotThrow(() -> mockMvc.perform(delete("/books/1")));
    }
}
package com.example.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.BookController;
import com.example.dto.BookDto;
import com.example.entity.Book;
import com.example.exception.BookNotFoundException;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.PublisherNotFoundException;
import com.example.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	public void testAddBook() throws Exception {
		String jsonPayload = "{ \"title\": \"Book Title\", \"author\": \"Author Name\", \"category\": \"Fiction\", \"publisher\": \"Publisher Name\" }";

		when(bookService.addBook(any(BookDto.class))).thenReturn("Book Added Successfully");

		mockMvc.perform(post("/api/v1/book/post").contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
				.andExpect(status().isBadRequest()).andExpect(content().string("Book Added Successfully"));
	}

	@Test
	public void testGetBookByTitle() throws Exception {
		String title = "Book Title";
		BookDto book = new BookDto();
		book.setTitle(title);

		when(bookService.getBookByTitle(title)).thenReturn(book);

		mockMvc.perform(get("/api/v1/book/title/{title}", title)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value(title));
	}

	@Test
	public void testGetBookByCategory() throws Exception {
		String category = "Fiction";
		List<BookDto> books = Arrays.asList(new BookDto(), new BookDto());

		when(bookService.getBookByCategory(category)).thenReturn(books);

		mockMvc.perform(get("/api/v1/book/category/{category}", category)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.size()").value(2));
	}

	@Test
	public void testUpdateDescriptionByIsbn() throws Exception {
		String isbn = "123456789";
		String description = "Updated Description";
		BookDto book = new BookDto();
		book.setDescription(description);

		when(bookService.updateDescriptionByIsbn(isbn, description)).thenReturn(book);

		mockMvc.perform(put("/api/v1/book/update/description/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(description)).andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value(description));
	}

	@Test
	public void testUpdateEditionByIsbn() throws Exception {
		String isbn = "123456789";
		String edition = "Updated Edition";
		BookDto book = new BookDto();
		book.setEdition(edition);

		when(bookService.updateEditionByIsbn(isbn, edition)).thenReturn(book);

		mockMvc.perform(put("/api/v1/book/update/edition/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(edition)).andExpect(status().isOk()).andExpect(jsonPath("$.edition").value(edition));
	}
}

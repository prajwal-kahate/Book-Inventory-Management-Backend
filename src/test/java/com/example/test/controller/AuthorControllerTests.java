package com.example.test.controller;

import com.example.controller.AuthorController;
import com.example.dto.AuthorDto;
import com.example.dto.BookDto;
import com.example.exception.AuthorNotFoundException;
import com.example.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorService authorService;

	@Test
	void testAddAuthor() throws Exception {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setFirstName("John");
		authorDto.setLastName("Doe");

		when(authorService.addAuthor(authorDto)).thenReturn("Author added successfully");

		mockMvc.perform(
				post("/api/v1/author/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(authorDto)))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$").value("Author added successfully"));

		verify(authorService, times(1)).addAuthor(authorDto);
	}

	@Test
	void testGetAuthorByLastName() throws Exception {
		String lastName = "Doe";
		AuthorDto authorDto = new AuthorDto();
		authorDto.setFirstName("John");
		authorDto.setLastName(lastName);

		when(authorService.getAuthorByLastName(lastName)).thenReturn(authorDto);

		mockMvc.perform(get("/api/v1/author/lastname/{lastname}", lastName)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.firstName").value("John")).andExpect(jsonPath("$.lastName").value(lastName));

		verify(authorService, times(1)).getAuthorByLastName(lastName);
	}

	@Test
	void testGetBooksByAuthorId() throws Exception {
		int authorId = 1;

		BookDto bookDto = new BookDto();
		bookDto.setTitle("Sample Book");

		List<BookDto> books = Arrays.asList(bookDto);

		when(authorService.getBooksByAuthorId(authorId)).thenReturn(books);

		mockMvc.perform(get("/api/v1/author/books/{authorId}", authorId)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value("Sample Book"));

		verify(authorService, times(1)).getBooksByAuthorId(authorId);
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.AuthorDto;
import com.example.dto.BookDto;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.exception.AuthorNotFoundException;

public interface AuthorService {
	AuthorDto getAuthorByLastName(String lastName) throws AuthorNotFoundException;
//	Set<Book> getBooksByAuthorId(int authorId);

	List<BookDto> getBooksByAuthorId(int authorId) throws AuthorNotFoundException;

	String addAuthor(AuthorDto authorDto);
}

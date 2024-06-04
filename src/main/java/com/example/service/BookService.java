package com.example.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dto.BookDto;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.exception.BookNotFoundException;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.PublisherNotFoundException;

public interface BookService {
//	Book addBook(Book book);

	String addBook(BookDto book) throws CategoryNotFoundException, PublisherNotFoundException;

	BookDto getBookByTitle(String title) throws BookNotFoundException;

//	Book getBookByCategory(Category category);

	List<BookDto> getBookByCategory(String category) throws BookNotFoundException;

	BookDto updateDescriptionByIsbn(String isbn, String description) throws BookNotFoundException;

	BookDto updateEditionByIsbn(String isbn, String edition) throws BookNotFoundException;

}

package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDto;
import com.example.entity.Book;
import com.example.exception.BookNotFoundException;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.PublisherNotFoundException;
import com.example.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	BookService bookService;

//	@PostMapping(value = "/api/v1/book/post", consumes = {"application/json"})
//	String addBook(@RequestBody Book book){
//		Book newBook = bookService.addBook(book);
//		return "Book Added Succesfully";
//	}

	@PostMapping(value = "/api/v1/book/post", consumes = { "application/json" })
	public ResponseEntity<String> addBook(@RequestBody BookDto bookDto)
			throws PublisherNotFoundException, CategoryNotFoundException {

		String response = bookService.addBook(bookDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/v1/book/title/{title}")
	ResponseEntity<BookDto> getBookByTitle(@PathVariable String title) throws BookNotFoundException {
		BookDto book1 = bookService.getBookByTitle(title);
		return new ResponseEntity<>(book1, HttpStatus.OK);
	}

	@GetMapping("/api/v1/book/category/{category}")
	ResponseEntity<List<BookDto>> getBookByCategory(@PathVariable String category) throws BookNotFoundException {
		System.out.println(category);

		List<BookDto> books = bookService.getBookByCategory(category);
		System.out.println(books);

		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@PutMapping("/api/v1/book/update/description/{isbn}")
	ResponseEntity<BookDto> updateDescriptionByIsbn(@PathVariable String isbn, @RequestBody String description)
			throws BookNotFoundException {
		System.out.println(isbn + description);
		BookDto book = bookService.updateDescriptionByIsbn(isbn, description);
		return new ResponseEntity<>(book, HttpStatus.OK);

	}

	@PutMapping("/api/v1/book/update/edition/{isbn}")
	ResponseEntity<BookDto> updateEditionByIsbn(@PathVariable String isbn, @RequestBody String edition)
			throws BookNotFoundException {

		BookDto book = bookService.updateEditionByIsbn(isbn, edition);

		return new ResponseEntity<>(book, HttpStatus.OK);

	}

//	/api/v1/book/update/description/{isbn}	PUT
//	/api/v1/book/update/edition/{isbn}	PUT
//	/api/v1/author/post	POST
//	/api/v1/author/lastname/{lastname}	GET
//	/api/v1/author/books/{authorId}	GET

}

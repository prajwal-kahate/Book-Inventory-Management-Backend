package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AuthorDto;
import com.example.dto.BookDto;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.exception.AuthorNotFoundException;
import com.example.service.AuthorService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/author")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/api/v1/author/post")
	ResponseEntity<String> addAuthor(@RequestBody AuthorDto authorDto) {
		String response = authorService.addAuthor(authorDto);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/api/v1/author/lastname/{lastName}")
	ResponseEntity<AuthorDto> getAuthorByLastName(@PathVariable("lastName") String lastName)
			throws AuthorNotFoundException {
		AuthorDto authorDto = authorService.getAuthorByLastName(lastName);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}

	@GetMapping("/api/v1/author/books/{authorId}")
	ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable("authorId") int authorId)
			throws AuthorNotFoundException {
		List<BookDto> books = authorService.getBooksByAuthorId(authorId);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/api/hello")
	String getHello() {
		return "Hello World";
	}

}

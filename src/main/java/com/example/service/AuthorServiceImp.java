package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AuthorDao;
import com.example.dao.BookDao;
import com.example.dto.AuthorDto;
import com.example.dto.BookDto;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.exception.AuthorCreationException;
import com.example.exception.AuthorNotFoundException;
import com.example.util.AuthorDTOMapper;
import com.example.util.BookDTOMapper;

@Service
public class AuthorServiceImp implements AuthorService {

	@Autowired
	AuthorDao authorDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	BookDTOMapper bookDTOMapper;

	@Override
	public AuthorDto getAuthorByLastName(String lastName) throws AuthorNotFoundException {
		Author author = authorDao.findAuthorByLastName(lastName);
		if (author == null) {
			throw new AuthorNotFoundException("Author not found with last name: " + lastName);
		}

		AuthorDTOMapper authorDTOMapper = new AuthorDTOMapper();

		return authorDTOMapper.mapToDTO(author);
	}

	@Override
	public List<BookDto> getBooksByAuthorId(int authorId) throws AuthorNotFoundException {
		List<Book> books = bookDao.findBooksByAuthorId(authorId);
		if (books.isEmpty()) {
			throw new AuthorNotFoundException("Author not found with ID: " + authorId);
		}
		List<BookDto> dtoBooks = books.stream().map(bookDTOMapper::mapToDTO).collect(Collectors.toList());

		return dtoBooks;
	}

	@Override
	public String addAuthor(AuthorDto authorDto) {
		try {
			AuthorDTOMapper authorDTOMapper = new AuthorDTOMapper();
			Author author = authorDTOMapper.mapToEntity(authorDto);
			authorDao.save(author);
			return "Author added successfully";
		} catch (Exception e) {
			throw new AuthorCreationException("Failed to create author: " + e.getMessage());
		}
	}

//	@Override
//	public Author addAuthor(AuthorDto authorDto) {
//		
//		AuthorDTOMapper authorDTOMapper = new AuthorDTOMapper();
//		Author author = authorDTOMapper.mapToEntity(authorDto);
//		return authorDao.save(author);
//	}

//	@Override
//	public Set<Book> getBooksByAuthorId(int authorId) {
//		Optional<Author> opt= authorDao.findById(authorId);
//		if (opt.isPresent()) {
//			Author author = opt.get();
//			Set<Book> book = author.getBooks();
//			
//			return book;
//		}
//		return null;
//	}

}

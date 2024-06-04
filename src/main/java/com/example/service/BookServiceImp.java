package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookDao;
import com.example.dao.CategoryDao;
import com.example.dto.BookDto;
import com.example.dao.PublisherDao;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.entity.Publisher;
import com.example.exception.BookNotFoundException;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.PublisherNotFoundException;
import com.example.util.BookDTOMapper;

@Service
public class BookServiceImp implements BookService {

	@Autowired
	BookDao bookDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	PublisherDao publisherDao;

	@Autowired
	BookDTOMapper bookDTOMapper;
//	@Override
//	public Book addBook(Book book) {
//		Book newBook= bookDao.save(book);
//		return newBook;
//	}

	@Override
	public String addBook(BookDto bookDto) throws CategoryNotFoundException, PublisherNotFoundException {
		Book newbook = new Book();
		newbook.setIsbn(bookDto.getIsbn());
		newbook.setTitle(bookDto.getTitle());
		newbook.setEdition(bookDto.getEdition());
		newbook.setDescription(bookDto.getDescription());

		Category category = categoryDao.findById(bookDto.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));

		newbook.setCategory(category);

		Publisher publisher = publisherDao.findById(bookDto.getPublisherId())
				.orElseThrow(() -> new PublisherNotFoundException("Publisher not found"));

		newbook.setPublisher(publisher);

		bookDao.save(newbook);

		return "Book Added successfully";

	}

	@Override
	public BookDto getBookByTitle(String title) throws BookNotFoundException {
		Book book1 = bookDao.findBookByTitle(title);
		if (book1 == null) {
			throw new BookNotFoundException("Book not found with title: " + title);
		}
		BookDTOMapper bookDTOMapper = new BookDTOMapper();

		return bookDTOMapper.mapToDTO(book1);
	}

//	@Override
//	public Book getBookByCategory(Category category) {
//		Book book2= bookDao.findBookByCategory(category);
//		return book2;
//	}

	@Override
	public List<BookDto> getBookByCategory(String category) throws BookNotFoundException {
		List<Book> books = bookDao.findBooksByCategory(category);

		if (books.isEmpty()) {

			throw new BookNotFoundException("No books found for category: " + category);
		}
		List<BookDto> dtoBooks = books.stream().map(bookDTOMapper::mapToDTO).collect(Collectors.toList());
		System.out.println(dtoBooks);
		return dtoBooks;
	}

	@Override
	public BookDto updateDescriptionByIsbn(String isbn, String description) throws BookNotFoundException {

		Optional<Book> option = bookDao.findById(isbn);

		if (option.isPresent()) {
			Book book = option.get();
			book.setDescription(description);
			Book book1 = bookDao.save(book);

			BookDTOMapper bookDTOMapper = new BookDTOMapper();
			return bookDTOMapper.mapToDTO(book1);
		} else {
			throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
		}

	}

	@Override
	public BookDto updateEditionByIsbn(String isbn, String edition) throws BookNotFoundException {
		Optional<Book> opt = bookDao.findById(isbn);
		if (opt.isPresent()) {
			Book book = opt.get();

			book.setEdition(edition);

			Book book1 = bookDao.save(book);
			BookDTOMapper bookDTOMapper = new BookDTOMapper();
			BookDto bookDto = bookDTOMapper.mapToDTO(book1);
			return bookDto;
		} else {
			throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
		}

	}

}

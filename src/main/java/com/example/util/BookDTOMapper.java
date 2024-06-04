package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.CategoryDao;
import com.example.dao.PublisherDao;
import com.example.dto.BookDto;

import com.example.entity.Book;
import com.example.entity.Category;
import com.example.entity.Publisher;

@Component
public class BookDTOMapper {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private PublisherDao publisherDao;

	public Book mapToEntity(BookDto bookDto) {
		Book newBook = new Book();
		newBook.setIsbn(bookDto.getIsbn());
		newBook.setTitle(bookDto.getTitle());
		newBook.setEdition(bookDto.getEdition());
		newBook.setDescription(bookDto.getDescription());

		Category category = categoryDao.findById(bookDto.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found"));
		newBook.setCategory(category);

		Publisher publisher = publisherDao.findById(bookDto.getPublisherId())
				.orElseThrow(() -> new RuntimeException("Publisher not found"));
		newBook.setPublisher(publisher);

		return newBook;
	}

	public BookDto mapToDTO(Book book) {
		BookDto bookDTO = new BookDto();
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setEdition(book.getEdition());
		bookDTO.setDescription(book.getDescription());
		bookDTO.setCategoryId(book.getCategory().getCatID()); // Assuming getCategory() returns Category entity
		bookDTO.setPublisherId(book.getPublisher().getPublisherID()); // Assuming getPublisher() returns Publisher
																		// entity

		return bookDTO;
	}
}
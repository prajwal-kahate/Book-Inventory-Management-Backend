package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Book;
import com.example.entity.Category;

@Repository
public interface BookDao extends JpaRepository<Book, String> {

	Book findBookByTitle(String title);

	@Query("SELECT b FROM Book b WHERE b.category.catDescription = :category")
	List<Book> findBooksByCategory(@Param("category") String category);

	@Query(value = "SELECT b.* FROM Book b " + "JOIN book_author ba ON b.ISBN = ba.ISBN "
			+ "WHERE ba.authorID = :authorId", nativeQuery = true)
	List<Book> findBooksByAuthorId(@Param("authorId") int authorId);

}

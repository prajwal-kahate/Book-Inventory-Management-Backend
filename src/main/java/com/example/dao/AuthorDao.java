package com.example.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Author;
import com.example.entity.Book;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {
	Author findAuthorByLastName(String lastName);

//	@Query(value = "SELECT b.* FROM Book b " +
//            "JOIN book_author ba ON b.ISBN = ba.ISBN " +
//            "WHERE ba.authorID = :authorId", nativeQuery = true)
//     List<String> findBooksByAuthorId(@Param("authorId") int authorId);

}

//SELECT Courses.course_name
//FROM Courses
//JOIN Enrollment ON Courses.course_id = Enrollment.course_id
//WHERE Enrollment.student_id = [specific_student_id];

//SELECT * from parents p inner join children c on c.id=p.childId 
//where TIMESTAMPDIFF(SECOND, p.ts, CURRENT_TIMESTAMP) < :interval", nativeQuery = true)

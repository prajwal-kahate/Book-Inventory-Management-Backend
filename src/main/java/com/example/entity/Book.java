package com.example.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Book {

	@Id
	private String isbn;

	private String title;
	private String description;
	private String edition;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category")
	private Category category;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "publisherID")
	private Publisher publisher;

	@JsonBackReference
	@ManyToMany(mappedBy = "books")
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "bookReview", joinColumns = { @JoinColumn(name = "isbn") }, inverseJoinColumns = {
			@JoinColumn(name = "reviewerId") })
	private Set<Reviewer> reviewers;

	@ManyToMany(mappedBy = "books")
	private Set<User> users;

}

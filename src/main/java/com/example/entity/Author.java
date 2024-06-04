package com.example.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Author {

	@Id
	private int authorID;
	private String lastName;
	private String firstName;

	private String photo;

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "bookAuthor", joinColumns = { @JoinColumn(name = "authorID") }, inverseJoinColumns = {
			@JoinColumn(name = "isbn") })
	private List<Book> books;

}

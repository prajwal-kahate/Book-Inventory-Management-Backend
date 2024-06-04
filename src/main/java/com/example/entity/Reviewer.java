package com.example.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Reviewer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewerID;

	private String name;
	private String employedBy;

	@ManyToMany(mappedBy = "reviewers")
	private Set<Book> books;

}

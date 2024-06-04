package com.example.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Inventory {

	@Id
	private int inventoryID;

	private boolean purchased;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isbn")
	private Book book;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ranks")
	private BookCondition bookCondition;

	@ManyToMany(mappedBy = "inventory")
	private Set<User> users;
}

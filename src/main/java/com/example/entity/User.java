package com.example.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;

	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String userName;
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rollNumber")
	private PermRole permRole;

	@ManyToMany
	@JoinTable(name = "purchesLog", joinColumns = { @JoinColumn(name = "userID") }, inverseJoinColumns = {
			@JoinColumn(name = "inventoryID") })
	private Set<Inventory> inventory;

	@ManyToMany
	@JoinTable(name = "shoppingCart", joinColumns = { @JoinColumn(name = "userID") }, inverseJoinColumns = {
			@JoinColumn(name = "isbn") })
	private Set<Book> books;

	private boolean isLogin = false;

}

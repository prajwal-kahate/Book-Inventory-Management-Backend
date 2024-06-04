package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookCondition {

	@Id
	private int ranks;

	private String description;
	private String fullDescription;
	private float price;

}

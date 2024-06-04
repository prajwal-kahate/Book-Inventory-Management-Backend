package com.example.dto;

import lombok.Data;

@Data
public class BookDto {

	private String isbn;
	private String title;
	private String description;
	private String edition;
	private int categoryId;

	private int publisherId;

}

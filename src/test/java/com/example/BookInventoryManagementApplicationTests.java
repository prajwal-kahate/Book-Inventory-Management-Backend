package com.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.controller.BookController;

@SpringBootTest
class BookInventoryManagementApplicationTests {

	@Autowired
	private BookController bookController;

	@Test
	void contextLoads() {
		assertNotNull(bookController);
	}

}

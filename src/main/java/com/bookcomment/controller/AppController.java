package com.bookcomment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookcomment.entity.BookDetails;
import com.bookcomment.service.BookService;

@RestController
@RequestMapping("/api")
public class AppController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<BookDetails> getBooks() {
		return 	bookService.getBooks();
	}
}

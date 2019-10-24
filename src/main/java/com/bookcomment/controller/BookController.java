package com.bookcomment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookcomment.entity.BookDetails;
import com.bookcomment.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/admin/addbook")
	public String addBook(BookDetails bookDetails) {
		System.out.println(bookDetails);
		bookService.addBook(bookDetails);
		return "redirect:/index";
	}
	
	@GetMapping(value= {"/", "/index"})
	public String getindex(Model model) {
		model.addAttribute("books", bookService.getBooks());
		System.out.println(bookService.getBooks());
		return "index";
	}
	
	@GetMapping("/deletebook/{id}")
	public String deletebook(@PathVariable(value="id") String id) {
		bookService.deleteBook(id);
		System.out.println(id);
		return "redirect:/index";
	}
	
	
}

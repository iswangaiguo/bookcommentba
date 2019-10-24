package com.bookcomment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookcomment.dao.BookDao;
import com.bookcomment.entity.BookDetails;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	public void addBook(BookDetails bookDetails) {
		bookDao.insertBook(bookDetails);
	}

	public List<BookDetails> getBooks() {
		return bookDao.selectAllbooks();
	}

	public void deleteBook(String id) {
		bookDao.deleteBookById(id);
	}
}

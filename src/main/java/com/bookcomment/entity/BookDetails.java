package com.bookcomment.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookDetails {
	private int bookId;
	private String bookName;
	private String bookIndex;
	private String bookAuthor;
	private String bookPublishHouse;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookPublishTime;
	private String bookAbstract;
	private int bootCategory;
	private String bookImageUrl;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookIndex() {
		return bookIndex;
	}

	public void setBookIndex(String bookIndex) {
		this.bookIndex = bookIndex;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublishHouse() {
		return bookPublishHouse;
	}

	public void setBookPublishHouse(String bookPublishHouse) {
		this.bookPublishHouse = bookPublishHouse;
	}

	public String getBookPublishTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(bookPublishTime);
		
	}

	public void setBookPublishTime(Date bookPublishTime) {
		this.bookPublishTime = bookPublishTime;
	}

	public String getBookAbstract() {
		return bookAbstract;
	}

	public void setBookAbstract(String bookAbstract) {
		this.bookAbstract = bookAbstract;
	}

	public int getBootCategory() {
		return bootCategory;
	}

	public void setBootCategory(int bootCategory) {
		this.bootCategory = bootCategory;
	}

	public String getBookImageUrl() {
		return bookImageUrl;
	}

	public void setBookImageUrl(String bookImageUrl) {
		this.bookImageUrl = bookImageUrl;
	}

	@Override
	public String toString() {
		return "BookDetails [bookId=" + bookId + ", bookName=" + bookName + ", bookIndex=" + bookIndex + ", bookAuthor="
				+ bookAuthor + ", bookPublishHouse=" + bookPublishHouse + ", bookPublishTime=" + bookPublishTime
				+ ", bookAbstract=" + bookAbstract + ", bootCategory=" + bootCategory + ", bookImageUrl=" + bookImageUrl
				+ "]";
	}
	
	
	
}

package com.greatlearning.javafsd.library.service;

import java.util.List;

import com.greatlearning.javafsd.library.entity.Book;

public interface BookService {

	List<Book> listAll();
	
	Book findById(int bookId);
	
	void update(Book book, String name, String author, String category);
	
	void deleteById(int bookId);
	
	void add(Book newBook);
	
	List<Book> searchBy(String name, String author);
}

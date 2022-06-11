package com.greatlearning.javafsd.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.javafsd.library.entity.Book;
import com.greatlearning.javafsd.library.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping("/list")
	public String listAll(Model model){
		
		List<Book> books = bookService.listAll();
		
		model.addAttribute("bookCollection", books);
		
		return "book-lister";
	}

	@PostMapping("/addOrUpdate")
	public String handleAddOrUpdate(
	
		@RequestParam("bookId") int bookId,
		@RequestParam("name") String name,
		@RequestParam("author") String author,
		@RequestParam("category") String category) {
		
		if (bookId == 0) {
			
			// Add operation
			
			Book newBook = new Book();
			newBook.setName(name);
			newBook.setAuthor(author);
			newBook.setCategory(category);
			
			bookService.add(newBook);
						
			return "redirect:/books/list";			
			
		}else {
			
			// Update operation

			Book book = bookService.findById(bookId);
			
			bookService.update(book, name, author, category);
			
			return "redirect:/books/list";			
		}		
	}

	@RequestMapping("/begin-update")
	public String handleBeginUpdate(
		@RequestParam("bookId") int bookId, Model model){
		
		Book book = bookService.findById(bookId);
		
		model.addAttribute("bookObject", book);
	
		return "book-details-page";
	}
	

	@RequestMapping("/delete")
	public String handleDelete(
		@RequestParam("bookId") int bookId){
				
		bookService.deleteById(bookId);
		
		return "redirect:/books/list";				
	}
	
	@RequestMapping("/begin-add")	
	public String handleBeginAdd(Model model) {
		
		Book newBook = new Book();
		
		model.addAttribute("bookObject", newBook);
		
		return "book-details-page";
	}
	
	
	@RequestMapping("/search")	
	public String handleSearch(
		
		@RequestParam("name") String name,
		@RequestParam("author") String author,
		Model model){
		
		// "   name1   " -> "name1"
		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			
			return "redirect:/books/list";
		}else {
			
			List<Book> books = bookService.searchBy(name, author);
			
			model.addAttribute("bookCollection", books);
			
			return "book-lister";
		}		
	}
	


}

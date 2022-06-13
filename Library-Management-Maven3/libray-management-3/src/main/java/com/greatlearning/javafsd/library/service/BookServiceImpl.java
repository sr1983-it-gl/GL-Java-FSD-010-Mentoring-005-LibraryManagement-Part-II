package com.greatlearning.javafsd.library.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.javafsd.library.entity.Book;

@Repository
public class BookServiceImpl implements BookService{

	private Session session;
	
	@Autowired
	public BookServiceImpl(SessionFactory sessionFactory) {
		
		try {
			this.session = sessionFactory.getCurrentSession();			
		}catch (HibernateException e) {
			this.session = sessionFactory.openSession();
		}
	}
	
	
	@Transactional
	@Override
	public List<Book> listAll() {
	
		Transaction transaction = session.beginTransaction();
		
		// Database Query - "select * from BOOK" [SQL / BOOK -> Table Name]
		// 				  - "from Book" [Hibernate Query Language [HQL] - Book -> Entity Name ]
		List<Book> books 
			= session.createQuery("from MyBook").list();

		transaction.commit();
		
		return books;
	}


	@Transactional
	@Override
	public Book findById(int bookId) {

		Transaction transaction = session.beginTransaction();

		Book book = (Book) session.get(Book.class, bookId);
		
		transaction.commit();
		
		return book;
	}

	@Transactional
	public void update(Book book, String name, String author, String category) {
		
		Transaction transaction = session.beginTransaction();

		book.setName(name);
		book.setAuthor(author);
		book.setCategory(category);
		
		session.saveOrUpdate(book);
		
		transaction.commit();
	}
	
	@Transactional
	public void deleteById(int bookId) {
		
		Transaction transaction = session.beginTransaction();

		Book book = (Book) session.get(Book.class, bookId);
		
		session.delete(book);

		transaction.commit();
	}
	
	@Transactional
	public void add(Book newBook) {
		
		Transaction transaction = session.beginTransaction();

		session.saveOrUpdate(newBook);

		transaction.commit();				
	}
	
	
	@Transactional
	public List<Book> searchBy(String name, String author){
		
		Transaction transaction = session.beginTransaction();

		String hiberateQuery = "";
		
		
		if (name.length() != 0 && author.length() != 0) {
			
			// name like '%A%'
			hiberateQuery = "from MyBook where name like '%" + name + "%'" +
					" or author like '%" + author + "%'";				
		}else if (name.length() != 0) {
			hiberateQuery = "from MyBook where name like '%" + name + "%'";
		}else if (author.length() != 0) {
			hiberateQuery = "from MyBook where author like '%" + author + "%'";
		}else {
			System.out.println("Control wont come here...");
		}
		
		List<Book> books = session.createQuery(hiberateQuery).list();
		
		transaction.commit();				

		return books;
	}
	
	
}

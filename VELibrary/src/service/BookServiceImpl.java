package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Author;
import domain.Book;
import domain.BookCopy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class BookServiceImpl implements IBookService {
    @Override
    public List<Book> listAllBook() {
        DataAccess dataaccess = new DataAccessFacade();
        HashMap<String, Book> maps = dataaccess.readBooksMap();
        return maps != null ? maps.values().stream().collect(Collectors.toList()) : new ArrayList<Book>();
    }

	private DataAccess dataaccess = new DataAccessFacade();
    
	@Override
    public Book addNewBook(Book book) {
        dataaccess.saveNewBook(book);
        return book;
    }


	@Override
	public Book find(String isbn) {
		DataAccess dataAccess = new DataAccessFacade();
		return dataAccess.findBook(isbn);
	}

	@Override
	public boolean available(String isbn) {
		if(!dataaccess.existBook(isbn)) {
			return false;
		}
		
		return dataaccess.hasAvailableBookCopy(isbn);
	}

	@Override
	public int getMaxCheckoutLength(String isbn) {
		return dataaccess.findBook(isbn).getMaxCheckoutLength();
	}

	@Override
	public BookCopy findCopy(String isbn) {
		return dataaccess.findCopy(isbn);
	}

	@Override
	public void saveBook(Book book) {
		dataaccess.saveBook(book);
		
	}

	@Override
	public void addBookCopy(String isbn) {
		Book book = find(isbn);
		book.addCopy();
		saveBook(book);
	}

}

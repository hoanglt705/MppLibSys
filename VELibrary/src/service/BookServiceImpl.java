package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Author;
import domain.Book;

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

    @Override
    public Book addNewBook(Book book) {
        DataAccess dataaccess = new DataAccessFacade();
        dataaccess.saveNewBook(book);
        return book;
    }


	@Override
	public Book find(String isbn) {
		DataAccess dataAccess = new DataAccessFacade();
		return dataAccess.findBook(isbn);
	}

}

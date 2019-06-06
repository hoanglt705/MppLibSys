package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.BookCopy;

/**
 * Created by haupham on 6/5/19.
 */
public class BookServiceImpl implements IBookService {

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

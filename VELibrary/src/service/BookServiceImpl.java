package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;

/**
 * Created by haupham on 6/5/19.
 */
public class BookServiceImpl implements IBookService {
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

package service;

import domain.Book;
import domain.BookCopy;

/**
 * Created by haupham on 6/5/19.
 */
public interface IBookService {
	public Book addNewBook(Book book);
	public Book find(String isbn);
	public BookCopy findCopy(String isbn);
	public boolean available(String isbn);
	public int getMaxCheckoutLength(String isbn);
	public void saveBook(Book book);
	public void addBookCopy(String isbn);
}

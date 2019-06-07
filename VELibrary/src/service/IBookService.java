package service;

import domain.Book;
import domain.BookCopy;

import java.util.List;


public interface IBookService {
	public Book addNewBook(Book book);
	public Book find(String isbn);
    public List<Book> listAllBook();
	public BookCopy findCopy(String isbn);
	public boolean available(String isbn);
	public int getMaxCheckoutLength(String isbn);
	public void saveBook(Book book);
	public void addBookCopy(String isbn);
	public long countAvailable(String isbn);
	public void returnBook(String isbn);
    public boolean existBook(String isbn);
}

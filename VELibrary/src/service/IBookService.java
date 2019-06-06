package service;

import domain.Book;

import java.util.List;


public interface IBookService {
	public Book addNewBook(Book book);
	public Book find(String isbn);
    public List<Book> listAllBook();

}

package dataaccess;

import java.util.HashMap;

import domain.Author;
import domain.Book;
import domain.BookCopy;
import domain.CheckoutRecord;
import domain.CheckoutRecordEntry;
import domain.Member;
import domain.User;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, Member> readMemberMap();
	public HashMap<String, Author> readAuthorsMap();

	public Author saveNewAuthor(Author author);
	public String saveNewMember(Member member);
	public Book saveNewBook(Book book);
	public boolean existMember(String memberId);
	public Book findBook(String isbn);
	public boolean existBook(String isbn);
	public boolean hasAvailableBookCopy(String isbn);
	public BookCopy findCopy(String isbn);
	public void setBookCopyAvailable(BookCopy book, boolean b);
	public void saveCheckoutRecord(CheckoutRecord checkoutRecord);
	public void saveBook(Book bookCopy);
}

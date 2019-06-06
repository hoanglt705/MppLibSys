package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class Book implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;
	private List<BookCopy> copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;

	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		this.copies = new ArrayList<BookCopy>();
		this.copies.add(new BookCopy(this, 1, true));
	}

	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<Integer>();
		for (BookCopy c : copies) {
			retVal.add(c.getCopyNum());
		}
		return retVal;

	}

	public void addCopy() {
		copies.add(new BookCopy(this, copies.size() + 1));
	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (ob.getClass() != getClass())
			return false;
		Book b = (Book) ob;
		return b.isbn.equals(isbn);
	}

	@Override
	public String toString() {
		return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength;
	}

	public int getNumCopies() {
		return copies.size();
	}

	public String getTitle() {
		return title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public BookCopy getCopy(int copyNum) {
		for (BookCopy c : copies) {
			if (copyNum == c.getCopyNum()) {
				return c;
			}
		}
		return null;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public List<BookCopy> getCopies() {
		return copies;
	}
}

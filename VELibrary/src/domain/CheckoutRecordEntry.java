package domain;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {
	private String id;
	private Date checkoutDate;
	private Date dueDate;
	private CheckoutRecord checkoutRecord;
	private BookCopy bookCopy;

	public CheckoutRecordEntry(CheckoutRecord checkoutRecord, BookCopy bookCopy, Date checkoutDate, Date dueDate) {
		this.checkoutRecord = checkoutRecord;
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}
}

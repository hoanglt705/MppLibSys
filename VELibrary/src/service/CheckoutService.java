package service;

import java.util.Collection;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.BookCopy;
import domain.CheckoutRecord;

public class CheckoutService implements ICheckoutService {
	private IBookService bookService = new BookServiceImpl();
	private DataAccess dataAccess = new DataAccessFacade();

	@Override
	public void save(CheckoutRecord checkoutRecord) {
		dataAccess.saveCheckoutRecord(checkoutRecord);
		checkoutRecord.getCheckoutRecords().stream().forEach(checkoutRecordEntry -> {
			BookCopy bookCopy = checkoutRecordEntry.getBookCopy();
			bookCopy.changeAvailability();
			Book book = bookCopy.getBook();
			bookService.saveBook(book);
		});
	}

	@Override
	public List<CheckoutRecord> findAll(String memberId) {
		return dataAccess.findAllCheckoutRecord(memberId);
	}

	@Override
	public Collection<CheckoutRecord> findAll() {
		return dataAccess.readCheckoutRecordMap().values();
	}

}

package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberId;
	private List<CheckoutRecordEntry> checkoutRecords;

	public CheckoutRecord(String memberId) {
		this.memberId = memberId;
		this.checkoutRecords = new ArrayList<>();
	}

	public String getMemberId() {
		return memberId;
	}

	public void addCheckoutRecordEntry(CheckoutRecordEntry... entry) {
		checkoutRecords.addAll(Arrays.asList(entry));
	}

	public List<CheckoutRecordEntry> getCheckoutRecords() {
		return checkoutRecords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		builder.append("\n*************** CHECKOUT RECORD " + id + " ***********************");
		builder.append("\n entry total: ").append(checkoutRecords.size());
		for (int i=0; i<checkoutRecords.size(); i++) {
			CheckoutRecordEntry entry = checkoutRecords.get(i);
			builder.append("\n****************************** ENTRY ").append(i + 1).append(" ***************************");
			builder.append("\n\t title: ").append(entry.getBookCopy().getBook().getTitle());
			builder.append("\n\t checkout date: ").append(simpleDateFormat.format(entry.getCheckoutDate()));
			builder.append("\n\t due date: ").append(simpleDateFormat.format(entry.getDueDate()));
			builder.append("\n*******************************************************************");
		}
		checkoutRecords.stream().forEach(entry -> {
		});
		return builder.toString();
	}

}

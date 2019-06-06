package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutRecord implements Serializable{
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

}

package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutRecord {
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

}

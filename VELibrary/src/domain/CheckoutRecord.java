package domain;

import java.util.List;

public class CheckoutRecord {
    private List<CheckoutRecordEntry> checkoutRecords;

    public CheckoutRecord(List<CheckoutRecordEntry> checkoutRecords) {
        this.checkoutRecords = checkoutRecords;
    }
}

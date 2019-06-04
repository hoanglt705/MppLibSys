package domain;


import java.util.Date;

public class CheckoutRecordEntry {
    private Date checkoutDate;
    private Date dueDate;
    CheckoutRecord checkoutRecord;


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

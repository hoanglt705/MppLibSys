package context;

import domain.CheckoutRecord;
import domain.User;

public class AppContext  {

    private static AppContext single_instance = null;

    public User user;

	private CheckoutRecord checkoutRecord;

    private AppContext()
    {
        user = new User();
    }

    public static AppContext getInstance()
    {
        if (single_instance == null)
            single_instance = new AppContext();

        return single_instance;
    }

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
		
	}
}

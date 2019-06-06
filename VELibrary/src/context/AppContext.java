package context;

import domain.Author;
import domain.CheckoutRecord;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class AppContext  {

    private static AppContext single_instance = null;

    public User user;

	private CheckoutRecord checkoutRecord;
    private List<Author> selectedAuthor;

    private AppContext()
    {
        user = new User();
        selectedAuthor = new ArrayList<Author>();
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


    public void setUser(User user) {
        this.user = user;
    }

    public void setSelectedAuthor(List<Author> selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

    public User getUser() {
        return user;
    }

    public List<Author> getSelectedAuthor() {
        return selectedAuthor;
    }

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}


}

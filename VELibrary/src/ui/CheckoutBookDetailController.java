package ui;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import context.AppContext;
import domain.Book;
import domain.BookCopy;
import domain.CheckoutRecord;
import domain.CheckoutRecordEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BookServiceImpl;
import service.IBookService;

public class CheckoutBookDetailController implements Initializable {
	private IBookService bookService = new BookServiceImpl();
	private ObservableList<BookDetail> data;

	@FXML
	private JFXButton btnAddToList;

	@FXML
	private JFXButton btnCheckout;

	@FXML
	private JFXTextField txtBookId;

	@FXML
	private Label lblBookDetail;

	@FXML
	private TableView<BookDetail> tableView;

	@FXML
	TableColumn<BookDetail, String> isbnColumn;

	@FXML
	TableColumn<BookDetail, String> titleColumn;

	@FXML
	void addToList(ActionEvent event) {
		String bookId = txtBookId.getText();
		Book book = bookService.find(bookId);
		data.add(new BookDetail(bookId, book.getTitle()));
		txtBookId.clear();
	}

	@FXML
	void checkout(ActionEvent event) {
		data.stream().forEach(this::checkout);
		CheckoutRecord checkoutRecord = AppContext.getInstance().getCheckoutRecord();
		if(checkoutRecord.getCheckoutRecords().isEmpty())
		{
			Alert a = new Alert(AlertType.WARNING);
	    	a.setHeaderText("There is no available book");
	    	a.showAndWait();
	    	clear();
	    	return;
		}
		
		Alert a = new Alert(AlertType.INFORMATION);
    	a.setHeaderText("Checkout successfully");
    	a.setAlertType(AlertType.INFORMATION);
    	a.showAndWait();
    	clear();
	}
	
	private void clear() {
		data.clear();
		txtBookId.clear();
	}

	void checkout(BookDetail detail) {
		String isbn = detail.getIsbn();
		boolean available = bookService.available(isbn);
		if(!available) {
			//TODO show error here, maybe dead code
			return;
		}
		int maxCheckoutLength = bookService.getMaxCheckoutLength(isbn);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, maxCheckoutLength);
		Date dueDate = calendar.getTime();
		Date checkoutDate = new Date();
		CheckoutRecord checkoutRecord = AppContext.getInstance().getCheckoutRecord();
		
		BookCopy bookCopy = bookService.findCopy(isbn);
		
		CheckoutRecordEntry recordEntry = new CheckoutRecordEntry(checkoutRecord, bookCopy, checkoutDate, dueDate);
		checkoutRecord.addCheckoutRecordEntry(recordEntry);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
		data = FXCollections.observableArrayList();
		tableView.setItems(data);
	}

	public static class BookDetail {
		private final SimpleStringProperty isbn;
		private final SimpleStringProperty title;

		private BookDetail(String isbn, String title) {
			this.isbn = new SimpleStringProperty(isbn);
			this.title = new SimpleStringProperty(title);
		}

		public String getIsbn() {
			return isbn.get();
		}

		public String getTitle() {
			return title.get();
		}

	}
}

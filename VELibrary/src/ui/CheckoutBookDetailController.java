package ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import domain.Book;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BookServiceImpl;
import service.IBookService;

public class CheckoutBookDetailController implements Initializable {

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
		IBookService bookService = new BookServiceImpl();
		Book book = bookService.find(bookId);
		data.add(new BookDetail(bookId, book.getTitle()));
		txtBookId.clear();
	}

	@FXML
	void checkout(ActionEvent event) {
		data.stream().forEach(bookDetail -> {
			
		});
	}
	
	void checkout(BookDetail detail) {
		
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

package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import domain.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import service.BookServiceImpl;
import service.IBookService;

public class CopyBookController {

	@FXML
	private JFXTextField txtISBN;

	@FXML
	private JFXButton btnAddBookCopy;

	@FXML
	void addBookCopy() {
		String isbn = txtISBN.getText();
		IBookService service = new BookServiceImpl();
		Book book = service.find(isbn);
		if(book == null) {
			Alert a = new Alert(AlertType.ERROR);
	    	a.setHeaderText("Book does not exist");
	    	a.showAndWait();
	    	clear();
	    	return;
		}
		service.addBookCopy(isbn);
		clear();
	}

	private void clear() {
		txtISBN.clear();
	}
}

package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import service.BookServiceImpl;
import service.IBookService;

public class ReturnBookController {

	IBookService bookService = new BookServiceImpl();

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXButton btnReturnBook;

    @FXML
    void returnBook(ActionEvent event) {
    	String isbn = txtBookId.getText();
    	if(!bookService.available(isbn)) {
    		Alert a = new Alert(AlertType.ERROR);
        	a.setHeaderText("Isbn is invalid");
        	a.showAndWait();
        	return;
    	}
    	
    	bookService.returnBook(txtBookId.getText());
    	
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setHeaderText("Book is returned");
    	a.showAndWait();
    	
    	txtBookId.clear();
    }

}

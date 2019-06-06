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

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXButton btnReturnBook;

    @FXML
    void returnBook(ActionEvent event) {
    	IBookService bookService = new BookServiceImpl();
    	bookService.returnBook(txtBookId.getText());
    	
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setHeaderText("Book is returned");
    	a.setAlertType(AlertType.INFORMATION);
    	a.showAndWait();
    	
    	txtBookId.clear();
    }

}

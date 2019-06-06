package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class CopyBookController {

	@FXML
	private JFXTextField txtISBN;

	@FXML
	private JFXButton btnAddBookCopy;

	@FXML
	void addBookCopy() {
		System.out.println(txtISBN.getText());
		txtISBN.clear();
	}
}

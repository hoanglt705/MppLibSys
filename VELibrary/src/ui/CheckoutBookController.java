package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CheckoutBookController {

	@FXML
	private JFXTextField txtMemberId;

	@FXML
	private JFXButton btnCheckoutBookDetail;

	@FXML
	private Label lblMemberFullName;

	@FXML
	private DatePicker borrowDate;

	@FXML
	void addBookDetail(ActionEvent event) throws IOException {
		Stage stage = new Stage();

		Parent root = (Parent) FXMLLoader.load(getClass().getResource("../ui/CheckoutBookDetail.fxml"));
		stage.setScene(new Scene(root));
		stage.showAndWait();
	}

}

package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private AnchorPane mainScreen;

	@FXML
	private Button btnAddBook;

	@FXML
	private Button btnCheckoutBook;

	@FXML
	private Button btnRegisterMember;

	@FXML
	private Button btnReport;

	@FXML
	private Button btnReturnBook;

	@FXML
	private JFXButton btnLogout;

	@FXML
	void registerMember(ActionEvent event) throws IOException {
		displayScreen("../ui/AddMember.fxml");
	}

	@FXML
	void returnBook(ActionEvent event) throws IOException {
		displayScreen("../ui/ReturnBook.fxml");
	}

	@FXML
	void checkoutBook(ActionEvent event) throws IOException {
		displayScreen("../ui/CheckoutBook.fxml");
	}

	@FXML
	void addBook(ActionEvent event) throws IOException {
		displayScreen("../ui/AddBook.fxml");
	}

	@FXML
	void generateReport(ActionEvent event) throws IOException {
		displayScreen("../ui/Report.fxml");
	}

	public void initialize() {
		try {
			displayScreen("../ui/CheckoutBook.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		// validate login...
		Stage stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();

		stage = new Stage();

		Parent screen = (Parent) FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage.setScene(new Scene(screen));
		stage.show();
	}

	void displayScreen(String fxmlPath) throws IOException {
		AnchorPane screen = FXMLLoader.load(getClass().getResource(fxmlPath));
		mainScreen.getChildren().clear();
		mainScreen.getChildren().add(screen);
	}

}
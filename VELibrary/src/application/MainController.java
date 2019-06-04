package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

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

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
//    	try {
//			displayScreen("../ui/ViewIssuedBooks.fxml");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	void displayScreen(String fxmlPath) throws IOException {
		AnchorPane screen = FXMLLoader.load(getClass().getResource(fxmlPath));
		mainScreen.getChildren().clear();
		mainScreen.getChildren().add(screen);
	}

}
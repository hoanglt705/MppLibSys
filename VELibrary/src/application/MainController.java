package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    void registerMember(ActionEvent event) throws IOException {
    	AnchorPane registerMemberScreen = FXMLLoader.load(getClass().getResource("../ui/AddMember.fxml"));
    	mainScreen.getChildren().clear();
    	mainScreen.getChildren().add(registerMemberScreen);
    }

    @FXML
    void returnBook(ActionEvent event) {

    }

    @FXML
    void checkoutBook(ActionEvent event) {

    }

    @FXML
    void addBook(ActionEvent event) throws IOException{
    	AnchorPane addBookScreen = FXMLLoader.load(getClass().getResource("../ui/AddBook.fxml"));
    	mainScreen.getChildren().clear();
    	mainScreen.getChildren().add(addBookScreen);
    }

    @FXML
    void generateReport(ActionEvent event) {

    }

}
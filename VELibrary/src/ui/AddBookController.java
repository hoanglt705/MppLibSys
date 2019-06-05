package ui;

import java.io.IOException;
import java.util.Arrays;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import domain.Address;
import domain.Author;
import domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.AuthorServiceImpl;
import service.BookServiceImpl;
import service.IAuthorService;
import service.IBookService;

public class AddBookController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAddBook;

    @FXML
    private JFXTextField isbn;

    @FXML
    private JFXButton btnAddAuthor;

    @FXML
    void addBook(ActionEvent event) {


    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void showAddAuthorScreen(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	
    	Parent root = (Parent) FXMLLoader.load(getClass().getResource("../ui/AddAuthor.fxml"));
    	stage.initStyle(StageStyle.UTILITY);
    	stage.setScene(new Scene(root));
    	stage.showAndWait();
    }

}

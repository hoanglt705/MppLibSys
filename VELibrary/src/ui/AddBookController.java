package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import context.AppContext;
import domain.Author;
import domain.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.*;

public class AddBookController implements Initializable {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAddBook;

    @FXML
    private JFXTextField isbn;

    @FXML
    private JFXTextField title;

    @FXML
    private Label lblMessage;

    @FXML
    private JFXTextField maxlength;

    @FXML
    private JFXButton btnAddAuthor;

    @FXML
    private TableView<Author> tbvAuthor;

    @FXML
    TableColumn<Author, String> fnColumn;

    @FXML
    TableColumn<Author, String> lnColumn;

    @FXML
    TableColumn<Author, String> bioColumn;



    private ObservableList<Author> data;

    private List<Author> authorList = new ArrayList<>();
    @FXML
    void addBook(ActionEvent event) {

        try{
            IAuthorService s = new AuthorServiceImpl();
            int num = Integer.parseInt(maxlength.getText());
            Book abook = new Book(isbn.getText(), title.getText(), num , authorList);
            IBookService service = new BookServiceImpl();
            Book a = service.addNewBook(abook);
            lblMessage.setText("Book is added successfully!");
        }catch (Exception e){
            System.out.println("Add book error:" + e.getMessage());
            lblMessage.setText("Can't add this book!");
        }
        clear();
    }

    private void clear(){
        isbn.clear();
        title.clear();
        maxlength.clear();
        data.clear();
    }


    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void showAddAuthorScreen(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	
    	Parent root = (Parent) FXMLLoader.load(getClass().getResource("../ui/SelectAuthor.fxml"));
    	stage.initStyle(StageStyle.UTILITY);
    	stage.setScene(new Scene(root));
    	stage.showAndWait();

        authorList.addAll(AppContext.getInstance().getSelectedAuthor());
        data.addAll(authorList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        bioColumn.setCellValueFactory(new PropertyValueFactory<>("bio"));
        data = FXCollections.observableArrayList();


        data.addAll(AppContext.getInstance().getSelectedAuthor());
        tbvAuthor.setItems(data);
    }
}

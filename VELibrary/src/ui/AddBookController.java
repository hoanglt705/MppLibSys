package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import com.jfoenix.validation.NumberValidator;
import context.AppContext;
import context.ValidationUtils;
import domain.Author;
import domain.Book;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.AuthorServiceImpl;
import service.BookServiceImpl;
import service.IAuthorService;
import service.IBookService;

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

            if(validateForm()) {


                IAuthorService s = new AuthorServiceImpl();
                int num = Integer.parseInt(maxlength.getText());
                Book abook = new Book(isbn.getText(), title.getText(), num, authorList);
                IBookService service = new BookServiceImpl();
                Book b = service.addNewBook(abook);

                // lblMessage.setText("Book is added successfully!");
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText(String.format("Book %s", b.getIsbn() + ":" + b.getTitle()) + " is added successfully.");
                a.setAlertType(Alert.AlertType.INFORMATION);

                Optional<ButtonType> alert = a.showAndWait();
                if (alert.get() == ButtonType.OK) {
                    clear();
                }
            }

        }catch (Exception e){
            System.out.println("Add book error:" + e.getMessage());
            lblMessage.setText("Can't add this book!");
        }


    }


    private boolean validateForm(){

        if("".equals(isbn.getText()) || "".equals(isbn.getText().trim())){
            lblMessage.setText("ISBN is invalid");
            return false;
        }

        IBookService service = new BookServiceImpl();

        if(service.existBook(isbn.getText())){
            lblMessage.setText("ISBN is existed");
            return false;
        }

        if("".equals(title.getText()) || "".equals(title.getText().trim())){
            lblMessage.setText("Title is invalid");
            return false;
        }
        if(!ValidationUtils.isNumberOnly(maxlength.getText())){
            lblMessage.setText("Checkout length is invalid");
            return false;
        }

        if(data == null || data.isEmpty()){
            lblMessage.setText("Please add a author");
            return false;
        }

        return true;
    }



    private void clear(){
        isbn.clear();
        title.clear();
        maxlength.clear();
        data.clear();
        lblMessage.setText("");
    }


    @FXML
    void showAddAuthorScreen(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	
    	Parent root = (Parent) FXMLLoader.load(getClass().getResource("../ui/SelectAuthor.fxml"));
    	stage.initStyle(StageStyle.UTILITY);
    	stage.setScene(new Scene(root));
    	stage.showAndWait();
        data.addAll(AppContext.getInstance().getSelectedAuthor());
        authorList.addAll(AppContext.getInstance().getSelectedAuthor());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        bioColumn.setCellValueFactory(new PropertyValueFactory<>("bio"));
        data = FXCollections.observableArrayList();

        //data.addAll(AppContext.getInstance().getSelectedAuthor());
        tbvAuthor.setItems(data);

    }
}

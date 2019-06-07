package ui;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import context.ValidationUtils;
import domain.Address;
import domain.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.AuthorServiceImpl;
import service.IAuthorService;

public class AddAuthorController {

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtCredentials;


    @FXML
    private JFXTextField txtZip;

    @FXML
    private JFXButton btnAddAuthor;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextArea txtShortBio;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtState;

    @FXML
    private Label lblMessage;

    @FXML
    void addAuthor(ActionEvent event) {

        if(validateForm()) {

            Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
            Author author = new Author(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), address, txtShortBio.getText());
            IAuthorService service = new AuthorServiceImpl();
            Author newAuthor = service.createNewAuthor(author, address);

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(String.format("Author %s", newAuthor.getFirstName() + " is added successfully!"));
            a.setAlertType(Alert.AlertType.INFORMATION);

            Optional<ButtonType> alert = a.showAndWait();
            if (alert.get() == ButtonType.OK) {
                clear();
            }
        }
    }

    private boolean validateForm(){

        if("".equals(txtFirstName.getText()) || "".equals(txtFirstName.getText().trim())){
            lblMessage.setText("First Name is invalid");
            return false;
        }

        if("".equals(txtLastName.getText()) || "".equals(txtLastName.getText().trim())){
            lblMessage.setText("Last Name is invalid");
            return false;
        }

        if(!ValidationUtils.isZipcode(txtZip.getText())){
            lblMessage.setText("Zipcode is invalid");
            return false;
        }
        if(!ValidationUtils.isValidPhoneNo(txtPhone.getText())){
            lblMessage.setText("Phone Number is invalid");
            return false;
        }


        return true;
    }

    private void clear(){
        txtFirstName.clear();
        txtLastName.clear();
        txtShortBio.clear();
        txtStreet.clear();
        txtState.clear();
        txtZip.clear();
        txtCity.clear();
        txtPhone.clear();
        lblMessage.setText("");
    }



}

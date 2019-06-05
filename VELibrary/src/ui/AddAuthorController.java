package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddAuthorController {

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtCredentials;

    @FXML
    private JFXButton btnCancel;

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
    void addAuthor(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
    	stage.close();
    }

}

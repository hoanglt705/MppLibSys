package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField userName;

    @FXML
    void loginClick(ActionEvent event) {

    }

    @FXML
    void cancelClick(ActionEvent event) {

    	Stage stage = (Stage)btnCancel.getScene().getWindow();
    	stage.close();
    }

}
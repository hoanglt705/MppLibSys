package ui;

import java.awt.event.KeyListener;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UserServiceImp;

import javax.security.auth.login.LoginException;

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
	private Label lblMessage;

	@FXML
	void loginClick(ActionEvent event) throws IOException {
		//validate login...
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		//stage.close();
		//stage = new Stage();

		UserServiceImp userService = new UserServiceImp();

		try {
			userService.login(userName.getText(), password.getText());
			Parent screen = (Parent) FXMLLoader.load(getClass().getResource("Main.fxml"));
			stage.setScene(new Scene(screen));
			stage.setResizable(false);
			stage.show();
		} catch (LoginException e) {
			System.out.println("Login error >>" + e.getMessage());
			lblMessage.setText(e.getMessage());
		}

	}

	@FXML
	void cancelClick(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

}
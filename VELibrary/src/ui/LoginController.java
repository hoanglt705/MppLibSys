package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.LoginException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UserServiceImp;

public class LoginController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
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
	private AnchorPane mainScreen;

	@FXML
	void loginClick(ActionEvent event) throws IOException {
		// validate login...
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		// stage.close();
		// stage = new Stage();

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainScreen.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();

			}
		});

		mainScreen.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mainScreen.getScene().getWindow().setX(event.getScreenX() - xOffset);
				mainScreen.getScene().getWindow().setY(event.getScreenY() - yOffset);
			}

		});
	}
}
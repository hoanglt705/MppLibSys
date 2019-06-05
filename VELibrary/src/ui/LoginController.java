package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	void loginClick(ActionEvent event) throws IOException {
		//validate login...
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
		
		stage = new Stage();

		Parent screen = (Parent) FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage.setScene(new Scene(screen));

		stage.setResizable(false);
		stage.show();
	}

	@FXML
	void cancelClick(ActionEvent event) {

		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

}
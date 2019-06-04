package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ReturnBook extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ReturnBook.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());

			primaryStage.initStyle(StageStyle.TRANSPARENT);

			primaryStage.resizableProperty().setValue(Boolean.FALSE);

			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
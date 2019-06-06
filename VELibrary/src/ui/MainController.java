package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import context.AppContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import domain.*;

public class MainController implements Initializable{

	private double xOffset = 0;
	private double yOffset = 0;
	
	@FXML
	private AnchorPane mainScreen;

	@FXML
	private Button btnAddBook;

	@FXML
	private Button btnCheckoutBook;

	@FXML
	private Button btnRegisterMember;

	@FXML
	private Button btnReport;

	@FXML
	private Button btnReturnBook;
	
	@FXML
	private Button btnAddCopyBook;

    @FXML
    private Button btnAddAuthor;

	@FXML
	private JFXButton btnLogout;
	
	@FXML
    private Label lblCurUsr;

	@FXML
	void registerMember(ActionEvent event) throws IOException {
		displayScreen("AddMember.fxml");
	}

	@FXML
	void returnBook(ActionEvent event) throws IOException {
		displayScreen("ReturnBook.fxml");
	}

	@FXML
	void checkoutBook(ActionEvent event) throws IOException {
		displayScreen("CheckoutBook.fxml");
	}

	@FXML
	void addBook(ActionEvent event) throws IOException {
		displayScreen("AddBook.fxml");
	}

	@FXML
	void generateReport(ActionEvent event) throws IOException {
		displayScreen("Report.fxml");
	}
	
	@FXML
	void addCopyBook(ActionEvent event) throws IOException {
		displayScreen("CopyBook.fxml");
	}

    @FXML
    void addAuthor(ActionEvent event) throws IOException{
        displayScreen("AddAuthor.fxml");
    }

	public void initialize() {
		try {
			displayScreen("CheckoutBook.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AppContext context = AppContext.getInstance();
        User user = context.getUser() == null ? new User() : context.getUser();

        lblCurUsr.setText(user.getId() + " [" + context.getUser().getRoles() +"]" );
        if(Role.ADMIN.equals(user.getRoles())){
            btnRegisterMember.setDisable(true);
            btnAddBook.setDisable(false);
            btnCheckoutBook.setDisable(true);
            btnReturnBook.setDisable(true);
            btnReport.setDisable(true);
            btnAddCopyBook.setDisable(false);
            btnAddAuthor.setDisable(false);
        }else if(Role.LIBRARIAN.equals(user.getRoles())){
            btnRegisterMember.setDisable(false);
            btnAddBook.setDisable(true);
            btnCheckoutBook.setDisable(false);
            btnReturnBook.setDisable(false);
            btnReport.setDisable(false);
            btnAddCopyBook.setDisable(true);
            btnAddAuthor.setDisable(true);
        }else if(Role.BOTH.equals(user.getRoles())){
            btnRegisterMember.setDisable(false);
            btnAddBook.setDisable(false);
            btnCheckoutBook.setDisable(false);
            btnReturnBook.setDisable(false);
            btnReport.setDisable(false);
            btnAddCopyBook.setDisable(false);
            btnAddAuthor.setDisable(false);
        }
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		// validate login...
		Stage stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();

		stage = new Stage();

		Parent screen = (Parent) FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage.setScene(new Scene(screen));
		stage.show();
	}

	void displayScreen(String fxmlPath) throws IOException {
		AnchorPane screen = FXMLLoader.load(getClass().getResource(fxmlPath));
		mainScreen.getChildren().clear();
		mainScreen.getChildren().add(screen);
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initialize();
		
		//to allow users to drag the screen
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

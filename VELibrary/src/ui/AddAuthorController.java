package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import domain.Address;
import domain.Author;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.AuthorServiceImpl;
import service.IAuthorService;
import service.IMemberService;
import service.MemberServiceImpl;

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
        Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
        Author author = new Author(txtFirstName.getText(),txtLastName.getText(), txtPhone.getText(), address, txtShortBio.getText());
        IAuthorService service = new AuthorServiceImpl();
        Author newAuthor = service.createNewAuthor(author, address);

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(String.format("Author %s", newAuthor.getFirstName()));
            a.setContentText("Please provide this id to student");
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.showAndWait();

    }

    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
    	stage.close();
    }


}

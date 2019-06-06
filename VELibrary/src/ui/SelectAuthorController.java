package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import context.AppContext;
import domain.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AuthorServiceImpl;
import service.IAuthorService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectAuthorController implements Initializable {

	private ObservableList<Author> data;

	@FXML
	private JFXButton btnAddToList;

	@FXML
	private JFXButton btnAddAuthor;

	@FXML
	private JFXTextField txtBookId;

	@FXML
	private Label lblDetail;

	@FXML
	private TableView<Author> tableView;

	@FXML
	TableColumn<Author, String> fnColumn;

	@FXML
	TableColumn<Author, String> lnColumn;

    @FXML
    TableColumn<Author, String> bioColumn;

	@FXML
	void selectAuthor(ActionEvent event) {
        AppContext context = AppContext.getInstance();
        List<Author> selectedList = new ArrayList<>();

        selectedList = tableView.getSelectionModel().getSelectedItems();
        context.setSelectedAuthor(selectedList);

        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

        fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        bioColumn   .setCellValueFactory(new PropertyValueFactory<>("bio"));
        data = FXCollections.observableArrayList();

        IAuthorService service = new AuthorServiceImpl();
        List<Author> lsAuthors = service.getAllAuthors();

        data.addAll(lsAuthors);
		tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}



}

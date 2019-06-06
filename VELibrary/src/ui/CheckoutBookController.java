package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import context.AppContext;
import domain.CheckoutRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.CheckoutService;
import service.ICheckoutService;
import service.IMemberService;
import service.MemberServiceImpl;

public class CheckoutBookController {

	private ICheckoutService checkoutService = new CheckoutService();
	private IMemberService memberService = new MemberServiceImpl();
	
	@FXML
	private JFXTextField txtMemberId;

	@FXML
	private JFXButton btnCheckoutBookDetail;

	@FXML
	private Label lblMemberFullName;

	@FXML
	private DatePicker borrowDate;

	@FXML
	void addBookDetail(ActionEvent event) throws IOException {
		boolean exist = memberService.exist(txtMemberId.getText());
		if(!exist) {
			Alert a = new Alert(AlertType.WARNING);
	    	a.setHeaderText("The member id is not valid");
	    	a.showAndWait();
	    	return;
		}
		createCheckoutRecord();
		showCheckoutBookDetail();
		saveCheckoutRecord();
	}

	private void saveCheckoutRecord() {
		CheckoutRecord checkoutRecord = AppContext.getInstance().getCheckoutRecord();
		checkoutService.save(checkoutRecord);
		AppContext.getInstance().setCheckoutRecord(null);
	}

	private void createCheckoutRecord() {
		String memberId = txtMemberId.getText();
		CheckoutRecord checkoutRecord = new CheckoutRecord(memberId);
		AppContext.getInstance().setCheckoutRecord(checkoutRecord);
	}

	private void showCheckoutBookDetail() throws IOException {
		Stage stage = new Stage();
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("../ui/CheckoutBookDetail.fxml"));
		stage.setScene(new Scene(root));
		stage.showAndWait();
	}

}

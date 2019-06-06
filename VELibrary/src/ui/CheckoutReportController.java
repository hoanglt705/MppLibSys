package ui;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import domain.CheckoutRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import service.CheckoutService;
import service.ICheckoutService;
import service.IMemberService;
import service.MemberServiceImpl;

public class CheckoutReportController {
	IMemberService memberService = new MemberServiceImpl();

	@FXML
	private JFXTextField txtMemberId;

	@FXML
	private JFXButton btnSearch;

	@FXML
	void print(ActionEvent event) {
		String memberId = txtMemberId.getText();
		boolean exist = memberService.exist(memberId);
		if(!exist) {
			Alert a = new Alert(AlertType.ERROR);
	    	a.setHeaderText("The member id does not correct");
	    	a.setAlertType(AlertType.INFORMATION);
	    	a.showAndWait();
	    	return;
		}
		
		printToConsole(memberId);
	}

	private void printToConsole(String memberId) {
		ICheckoutService checkoutService = new CheckoutService();
		checkoutService.findAll(memberId).stream().forEach(System.out::println);
	}
}

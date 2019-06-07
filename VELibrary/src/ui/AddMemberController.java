package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import context.ValidationUtils;
import domain.Address;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.IMemberService;
import service.MemberServiceImpl;

public class AddMemberController {
	@FXML
	private AnchorPane mainScreen;
	
    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtState;

    @FXML
    private JFXTextField txtZip;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private Label lblMessage;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAdd;

    @FXML
    void addMember(ActionEvent event) {

        if(validateForm()) {
            Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
            Member member = new Member(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), address);
            IMemberService service = new MemberServiceImpl();
            String memberId = service.createMember(member, address);

            Alert a = new Alert(AlertType.INFORMATION);
            a.setHeaderText(String.format("Member id is %s", memberId));
            a.setContentText("Please provide this id to member");
            a.setAlertType(AlertType.INFORMATION);
            a.showAndWait();
            clear();
        }
    }

    private boolean validateForm(){


        if("".equals(txtFirstName.getText()) || "".equals(txtFirstName.getText().trim())){
            lblMessage.setText("First Name is required");
            return false;
        }
        if("".equals(txtLastName.getText()) || "".equals(txtLastName.getText().trim())){
            lblMessage.setText("Last Name is required");
            return false;
        }
        if(!ValidationUtils.isNumberOnly(txtZip.getText())){
            lblMessage.setText("Zipcode is invalid");
            return false;
        }
        if(!ValidationUtils.isValidPhoneNo(txtPhone.getText())){
            lblMessage.setText("Phone Numer is invalid");
            return false;
        }

        return true;
    }

    private void clear() {
    	txtFirstName.clear();
    	txtLastName.clear();
    	txtCity.clear();
    	txtState.clear();
    	txtStreet.clear();
    	txtPhone.clear();
    	txtZip.clear();

	}

	@FXML
    void cancel(ActionEvent event) {
    }

}

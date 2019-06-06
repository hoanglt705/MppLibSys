package ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer.Record;

import domain.Book;
import domain.CheckoutRecord;
import domain.CheckoutRecordEntry;
import domain.Member;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BookServiceImpl;
import service.CheckoutService;
import service.IBookService;
import service.ICheckoutService;
import service.IMemberService;
import service.MemberServiceImpl;

public class CheckoutReportController implements Initializable{
	private IMemberService memberService = new MemberServiceImpl();
	private ICheckoutService checkoutService = new CheckoutService();

	@FXML
	private JFXTextField txtMemberId;

	@FXML
	private JFXButton btnSearch;
	
	@FXML
	private Label lblConsole;
	
	@FXML
	private TableView<BookDetail> tblBookList;

	@FXML
	TableColumn<BookDetail, String> colISBN;

	@FXML
	TableColumn<BookDetail, String> colTitle;

	@FXML
	TableColumn<BookDetail, String> colLoanPeriod;
	
	
	@FXML
	private TableView<MemberDetail> tblMemberList;

	@FXML
	TableColumn<MemberDetail, String> colId;

	@FXML
	TableColumn<MemberDetail, String> colFirstname;

	@FXML
	TableColumn<MemberDetail, String> colLastName;

	@FXML
	TableColumn<MemberDetail, String> colPhoneNumber;
	
	@FXML
	private JFXTextField txtISBN;

	@FXML
	private JFXButton btnOverDueSearch;

	@FXML
	TableColumn<OverDueDetail, String> colOverDueISBN;

	@FXML
	TableColumn<OverDueDetail, String> colOverDueTitle;
	
	@FXML
	TableColumn<OverDueDetail, String> colOverDueCopyNo;

	@FXML
	TableColumn<OverDueDetail, String> colOverDueStatus;
	
	
	@FXML
	void print(ActionEvent event) {
		String memberId = txtMemberId.getText();
		boolean exist = memberService.exist(memberId);
		if (!exist) {
			alertNotExist();
			return;
		}

		printToConsole(memberId);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUpBookTableView();
		setUpMemberTableView();
		setUpOverdueTableView();
	}

	private void setUpOverdueTableView() {
		colOverDueISBN.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
		colOverDueTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colOverDueCopyNo.setCellValueFactory(new PropertyValueFactory<>("CopyNo"));
		colOverDueStatus.setCellValueFactory(new PropertyValueFactory<>("OverdueStatus"));
		
		ICheckoutService checkoutService = new CheckoutService();
		Collection<CheckoutRecord> checkoutRecords = checkoutService.findAll();
		Collection<CheckoutRecordEntry> allEntries = new ArrayList<CheckoutRecordEntry>();
		checkoutRecords.stream().forEach(record -> {
			allEntries.addAll(record.getCheckoutRecords());
		});
		
//		allEntries.stream().map(entry -> {
//			String isbn = entry.getBookCopy().getBook().getIsbn();
//			String title = entry.getBookCopy().getBook().getTitle();
//			String copyNum = entry.getBookCopy().getCopyNum();
//			return new OverDueDetail(isbn, title, copyNo, overdueStatus);
//		});
//		List<OverDueDetail> bookDetails = checkoutRecords.stream().map(member-> {
//			return new MemberDetail(member.getMemberId(), member.getFirstName(), member.getLastName(), member.getTelephone());
//		}).collect(Collectors.toList());
//		ObservableList<MemberDetail> data = FXCollections.observableArrayList();
//		data.addAll(bookDetails);
//		tblMemberList.setItems(data);
	}

	private void setUpMemberTableView() {
		colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
		IMemberService bookService = new MemberServiceImpl();
		Collection<Member> members = bookService.listAllMember();
		List<MemberDetail> bookDetails = members.stream().map(member-> {
			return new MemberDetail(member.getMemberId(), member.getFirstName(), member.getLastName(), member.getTelephone());
		}).collect(Collectors.toList());
		ObservableList<MemberDetail> data = FXCollections.observableArrayList();
		data.addAll(bookDetails);
		tblMemberList.setItems(data);
	}

	private void setUpBookTableView() {
		colISBN.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colLoanPeriod.setCellValueFactory(new PropertyValueFactory<>("LoanPeriod"));
		IBookService bookService = new BookServiceImpl();
		List<Book> books = bookService.listAllBook();
		List<BookDetail> bookDetails = books.stream().map(book-> {
			return new BookDetail(book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength());
		}).collect(Collectors.toList());
		ObservableList<BookDetail> data = FXCollections.observableArrayList();
		data.addAll(bookDetails);
		tblBookList.setItems(data);
	}

	private void alertNotExist() {
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("The member id does not correct");
		a.setAlertType(AlertType.INFORMATION);
		a.showAndWait();
	}

	private void printToConsole(String memberId) {
		List<CheckoutRecord> checkoutRecords = checkoutService.findAll(memberId);
		
		if (checkoutRecords.isEmpty()) {
			System.out.println("There is no checkout record for this member id");
			return;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("\n PRINT RECORD OF MEMBER ").append(memberId);
		checkoutRecords.stream().forEach(record -> {
			builder.append(record);
		});
		lblConsole.setText(builder.toString());
	}
	
	public static class BookDetail {
		private final SimpleStringProperty isbn;
		private final SimpleStringProperty title;
		private final SimpleIntegerProperty loanPeriod;

		private BookDetail(String isbn, String title, int loanPeriod) {
			this.isbn = new SimpleStringProperty(isbn);
			this.title = new SimpleStringProperty(title);
			this.loanPeriod = new SimpleIntegerProperty(loanPeriod);
		}

		public String getIsbn() {
			return isbn.get();
		}

		public String getTitle() {
			return title.get();
		}

		public int getLoanPeriod() {
			return loanPeriod.get();
		}
	}
	
	public static class MemberDetail {
		private final SimpleStringProperty id;
		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty phone;

		private MemberDetail(String id, String firstName, String lastName, String phone) {
			this.id = new SimpleStringProperty(id);
			this.firstName = new SimpleStringProperty(firstName);
			this.lastName = new SimpleStringProperty(lastName);
			this.phone = new SimpleStringProperty(phone);
		}

		public String getId() {
			return id.get();
		}

		public String getFirstName() {
			return firstName.get();
		}

		public String getLastName() {
			return lastName.get();
		}

		public String getPhone() {
			return phone.get();
		}
	}
	
	public static class OverDueDetail {
		private final SimpleStringProperty isbn;
		private final SimpleStringProperty title;
		private final SimpleStringProperty copyNo;
		private final SimpleStringProperty overdueStatus;

		public OverDueDetail(String isbn, String title, String copyNo, String overdueStatus) {
			this.isbn = new SimpleStringProperty(isbn);
			this.title = new SimpleStringProperty(title);
			this.copyNo = new SimpleStringProperty(copyNo);
			this.overdueStatus = new SimpleStringProperty(overdueStatus);
		}

		public String getIsbn() {
			return isbn.get();
		}

		public String getTitle() {
			return title.get();
		}

		public String getCopyNo() {
			return copyNo.get();
		}

		public String getOverdueStatus() {
			return overdueStatus.get();
		}

	}
}

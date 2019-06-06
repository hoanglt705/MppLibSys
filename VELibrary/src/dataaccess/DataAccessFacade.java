package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import domain.Author;
import domain.Book;
import domain.BookCopy;
import domain.CheckoutRecord;
import domain.CheckoutRecordEntry;
import domain.Member;
import domain.User;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS, AUTHORS, CHECKOUT_RECORD;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "//src//dataaccess//storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	//implement: other save operations
	@Override
	public String saveNewMember(Member member) {
		HashMap<String, Member> mems = readMemberMap();
		Random random = new Random();
		String memberId = Integer.toString(random.nextInt(10000));
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
		return memberId;
	}

	@Override
	public Book saveNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		books.put(book.getIsbn(),book);
		saveToStorage(StorageType.BOOKS, books);
		return book;
	}

    @Override
    public Author saveNewAuthor(Author author) {
        HashMap<String, Author> authors = readAuthorsMap();
        if(authors == null)
            authors = new HashMap<String, Author>();
        Random random = new Random();
        String authId = Integer.toString(random.nextInt(10000));
        authors.put(authId, author);
        saveToStorage(StorageType.AUTHORS, authors);

        return author;
    }
    
    @SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Member> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> Member
		return (HashMap<String, Member>) readFromStorage(StorageType.MEMBERS);
	}
	
    @Override
	public HashMap<String, Author> readAuthorsMap() {
		return (HashMap<String, Author>) readFromStorage(StorageType.AUTHORS);

	}

    @Override
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CHECKOUT_RECORD);

	}

    
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	//static void loadMemberMap(List<Member> memberList) {
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<Member> memberList) {
		HashMap<String, Member> members = new HashMap<String, Member>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))){
			
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}

	@Override
	public boolean existMember(String memberId) {
		return readMemberMap().containsKey(memberId);
	}

	@Override
	public Book findBook(String isbn) {
		return readBooksMap().get(isbn);
	}

	@Override
	public boolean existBook(String isbn) {
		return readBooksMap().containsKey(isbn);
	}

	@Override
	public boolean hasAvailableBookCopy(String isbn) {
		// TODO Auto-generated method stub
		return readBooksMap().get(isbn).getCopies().stream().anyMatch(BookCopy::isAvailable);
	}

	@Override
	public BookCopy findCopy(String isbn) {
		Optional<BookCopy> bookCopy = readBooksMap().get(isbn).getCopies().stream().filter(BookCopy::isAvailable).findFirst();
		boolean existed = bookCopy.isPresent();
		return existed ? bookCopy.get() : null;
	}


	public static void loadCheckoutRecord(List<CheckoutRecordEntry> entries) {
		Map<String, CheckoutRecordEntry> users = new HashMap<>();
		entries.forEach(entry -> users.put(entry.getId(), entry));
		saveToStorage(StorageType.CHECKOUT_RECORD, users);
	}

	@Override
	public void setBookCopyAvailable(BookCopy bookCopy, boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCheckoutRecord(CheckoutRecord checkoutRecord) {
		HashMap<String, CheckoutRecord> records = readCheckoutRecordMap();
		Random random = new Random();
		String checkoutRecordId = Integer.toString(random.nextInt(10000));
		checkoutRecord.setId(checkoutRecordId);
		records.put(checkoutRecordId, checkoutRecord);
		saveToStorage(StorageType.CHECKOUT_RECORD, records);
	}

	@Override
	public void saveBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		books.put(book.getIsbn(),book);
		saveToStorage(StorageType.BOOKS, books);
	}

	@Override
	public List<CheckoutRecord> findAllCheckoutRecord(String memberId) {
		// TODO Auto-generated method stub
		return readCheckoutRecordMap().values().stream()
				.filter(checkoutRecord -> checkoutRecord.getMemberId().equals(memberId)).collect(Collectors.toList());
	}
	
}

package dataaccess;

import java.util.HashMap;

import domain.Book;
import domain.Member;
import domain.User;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, Member> readMemberMap();
	public String saveNewMember(Member member);
}

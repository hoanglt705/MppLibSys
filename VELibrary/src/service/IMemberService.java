package service;

import domain.Address;
import domain.Member;

public interface IMemberService {

	String createMember(Member member, Address address);
	
	boolean exist(String memberId);

}

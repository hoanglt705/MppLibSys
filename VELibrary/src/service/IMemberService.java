package service;

import java.util.Collection;

import domain.Address;
import domain.Member;

public interface IMemberService {

	String createMember(Member member, Address address);
	
	boolean exist(String memberId);

	Collection<Member> listAllMember();

}

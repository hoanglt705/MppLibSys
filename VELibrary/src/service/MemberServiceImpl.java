package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Address;
import domain.Member;

public class MemberServiceImpl implements IMemberService {
	@Override
	public String createMember(Member member, Address address) {
		DataAccess dataAccess = new DataAccessFacade();
		member.setAddress(address);
		return dataAccess.saveNewMember(member);
	}
}

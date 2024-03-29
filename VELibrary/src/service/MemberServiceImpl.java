package service;

import java.util.Collection;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Address;
import domain.Member;

public class MemberServiceImpl implements IMemberService {
	@Override
	public String createMember(Member member, Address address) {
		member.setAddress(address);
		return getDataAccess().saveNewMember(member);
	}

	@Override
	public boolean exist(String memberId) {
		return getDataAccess().existMember(memberId);
	}
	
	private DataAccess getDataAccess() {
		return new DataAccessFacade();
	}

	@Override
	public Collection<Member> listAllMember() {
		return getDataAccess().readMemberMap().values();
	}
}

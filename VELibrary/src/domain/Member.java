package domain;

import java.io.Serializable;

final public class Member extends Person implements Serializable {
	private static final long serialVersionUID = -2226197306790714013L;

	private String memberId;

	public Member(String firstName, String lastName, String telephone, Address address) {
		super(firstName, lastName, telephone, address);
	}

	public Member(String memberId, String firstName, String lastName, String telephone, Address address) {
		this(firstName, lastName, telephone, address);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + "]";
	}

}

package domain;

import java.io.Serializable;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	private String password;
	private Role roles;
	public User(String id, String pass, Role auth) {
		this.id = id;
		this.password = pass;
		this.roles = auth;
	}

	public User() {
	}

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}

	public Role getRoles() {
		return roles;
	}
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + roles.toString() + "]";
	}
	
}

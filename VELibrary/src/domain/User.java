package domain;

import java.io.Serializable;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	private String username;
	private String password;
	private Role roles;
	public User(String id, String pass, Role auth) {
		this.id = id;
		this.password = pass;
		this.roles = auth;
	}

	User(String id, String username, String pass, Role auth) {
		this.id = id;
		this.username = username;
		this.password = pass;
		this.roles = auth;
	}

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}

	public Role getRoles() {
		return roles;
	}
	@Override
	public String toString() {
		return "[" + id + ":" + username + ":" + password + ", " + roles.toString() + "]";
	}
	
}

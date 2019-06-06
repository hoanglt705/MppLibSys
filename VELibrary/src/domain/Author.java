package domain;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 7508481940058530471L;

    private String id;
    private String bio;
	public String getBio() {
		return bio;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

    public Author(String id, String f, String l, String t, Address a, String bio) {
        this(f, l, t, a, bio);
        this.id = id;
    }



}

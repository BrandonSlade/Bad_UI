package com.revature.Bad_UI.beans;

import java.security.NoSuchAlgorithmException;

import com.revature.Bad_UI.hasher.PasswordHasher;

public class Credentials {
	
	private int id;
	private String username;
	private String password;
	private String hashedPassword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) throws NoSuchAlgorithmException {
		this.hashedPassword = PasswordHasher.passwordHasher(password);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashedPassword == null) ? 0 : hashedPassword.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		if (hashedPassword == null) {
			if (other.hashedPassword != null)
				return false;
		} else if (!hashedPassword.equals(other.hashedPassword))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Credentials [id=" + id + ", username=" + username + ", password=" + password + ", hashedPassword="
				+ hashedPassword + "]";
	}
	public Credentials(int id, String username, String password, String hashedPassword) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.hashedPassword = hashedPassword;
	}
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}

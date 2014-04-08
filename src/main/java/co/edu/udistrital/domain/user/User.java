package co.edu.udistrital.domain.user;

public class User {

	private long id;
	private String username;
	private String password;
	
	public long getId() {
		return id;
	}
	public void setId(long userId) {
		this.id = userId;
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
}

package entity;

public class ServerUser {
	private String userName;
	private String passWord;
	private String role;
	
	public ServerUser(String userName, String passWord, String role) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}

package tw.brad.stest2.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Member {
	private Long id;
	
	@Size(min=6, max=20)
	private String account;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,}$")
	private String passwd;
	private String realname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	
}

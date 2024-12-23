package tw.brad.stest2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {
	private Long id;
	
	private int errorCode;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Address")
	private String addr;
	
	@JsonProperty("Tel")
	private String tel;
	
	public Hotel() {}
	public Hotel(String name, String addr, String tel) {
		this.name = name; this.addr = addr; this.tel = tel;
	}
	
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}

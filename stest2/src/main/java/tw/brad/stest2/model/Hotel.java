package tw.brad.stest2.model;

public class Hotel {
	private Long id;
	private String name;
	private String addr;
	private String tel;
	
	public Hotel() {}
	public Hotel(String name, String addr, String tel) {
		this.name = name; this.addr = addr; this.tel = tel;
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

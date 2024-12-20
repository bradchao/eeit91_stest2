package tw.brad.stest2.model;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private int error;	// 0: success
	private String mesg;
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMesg() {
		return mesg;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	
	
	
}

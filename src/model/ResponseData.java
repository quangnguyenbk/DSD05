package model;


import com.googlecode.objectify.annotation.Index;

public class ResponseData {
	@Index 
	private String message;
	public ResponseData() {
	}

	public ResponseData(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

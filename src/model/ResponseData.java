package model;


import com.googlecode.objectify.annotation.Index;

public class ResponseData {
	@Index 
	private String message;
	private Boolean checkData;
	public ResponseData() {
	}

	public ResponseData(String message, Boolean checkData) {
		super();
		this.message = message;
		this.checkData = checkData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean getCheckData() {
		return checkData;
	}

	public void setCheckData(Boolean checkData) {
		this.checkData = checkData;
	}

}

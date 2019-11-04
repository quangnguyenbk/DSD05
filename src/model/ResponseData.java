package model;


import com.googlecode.objectify.annotation.Index;

public class ResponseData {
	@Index 
	private String message;
	private Object data;
	public ResponseData() {
	}

	public ResponseData(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

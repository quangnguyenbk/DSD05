package model;


import com.googlecode.objectify.annotation.Index;

public class ResponseData {
	@Index 
	private String message;
	DataConfigKPI data;
	private Boolean checkData;
	public ResponseData() {
	}

	public ResponseData(String message, DataConfigKPI data, Boolean checkData) {
		super();
		this.message = message;
		this.data = data;
		this.checkData = checkData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public DataConfigKPI getData() {
		return data;
	}

	public void setData(DataConfigKPI data) {
		this.data = data;
	}
	
	public Boolean getCheckData() {
		return checkData;
	}

	public void setCheckData(Boolean checkData) {
		this.checkData = checkData;
	}

}

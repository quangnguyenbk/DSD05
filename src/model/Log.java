package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
@XmlRootElement
public class Log {
	@Id
	private Long id;
	@Index
	private long userId;
	@Index
	String action;
	@Index
	String result;
	String content;
	@Index
	int type;
	@Index
	long dateCreated;
	
	public Log() {
		
	}

	public Log(Long id, long userId, String action, String result, int type, long dateCreated) {
		super();
		this.id = id;
		this.userId = userId;
		this.action = action;
		this.result = result;
		this.type = type;
		this.dateCreated = dateCreated;
	}

	
	public Log(long userId, String action, String result, String content, int type) {
		super();
		this.userId = userId;
		this.action = action;
		this.result = result;
		this.content = content;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

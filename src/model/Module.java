package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import utils.Config;
@Entity
@XmlRootElement
public class Module {
	@Id
	private Long id;
	@Index
	private String name;
	private String description;
	@Index
	private long createdDate;
	@Index
	private long statusId = Config.DEFAULT_ACTIVE;
	@Index
	private long lastUpdated;
	
	public Module() {
		
	}

	public Module(Long id, String name, String description, long createdDate, long statusId, long lastUpdated) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.statusId = statusId;
		this.lastUpdated = lastUpdated;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
}

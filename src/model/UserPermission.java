package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import utils.Config;

@Entity
@XmlRootElement
public class UserPermission {
	@Id
	private Long id;
	@Index
	private long permissionId;
	@Index
	private long userId;
	@Index
	private long createdDate;
	@Index
	private long statusId = Config.DEFAULT_ACTIVE;
	@Index
	private long lastUpdated;
	
	public UserPermission() {
	}

	public UserPermission(Long id, long permissionId, long userId, long createdDate, long statusId, long lastUpdated) {
		super();
		this.id = id;
		this.permissionId = permissionId;
		this.userId = userId;
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

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

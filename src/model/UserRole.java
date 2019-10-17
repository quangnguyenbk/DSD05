package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement
public class UserRole {
	@Id
	private Long id;
	@Index
	private long roleId;
	@Index
	private long userId;
	@Index
	private long createdDate;
	@Index
	private long statusId;
	@Index
	private long lastUpdated;
	
	public UserRole() {
	}

	public UserRole(Long id, long roleId, long groupId, long createdDate, long statusId, long lastUpdated) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.userId = groupId;
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

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getGroupId() {
		return userId;
	}

	public void setGroupId(long groupId) {
		this.userId = groupId;
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

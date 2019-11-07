package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import utils.Config;

@Entity
@XmlRootElement
public class GroupPermission {
	@Id
	private Long id;
	@Index
	private long permissionId = Config.DEFAULT_LONG;
	@Index
	private long groupId = Config.DEFAULT_LONG;
	@Index
	private long createdDate;
	@Index
	private long statusId = Config.DEFAULT_ACTIVE;
	@Index
	private long lastUpdated;
	
	public GroupPermission() {
	}

	public GroupPermission(Long id, long roleId, long groupId, long createdDate, long statusId, long lastUpdated) {
		super();
		this.id = id;
		this.permissionId = roleId;
		this.groupId = groupId;
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

	public void setPermissionId(long roleId) {
		this.permissionId = roleId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
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

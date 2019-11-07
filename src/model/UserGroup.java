package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
@XmlRootElement
public class UserGroup {
	@Id
	private Long id;
	@Index
	private long userId;
	@Index
	private long groupId;
	@Index
	private long createdDate;
	@Index
	private long statusId;
	@Index
	private long lastUpdated;
	
	public UserGroup(){
		
	}

	public UserGroup(Long id, long userId, long groupId, long createdDate, long statusId, long lastUpdated) {
		super();
		this.id = id;
		this.userId = userId;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

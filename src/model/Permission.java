package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import utils.Config;
@Entity
@XmlRootElement
public class Permission {
	@Id
	private Long id;
	@Index
	private String name;
	@Index
	private String url = Config.STRING_EMPTY;
	@Index
	private long permissionType;
	@Index
	private long projectId;
	@Index
	private long departmentId;
	@Index
	private String description;
	@Index
	private long createdDate;
	@Index
	private long statusId;
	@Index
	private long lastUpdated;
	public Permission() {
		
	}
	public Permission(Long id, String name, String url, long permissionType, long projectId, long departmentId,
			String description, long createdDate, long statusId, long lastUpdated) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.permissionType = permissionType;
		this.projectId = projectId;
		this.departmentId = departmentId;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(long permissionType) {
		this.permissionType = permissionType;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
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

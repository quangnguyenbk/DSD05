package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import utils.Config;

@Entity
@XmlRootElement
public class ModuleDepartment {
	@Id
	private Long id;
	@Index
	private long moduleId = Config.DEFAULT_LONG;
	@Index
	private long departmentId = Config.DEFAULT_LONG;
	@Index
	private long employId = Config.DEFAULT_LONG;
	@Index
	private long createdDate;
	@Index
	private long statusId = Config.DEFAULT_ACTIVE;
	@Index
	private long lastUpdated;
	
	public ModuleDepartment() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getEmployId() {
		return employId;
	}

	public void setEmployId(long employId) {
		this.employId = employId;
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

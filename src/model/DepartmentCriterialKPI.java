package model;


import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement
public class DepartmentCriterialKPI {
	@Id
	private Long id;
	@Index
	private long departmentId;
	@Index
	private long criteriaId;
	@Index
	private long indexCriteria = 0;
	@Index
	private long status;
	@Index
	long dateCreated;
	@Index
	long lastUpdate;
	public DepartmentCriterialKPI() {
	}

	public DepartmentCriterialKPI(Long id, long departmentId, long criteriaId, long indexCriteria, long status, long dateCreated, long lastUpdate) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.criteriaId = criteriaId;
		this.indexCriteria = indexCriteria;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}

	public long getIndexCriteria() {
		return indexCriteria;
	}

	public void setIndexCriteria(long indexCriteria) {
		this.indexCriteria = indexCriteria;
	}
	
	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
	public long getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


}

package model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class CiteriaJobpositionKPI {
	@Id
	private Long id;
	@Index
	private long departmentId;
	@Index
	private long jobPositionId;
	@Index
	private long criterialId;
	@Index
	private long indexCriteria = 0;
	@Index
	private long status;
	@Index
	long dateCreated;
	@Index
	long lastUpdate;
	public CiteriaJobpositionKPI() {
	}

	public CiteriaJobpositionKPI(Long id, long departmentId, long jobPositionId, long criterialId, long indexCriteria, long status, long dateCreated, long lastUpdate) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.jobPositionId = jobPositionId;
		this.criterialId = criterialId;
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

	public long getJobPositionId() {
		return jobPositionId;
	}

	public void setJobPositionId(long jobPositionId) {
		this.jobPositionId = jobPositionId;
	}

	public long getCriterialId() {
		return criterialId;
	}

	public void setCriterialId(long criterialId) {
		this.criterialId = criterialId;
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

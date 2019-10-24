package model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class CiteriaJobpositionKPI {
	@Id
	private Long id;
	@Index
	private long departmentId;
	@Index
	private long  jobPositionId;
	@Index
	private long criterialId;
	@Index
	private long indexAchieve;
	@Index 
	private long percentKPI;
	@Index
	private long status;
	public CiteriaJobpositionKPI() {
	}

	public CiteriaJobpositionKPI(Long id, long departmentId, long jobPositionId, long criterialId, long indexAchieve, long percentKPI, long status) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.jobPositionId = jobPositionId;
		this.criterialId = criterialId;
		this.indexAchieve = indexAchieve;
		this.percentKPI = percentKPI;
		this.status = status;
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
	public long getIndexAchieve() {
		return indexAchieve;
	}

	public void setIndexAchieve(long indexAchieve) {
		this.indexAchieve = criterialId;
	}
	public long getPercentKPI() {
		return percentKPI;
	}

	public void setPercentKPI(long percentKPI) {
		this.percentKPI = percentKPI;
	}
	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
}

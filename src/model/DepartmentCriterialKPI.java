package model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class DepartmentCriterialKPI {
	@Id
	private Long id;
	@Index
	private long departmentId;
	@Index
	private String criteriaName;
	@Index
	private long indexCriteria;
	@Index
	private String status;
	public DepartmentCriterialKPI() {
	}

	public DepartmentCriterialKPI(Long id, long departmentId, String criteriaName, String status, long indexCriteria) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.criteriaName = criteriaName;
		this.indexCriteria = indexCriteria;
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

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public long getIndexCriteria() {
		return indexCriteria;
	}

	public void setIndexCriteria(long indexCriteria) {
		this.indexCriteria = indexCriteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

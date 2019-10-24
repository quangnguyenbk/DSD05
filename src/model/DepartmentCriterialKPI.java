package model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class DepartmentCriterialKPI {
	@Id
	private Long id;
	@Index
	private long departmentId;
	@Index
	private long criteriaId;
	@Index
	private long indexCriteria;
	@Index
	private long percentKPI;
	@Index
	private long status;
	public DepartmentCriterialKPI() {
	}

	public DepartmentCriterialKPI(Long id, long departmentId, long criteriaId, long indexCriteria,long percentKPI, long status) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.criteriaId = criteriaId;
		this.indexCriteria = indexCriteria;
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

	public long getcriteriaId() {
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

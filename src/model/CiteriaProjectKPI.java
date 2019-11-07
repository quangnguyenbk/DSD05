package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement

public class CiteriaProjectKPI {
	@Id
	private Long id;
	@Index
	private long projectId;
	@Index
	private long criterialId;
	@Index
	private long indexAchieve;
	@Index
	private String descriptionCriteria;
	@Index
	private long status;
	@Index
	long dateCreated;
	@Index
	long lastUpdate;
	public CiteriaProjectKPI() {
	}

	public CiteriaProjectKPI(Long id,long projectId, long criterialId, long indexAchieve, String descriptionCriteria, long status, long dateCreated, long lastUpdate) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.criterialId = criterialId;
		this.indexAchieve = indexAchieve;
		this.descriptionCriteria = descriptionCriteria;
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

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
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
	public String getDescriptionCriteria() {
		return descriptionCriteria;
	}

	public void setDescriptionCriteria(String descriptionCriteria) {
		this.descriptionCriteria = descriptionCriteria;
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

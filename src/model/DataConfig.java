package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement
public class DataConfig {

	@Id
	private Long id;
	@Index 
	private long kpiId;
	@Index
	private long month;
	@Index
	private long year;
	@Index
	private long updated_at;
	@Index
	private long created_at;
	@Index
	ArrayList<CiterialKPI> criterias;
	
	public DataConfig() {
	}

	public DataConfig(Long id, long kpiId, long updated_at, long month, long year, ArrayList<CiterialKPI> criterias, long created_at) {
		super();
		this.id = id;
		this.kpiId = kpiId;
		this.criterias = criterias;
		this.created_at = created_at;
		this.month = month;
		this.year = year;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public long getKpiId() {
		return kpiId;
	}

	public void setKpiId(long kpiId) {
		this.kpiId = kpiId;
	}

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public long getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(long updated_at) {
		this.updated_at = updated_at;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public ArrayList<CiterialKPI> getCriterias() {
		return criterias;
	}

	public void setCriterias(ArrayList<CiterialKPI> criterias) {
		this.criterias = criterias;
	}
}

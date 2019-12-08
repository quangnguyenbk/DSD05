package model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@SuppressWarnings("deprecation")
@Entity
@XmlRootElement
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class DataConfigKPI {
	@Index
	private long id;
	@Index
	ArrayList<CiterialKPI> criterias;
	private long department_id;
	private String type;
	private String update_at;
	private String created_at;
	private String employee_id;
	private String project_id;
	private String period;
	
	
	public DataConfigKPI() {
	}

	public DataConfigKPI(long department_id, String type, String update_at, String employee_id, ArrayList<CiterialKPI> criterias, long id, String created_at, String project_id, String period) {
		super();
		this.id = id;
		this.criterias = criterias;
		this.project_id = project_id;
		this.employee_id = employee_id;
		this.created_at = created_at;
		this.department_id = department_id;
		this.type = type;
		this.update_at = update_at;
		this.period = period;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public void setCriterias(ArrayList<CiterialKPI> criterias) {
		this.criterias = criterias;
	}
	
	public ArrayList<CiterialKPI> getCriterias() {
		return criterias;
	}
	
	@Override
    public String toString() {
        return "DataConfigKPI{" +
                "id=" + id +
                ", criterias=" + Arrays.toString(criterias.toArray()) +
                '}';
    }

	public long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
}

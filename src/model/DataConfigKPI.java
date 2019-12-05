package model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement

public class DataConfigKPI {
	@Index
	private long id;
	@Index
	ArrayList<CiterialKPI> criterias;
	private long department_id;
	private String type;
	private String update_at;
	private String created_at;
	private long employee_id;
	private long project_id;
	
	
	public DataConfigKPI() {
	}

	public DataConfigKPI(long department_id, String type, String update_at, long employee_id, ArrayList<CiterialKPI> criterias, long id, String created_at, long project_id) {
		super();
		this.id = id;
		this.criterias = criterias;
		this.project_id = project_id;
		this.employee_id = employee_id;
		this.created_at = created_at;
		this.department_id = department_id;
		this.type = type;
		this.update_at = update_at;
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

	public long getDepartmentId() {
		return department_id;
	}

	public void setDepartmentId(long department_id) {
		this.department_id = department_id;
	}

	public String getUpdateAt() {
		return update_at;
	}

	public void setUpdateAt(String update_at) {
		this.update_at = update_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	public long getEmployeeId() {
		return employee_id;
	}

	public void setEmployeeId(long employee_id) {
		this.employee_id = employee_id;
	}

	public long getProjectId() {
		return project_id;
	}

	public void setProjectId(long project_id) {
		this.project_id = project_id;
	}
}

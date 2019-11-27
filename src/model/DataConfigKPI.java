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
	private long userId;
	@Index
	ArrayList<CiterialKPI> criterias;
	
	public DataConfigKPI() {
	}

	public DataConfigKPI(long id, long userId, ArrayList<CiterialKPI> criterias) {
		super();
		this.id = id;
		this.userId = userId;
		this.criterias = criterias;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return userId;
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
}

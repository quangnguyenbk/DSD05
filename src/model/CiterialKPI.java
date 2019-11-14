package model;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement

public class CiterialKPI {
	@Id
	private int id;
	@Index
	private String name;
	@Index
	private float ratio;
	@Index
	private String note;
	
	public CiterialKPI() {
	}

	public CiterialKPI(int id, String name, float ratio, String note) {
		super();
		this.id = id;
		this.name = name;
		this.ratio = ratio;
		this.note = note;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	public float getRatio() {
		return ratio;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return note;
	}

}

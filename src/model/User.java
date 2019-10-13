package model;


import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@XmlRootElement
public class User {
	@Id
	private Long id;
	@Index
	private String name;
	@Index
	private int age;
	
	public User() {
		
	}
	
	
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}

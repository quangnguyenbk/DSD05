package model;


import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import utils.Config;

@Entity
@XmlRootElement
public class UserInfo {
	@Id
	private Long id;
	@Index
	private String name;
	@Index
	String gender= Config.STRING_EMPTY;
	@Index
	long birthday;
	@Index
	String email;
	@Index
	String phoneNumber = Config.STRING_EMPTY;
	@Index
	String address = Config.STRING_EMPTY;
	@Index
	long employId;
	@Index
	long statusId;
	@Index
	long dateCreated;
	@Index
	long lastUpdate;
	@Ignore
	String username;
	@Ignore
	String password = "1";
	public UserInfo() {
		
	}
	@Override
    public String toString() {
        return "UserInfo{" +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateCreated=" + dateCreated +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
	public UserInfo(Long id, String name, String gender, long birthday, String email, String phoneNumber, String address,
			long departmentId, long statusId, long dateCreated, long lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.statusId = statusId;
		this.dateCreated = dateCreated;
		this.lastUpdate = lastUpdate;
	}





	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public long getEmployId() {
		return employId;
	}

	public void setEmployId(long employId) {
		this.employId = employId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	
}

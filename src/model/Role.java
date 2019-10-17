package model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class Role {
	@Id
	private Long id;
	@Index
	private String name;
	@Index
	private long roleType;
	@Index
	private long projectId;
	@Index
	private long departmentId;
	@Index
	private String description;
	@Index
	private long createdDate;
	@Index
	private long statusId;
	@Index
	private long lastUpdated;
}

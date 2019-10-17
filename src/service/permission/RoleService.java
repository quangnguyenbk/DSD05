package service.permission;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.UserGroup;
import model.UserGroupRole;

@Path("/role")
public class RoleService {
	
	@Path("/addUserGroup")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserGroup addUserGroup(UserGroup usergroup) {
		long test = new Long("11");
		UserGroup group = new UserGroup( test, "test", test, test, test, "description", test, test, test);
		return group;
	}
	
	@Path("/getAllUserGroup")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserGroup> getAllUserGroup(){
		ArrayList<UserGroup> list = new ArrayList<UserGroup>();
		for (int i = 1; i< 4; i ++) {
			long test = new Long(i+"");
			UserGroup group = new UserGroup( test, "test", test, test, test, "description", test, test, test);
			list.add(group);
		}
		return list;
	}
	
	@Path("/editUserGroup")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserGroup editUserGroup(UserGroup usergroup) {
		long test = new Long("11");
		UserGroup group = new UserGroup( test, "test", test, test, test, "description", test, test, test);
		return group;
	}
	
	@Path("/addUserGroupRole")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserGroupRole addUserGroupRole(UserGroupRole userGroupRole) {
		long test = new Long("11");
		UserGroupRole groupRole = new UserGroupRole(test, test, test, test, test, test);
		return groupRole;
	}
	
	@Path("/removeUserGroupRole")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserGroupRole removeUserGroupRole(UserGroupRole userGroupRole) {
		long test = new Long("11");
		UserGroupRole groupRole = new UserGroupRole(test, test, test, test, test, test);
		return groupRole;
	}
}

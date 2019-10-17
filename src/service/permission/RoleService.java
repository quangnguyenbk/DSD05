package service.permission;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Group;
import model.GroupRole;
import model.UserGroup;
import model.UserRole;

@Path("/role")
public class RoleService {
	
	@Path("/addGroup")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Group addUserGroup(Group usergroup) {
		long test = new Long("11");
		Group group = new Group( test, "test", test, test, test, "description", test, test, test);
		return group;
	}
	
	@Path("/getAllGroup")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Group> getAllUserGroup(){
		ArrayList<Group> list = new ArrayList<Group>();
		for (int i = 1; i< 4; i ++) {
			long test = new Long(i+"");
			Group group = new Group( test, "test", test, test, test, "description", test, test, test);
			list.add(group);
		}
		return list;
	}
	
	@Path("/editGroup")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Group editUserGroup(Group usergroup) {
		long test = new Long("11");
		Group group = new Group( test, "test", test, test, test, "description", test, test, test);
		return group;
	}
	
	@Path("/addUserGroupRole")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GroupRole addUserGroupRole(GroupRole userGroupRole) {
		long test = new Long("11");
		GroupRole groupRole = new GroupRole(test, test, test, test, test, test);
		return groupRole;
	}
	
	@Path("/removeGroupRole")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GroupRole removeUserGroupRole(GroupRole userGroupRole) {
		long test = new Long("11");
		GroupRole groupRole = new GroupRole(test, test, test, test, test, test);
		return groupRole;
	}
	
	@Path("/getRolesOfGroup")
	@GET
	public Response getRolesOfUserGroup(){
		ArrayList<Long> message = new ArrayList<Long>();
		message.add(new Long("1"));
		message.add(new Long("2"));
		message.add(new Long("3"));
		return Response
			      .status(Response.Status.INTERNAL_SERVER_ERROR)
			      .entity(message)
			      .build();
	}
	
	@Path("/addUserToGroup")
	@POST
	public UserGroup addUserToGroup(UserGroup usg) {
		long test = new Long("11");
		UserGroup usergroup = new UserGroup(test, test, test, test, test, test);
		return usergroup;
	}
	
	@Path("/removeUserFromGroup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserGroup removeUserFromGroup(UserGroup usg) {
		long test = new Long("11");
		UserGroup usergroup = new UserGroup(test, test, test, test, test, test);
		return usergroup;
	}
	
	@Path("/addUserRole")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserRole addUserRole(UserRole userGroupRole) {
		long test = new Long("11");
		UserRole userRole = new UserRole(test, test, test, test, test, test);
		return userRole;
	}
	
	@Path("/removeUserRole")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserRole removeUserRole(UserRole userGroupRole) {
		long test = new Long("11");
		UserRole userRole = new UserRole(test, test, test, test, test, test);
		return userRole;
	}
	
	@Path("/getRolesOfUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRolesOfUser(){
		ArrayList<Long> message = new ArrayList<Long>();
		message.add(new Long("1"));
		message.add(new Long("2"));
		message.add(new Long("3"));
		return Response
			      .status(Response.Status.OK)
			      .entity(message)
			      .build();
	}
	
	@Path("/checkRoleOfUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkRoleOfUser(){
		boolean message = true;
		return Response
			      .status(Response.Status.OK)
			      .entity(message)
			      .build();
	}
}

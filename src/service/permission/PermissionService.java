package service.permission;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PermissionDao;
import dao.UserDao;
import model.Group;
import model.GroupPermission;
import model.Permission;
import model.UserGroup;
import model.UserInfo;
import model.UserPermission;
import utils.Config;

@Path("/permission")
public class PermissionService {
	PermissionDao permissionDao = new PermissionDao();
	
	//group permission
	@Path("/addGroupPermission")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroupPermission(GroupPermission groupPermission) {
		try {
			Group group = permissionDao.getGroupById(groupPermission.getGroupId());
			Permission permission = permissionDao.getPermissionById(groupPermission.getPermissionId());
			if (group == null || permission == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("groupId hoặc permissionId không đúng")
					      .build();
			}
			
			// add group permission
			long id = permissionDao.addGroupPermission(groupPermission);
			GroupPermission temp = permissionDao.getGroupPermissionById(id);
			return Response
				      .status(Response.Status.OK)
				      .entity(temp)
				      .build();
			
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("groupId hoặc permissionId không đúng")
				      .build();
		}
	}
	
	@Path("/removeGroupPermission")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeGroupPermission(long id) {
		try {
			GroupPermission info = permissionDao.getGroupPermissionById(id);
			info.setStatusId(Config.DEFAULT_INACTIVE);
			permissionDao.saveGroupPermission(info);
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("không tìm thấy GroupPermission")
				      .build();
		}
	}
	
	@Path("/getGroupPermissions")
	@GET
	public Response getGroupPermissions(@QueryParam("id") long id){
		try {
			ArrayList<Permission> permissions = (ArrayList<Permission>) permissionDao.getGroupPermissions(id);
			return Response
				      .status(Response.Status.OK)
				      .entity(permissions)
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("không tìm thấy Group")
				      .build();
		}
	}
	
	// userGroup
	@Path("/addUserToGroup")
	@POST
	public Response addUserToGroup(UserGroup usg) {
		// check null
		if (usg.getGroupId() == 0  || usg.getUserId() ==0) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Thiếu GroupId hoặc UserId")
				      .build();
		}
		
		// add user to group
		try {
			long id = permissionDao.addUserToGroup(usg);
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
		} catch (Exception e){
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("failed")
				      .build();
		}
		
	}
	
	@Path("/removeUserFromGroup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserFromGroup(UserGroup usg) {
		try {
			
			// check null
			if (usg.getGroupId() == 0  || usg.getUserId() ==0) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("Thiếu GroupId hoặc UserId")
					      .build();
			}
			
			UserGroup userGroup = permissionDao.getUserGroup(usg.getGroupId(), usg.getUserId());
			userGroup.setStatusId(Config.DEFAULT_INACTIVE);
			permissionDao.saveUserGroup(userGroup);
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("không tìm thấy UserGroup")
				      .build();
		}
	}
	
	// user permission
	@Path("/addUserPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserPermission addUserPermission(UserPermission userGroupRole) {
		long test = new Long("11");
		UserPermission userRole = new UserPermission(test, test, test, test, test, test);
		return userRole;
	}
	
	@Path("/removeUserPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserPermission removeUserPermission(UserPermission userGroupRole) {
		long test = new Long("11");
		UserPermission userRole = new UserPermission(test, test, test, test, test, test);
		return userRole;
	}
	
	@Path("/getPermissionsOfUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPermissionsOfUser(){
		ArrayList<Long> message = new ArrayList<Long>();
		message.add(new Long("1"));
		message.add(new Long("2"));
		message.add(new Long("3"));
		return Response
			      .status(Response.Status.OK)
			      .entity(message)
			      .build();
	}
	
	@Path("/checkPermissionOfUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkPermissionOfUser(){
		boolean message = true;
		return Response
			      .status(Response.Status.OK)
			      .entity(message)
			      .build();
	}
	
	//permission
	@Path("/addPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPermission(Permission permission){
		//add permission
		long id = permissionDao.addPermission(permission);
		Permission temp = permissionDao.getPermissionById(id);
		return Response
			      .status(Response.Status.OK)
			      .entity(temp)
			      .build();
	}
	
}

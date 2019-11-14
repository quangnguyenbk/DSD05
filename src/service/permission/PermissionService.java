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
			Permission permission = permissionDao.getPermissionById(groupPermission.getPermissionId());
			if (permission == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("permissionId không đúng")
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
	
	@Path("/getAllGroupPermissions")
	@GET
	public Response getAllGroupPermissions(){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getAllGroupPermissions())
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
	@Path("/getGroupPermissions")
	@GET
	public Response getGroupPermissions(@QueryParam("groupId") long id){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getGroupPermissions(id))
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
	// user permission
	@Path("/addUserPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserPermission addUserPermission(UserPermission UserPermission) {
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
	
	//permission
	@Path("/checkPermissionOfGroup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkPermissionOfGroup(@QueryParam("groupId") long groupId, @QueryParam("permissionId") long permissionId){
		try {
			Permission permission = permissionDao.getPermissionById(permissionId);
			if (permission == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("không tìm thấy permisison")
					      .build();
			}
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.checkPermissionOfGroup(permissionId, groupId))
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
	@Path("/getAllPermissionsOfUser")
	@GET
	public Response getAllPermissionsOfUser(){
		long id = 1;
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getGroupPermissions(id))
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
}

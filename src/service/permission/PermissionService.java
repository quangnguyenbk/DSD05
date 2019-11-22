package service.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	UserDao userDao = new UserDao();
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
	
	@Path("/getMultiGroupPermissions")
	@GET
	public Response getMultiGroupPermissions(@QueryParam("listGroupId") String listGroupId){
		String[] listId = listGroupId.split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0 ; i < listId.length ; i++) {
			try {
				ids.add(Long.valueOf(listId[i]));
			} catch (Exception e) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("tham số truyền vào sai")
					      .build();
			}
		}
		Map<String, List<Permission>> result = new HashMap<String, List<Permission>>();
		if (ids.size()> 0) {
			for (int i = 0 ; i< ids.size(); i++) {
				List<Permission> permissions = permissionDao.getGroupPermissions(ids.get(i));
				result.put(ids.get(i) +"", permissions);
			}
		}
		
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity((result))
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
	public Response addUserPermission(UserPermission userPermission) {
		try {
			Permission permission = permissionDao.getPermissionById(userPermission.getPermissionId());
			if (permission == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("permissionId không đúng")
					      .build();
			}
			UserInfo info = userDao.getUserById(userPermission.getUserId());
			if (info == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("userId không đúng")
					      .build();
			}
			// add group permission
			long id = permissionDao.addUserPermission(userPermission);
			UserPermission temp = permissionDao.getUserPermissionById(id);
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
	
	@Path("/removeUserPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserPermission(UserPermission userPermission) {
		try {
			Permission permission = permissionDao.getPermissionById(userPermission.getPermissionId());
			if (permission == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("permissionId không đúng")
					      .build();
			}
			UserInfo info = userDao.getUserById(userPermission.getUserId());
			if (info == null) {
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("userId không đúng")
					      .build();
			}
			permissionDao.removeUserPermission(userPermission.getUserId(), userPermission.getPermissionId());
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
			
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("user không có permission này")
				      .build();
		}
	}
	
	@Path("/getPermissionsOfUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPermissionsOfUser(@QueryParam("userId") long userId){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getUserPermissions(userId))
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
	@Path("/checkPermissionOfUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkPermissionOfUser(@QueryParam("userId") long userId, @QueryParam("permissionId") long permissionId){
		boolean message = false;
		List<Permission> all = permissionDao.getUserPermissions(userId);
		
		for (int i = 0 ; i< all.size(); i++) {
			if (all.get(i).getId() == permissionId) {
				message = true;
				break;
			}
		}
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	
	@Path("/getAllGroup")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGroup(){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getAllGroup())
				      .build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
}

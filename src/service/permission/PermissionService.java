package service.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.LogDao;
import dao.PermissionDao;
import dao.UserDao;
import model.GroupPermission;
import model.Log;
import model.Permission;
import model.UserInfo;
import model.UserPermission;
import utils.Config;

@Path("/permission")
public class PermissionService {
	PermissionDao permissionDao = new PermissionDao();
	UserDao userDao = new UserDao();
	LogDao logDao = new LogDao();
	Logger log = Logger.getLogger("abc");
	//phân quyền cho chức vụ
	@Path("/addGroupPermission")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroupPermission(GroupPermission groupPermission) {
		try {
			//check permissionId
			Permission permission = permissionDao.getPermissionById(groupPermission.getPermissionId());
			if (permission == null) {
				Log log = new Log(0, "addGroupPermission", "error", "permissionId không tồn tại", Config.LOG_TYPE_USER);
				logDao.addLog(log);
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("permissionId không tồn tại")
					      .build();
			}
			
			// check permission và groupId phù hợp
			List<Long> departmentIds = permissionDao.getDepartment(groupPermission.getGroupId());
			List<Permission> pers = new ArrayList<Permission>();
			for (int i = 0; i< departmentIds.size(); i++) {
				if (departmentIds.get(i) != 0) {
					pers.addAll(permissionDao.getDepartmentPermissions(departmentIds.get(i)));
				}
			}
			boolean check = false;
			for (int i = 0 ; i< pers.size(); i++) {
				if(pers.get(i).getId() == groupPermission.getPermissionId()) {
					check = true;
					break;
				}
			}
			if (check == false) {
				logDao.addLog(new Log(0, "addGroupPermission", "error", "chức vụ này không phù hợp với quyền này", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("chức vụ này không phù hợp với quyền này")
					      .build();
			}
			// add group permission
			long id = permissionDao.addGroupPermission(groupPermission);
			GroupPermission temp = permissionDao.getGroupPermissionById(id);
			logDao.addLog(new Log(0, "addGroupPermission", "success", "phân quyền thành công", Config.LOG_TYPE_USER));
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
	public Response removeGroupPermission(GroupPermission groupPermision) {
		if(groupPermision.getGroupId()==0 || groupPermision.getPermissionId()==0) {
			logDao.addLog(new Log(0, "removeGroupPermission", "error", "thiếu permissionId hoặc groupId", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.OK)
				      .entity("thiếu permissionId hoặc groupId")
				      .build();
		}
		try {
			List<GroupPermission> infos = permissionDao.getGroupPermission(groupPermision.getGroupId(), groupPermision.getPermissionId());
			if (infos.size() == 0) {
				logDao.addLog(new Log(0, "removeGroupPermission", "error", "không tồn tại", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("không tồn tại")
					      .build();
			}
			permissionDao.deleteGroupPermission(infos);
			logDao.addLog(new Log(0, "removeGroupPermission", "success", "success", Config.LOG_TYPE_USER));
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
			logDao.addLog(new Log(0, "getAllGroupPermissions", "success", "success", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getAllGroupPermissions())
				      .build();
		} catch(Exception e) {
			logDao.addLog(new Log(0, "getAllGroupPermissions", "error", "có lỗi đã xảy ra", Config.LOG_TYPE_USER));
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
				logDao.addLog(new Log(0, "getMultiGroupPermissions", "error", "tham số truyền vào sai", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("tham số truyền vào sai")
					      .build();
			}
		}
		try {
		List<Permission> result = new ArrayList<Permission>();
		if (ids.size()> 0) {
			for (int i = 0 ; i< ids.size(); i++) {
				List<Permission> permissions = permissionDao.getGroupPermissions(ids.get(i));
				if (permissions.size()> 0 ) {
					for (int j = 0 ; j < permissions.size(); j++) {
						permissions.get(j).setGroupId(ids.get(i));
					}
					result.addAll(permissions);
				}
			}
		}
		
		logDao.addLog(new Log(0, "getMultiGroupPermissions", "success", "success", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.OK)
				      .entity((result))
				      .build();
		} catch(Exception e) {
			log.warning(e.getMessage());
			logDao.addLog(new Log(0, "getMultiGroupPermissions", "error", "có lỗi đã xảy ra", Config.LOG_TYPE_USER));
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
				logDao.addLog(new Log(0, "addUserPermission", "error", "permissionId không đúng", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("permissionId không đúng")
					      .build();
			}
			UserInfo info = userDao.getUserById(userPermission.getUserId());
			if (info == null) {
				logDao.addLog(new Log(0, "addUserPermission", "error", "userId không đúng", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("userId không đúng")
					      .build();
			}
			// add group permission
			long id = permissionDao.addUserPermission(userPermission);
			UserPermission temp = permissionDao.getUserPermissionById(id);
			logDao.addLog(new Log(0, "addUserPermission", "success", "success", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.OK)
				      .entity(temp)
				      .build();
			
		} catch(Exception e) {
			logDao.addLog(new Log(0, "addUserPermission", "error", "có lỗi đã xảy ra", Config.LOG_TYPE_USER));
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
				logDao.addLog(new Log(0, "removeUserPermission", "error", "permissionId không đúng", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("permissionId không đúng")
					      .build();
			}
			UserInfo info = userDao.getUserById(userPermission.getUserId());
			if (info == null) {
				logDao.addLog(new Log(0, "removeUserPermission", "error", "userId không đúng", Config.LOG_TYPE_USER));
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity("userId không đúng")
					      .build();
			}
			permissionDao.removeUserPermission(userPermission.getUserId(), userPermission.getPermissionId());
			logDao.addLog(new Log(0, "removeUserPermission", "success", "success", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
			
		} catch(Exception e) {
			logDao.addLog(new Log(0, "removeUserPermission", "error", "user không có permission này", Config.LOG_TYPE_USER));
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
			List<Permission> all = new ArrayList<Permission>();
			
			if (permissionDao.getUserPermissions(userId).size()>0)
				all.addAll(permissionDao.getUserPermissions(userId));
			List<Long> groupIds = permissionDao.getGroup(userId);
			for (int i = 0; i< groupIds.size(); i++) {
				if (permissionDao.getGroupPermissions(groupIds.get(i)).size() > 0) {
					all.addAll(permissionDao.getGroupPermissions(groupIds.get(i)));
				}
			}
			logDao.addLog(new Log(0, "getPermissionsOfUser", "success", "success", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.OK)
				      .entity(all)
				      .build();
			
		} catch(Exception e) {
			logDao.addLog(new Log(0, "getPermissionsOfUser", "error", "có lỗi đã xảy ra", Config.LOG_TYPE_USER));
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
	public Response checkPermissionOfUser(@QueryParam("userId") long userId, @QueryParam("permissionId") long permissionId, @QueryParam("permissionName") String permissionName){
		boolean message = false;
		List<Permission> all = new ArrayList<Permission>();
		
		if (permissionDao.getUserPermissions(userId).size()>0)
			all.addAll(permissionDao.getUserPermissions(userId));
		List<Long> groupIds = permissionDao.getGroup(userId);
		for (int i = 0; i< groupIds.size(); i++) {
			if (permissionDao.getGroupPermissions(groupIds.get(i)).size() > 0) {
				all.addAll(permissionDao.getGroupPermissions(groupIds.get(i)));
			}
		}
		
		for (int i = 0 ; i< all.size(); i++) {
			if (all.get(i).getId() == permissionId) {
				message = true;
				break;
			}
		}
		logDao.addLog(new Log(0, "checkPermissionOfUser", "success", "success", Config.LOG_TYPE_USER));
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
		if (permission.getModuleId() == 0) {
			logDao.addLog(new Log(0, "addPermission", "error", "thiếu moduleId", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("thiếu moduleId")
				      .build();
		}
		// check name
		if (permission.getName() == Config.STRING_EMPTY) {
			logDao.addLog(new Log(0, "addPermission", "error", "thiếu tên", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("thiếu tên")
				      .build();
		}
		if (permissionDao.getPermissionByName(permission.getName()).size()>0) {
			logDao.addLog(new Log(0, "addPermission", "error", "trùng tên", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("trùng tên")
				      .build();
		}
		//add permission
		permission.setCreatedDate((new Date()).getTime());
		long id = permissionDao.addPermission(permission);
		Permission temp = permissionDao.getPermissionById(id);
		logDao.addLog(new Log(0, "addPermission", "success", "success", Config.LOG_TYPE_USER));
		return Response
			      .status(Response.Status.OK)
			      .entity(temp)
			      .build();
	}
	
	@Path("/editPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editPermission(Permission permission){
		if (permission.getModuleId() == 0) {
			logDao.addLog(new Log(0, "editPermission", "error", "thiếu moduleId", Config.LOG_TYPE_USER));
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("thiếu moduleId")
				      .build();
		}
		// check name
		if (permission.getName() == Config.STRING_EMPTY) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("thiếu tên")
				      .build();
		}
		if (!permissionDao.getPermissionByName(permission.getName()).get(0).getId().equals(permission.getId())) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("trùng tên")
				      .build();
		}
		//check tồn tại
		if (permissionDao.getPermissionById(permission.getId()) == null) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("permission không tồn tại")
				      .build();
		}
		
		if (permissionDao.getPermissionById(permission.getId()).getLastUpdated() > permission.getLastUpdated()) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("dữ liệu cũ")
				      .build();
		}
		// save
		permissionDao.addPermission(permission);
		return Response
			      .status(Response.Status.OK)
			      .entity(permission)
			      .build();
	}
	
	
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
	
	@Path("/getAllPermissions")
	@GET
	public Response getAllPermissions(@QueryParam("moduleId") long moduleId){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getAllPermissions(moduleId))
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
}

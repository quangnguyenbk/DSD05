package service.permission;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PermissionDao;
import dao.UserDao;
import model.Permission;
import model.Module;
import model.ModuleDepartment;

@Path("/permission")
public class ModuleService {
	PermissionDao permissionDao = new PermissionDao();
	UserDao userDao = new UserDao();
	// module
	@Path("/addModule")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addModule(Module module){
		//add permission
		long id = permissionDao.addModule(module);
		Module temp = permissionDao.getModuleById(id);
		return Response
			      .status(Response.Status.OK)
			      .entity(temp)
			      .build();
	}
	
	@Path("/editModule")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editModule(Module module){
		//add permission
		Module temp = permissionDao.getModuleById(module.getId());
		if (temp == null) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("không tìm thấy module")
				      .build();
		}
		if (temp.getLastUpdated() > module.getLastUpdated()) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("dữ liệu cũ")
				      .build();
		}
		permissionDao.saveModule(module);
		return Response
			      .status(Response.Status.OK)
			      .entity(temp)
			      .build();
	}
	
	@Path("/addModuleDepartment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addModuleDepartment(ModuleDepartment rd){
		// check input
		if (rd.getModuleId() == 0) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("thiếu moduleId")
				      .build();
		}
		Module module = permissionDao.getModuleById(rd.getModuleId());
		if (module == null) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("module không tồn tại")
				      .build();
		}
		// check Department exist
		if (!permissionDao.checkDepartmentExist(rd.getDepartmentId())) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("department không tồn tại")
				      .build();
		}
		
		permissionDao.addModuleDepartment(rd);
		return Response
			      .status(Response.Status.OK)
			      .entity("success")
			      .build();
	}
	
	@Path("/checkDepartmentExist")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkDepartmentExist(@QueryParam("departmentId") long departmentId){
		return Response
			      .status(Response.Status.OK)
			      .entity(permissionDao.checkDepartmentExist(departmentId))
			      .build();
	}
	
	@Path("/removeModuleDepartment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeModuleDepartment(ModuleDepartment rd){
		// check input
		if (rd.getModuleId() == 0) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("thiếu moduleId")
				      .build();
		}
		Module module = permissionDao.getModuleById(rd.getModuleId());
		if (module == null) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("không tìm thấy module")
				      .build();
		}
		
		permissionDao.deleteModuleDepartment(rd);
		return Response
			      .status(Response.Status.OK)
			      .entity("success")
			      .build();
	}
	
	
	@Path("/getAllModules")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllModules(){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getAllModules())
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
	@Path("/getDepartmentModules")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartmentModules(@QueryParam("departmentId") long departmentId){
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissionDao.getDepartmentModules(departmentId))
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
	
	@Path("/getDepartmentPermissions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartmentPermissions(@QueryParam("departmentId") long departmentId){
		
		List<Module> modules = permissionDao.getDepartmentModules(departmentId);
		List<Permission> permissions = new ArrayList<Permission>();
		for (int i = 0 ; i < modules.size() ; i++) {
			permissions.addAll(permissionDao.getPermissionByModuleId(modules.get(i).getId()));
		}
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity(permissions)
				      .build();
		} catch(Exception e) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("có lỗi đã xảy ra")
				      .build();
		}
	}
}

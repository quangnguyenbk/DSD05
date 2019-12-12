package service.permission;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import dao.PermissionDao;
import dao.UserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultJwtParser;
import model.Permission;
import model.UserInfo;
import utils.Config;
import model.Log;
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
	public Response addModule(Module module, @Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
		if (permissionDao.getModuleByName(module.getName()).size()>0) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("trùng tên")
				      .build();
		}
		//add module
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
	public Response editModule(Module module, @Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
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
	public Response addModuleDepartment(ModuleDepartment rd , @Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
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
	
	@Path("/removeModuleDepartment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeModuleDepartment(ModuleDepartment rd , @Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
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
	public Response getAllModules(@Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
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
	public Response getDepartmentModules(@QueryParam("departmentId") long departmentId, @Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
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
	public Response getDepartmentPermissions(@QueryParam("departmentId") long departmentId, @Context HttpHeaders httpHeaders){
		if (!Config.checkPermissionUser(Config.getUserFromHeader(httpHeaders), Config.ADMIN_PERMISSION))
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Bạn không có quyền thực hiện chức năng này")
				      .build();
		
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

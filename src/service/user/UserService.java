package service.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
import javax.ws.rs.core.UriInfo;

import dao.LogDao;
import dao.UserDao;
import model.GroupPermission;
import model.Log;
import model.UserInfo;
import model.UserGroup;
import model.UserPermission;
import utils.CommonUtils;
import utils.Config;

@Path("/user")
public class UserService {
	UserDao userDao = new UserDao();
	LogDao logDao = new LogDao();
//	@Path("/editUserInfo")
//	@POST
//    @Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response editUserInfo(UserInfo user) {
//		// check null
//		if (user.getId() == null || user.getName() == null || user.getEmail() == null ) {
//			Log log = new Log(0, "editUserInfo", "error", "thiếu id, name hoặc email của user", Config.LOG_TYPE_USER);
//			logDao.addLog(log);
//			return Response
//				      .status(Response.Status.INTERNAL_SERVER_ERROR)
//				      .entity("Thiếu id, tên hoặc email")
//				      .build();
//		}
//		
//		
//		
//		//check id
//		UserInfo testId = userDao.getUserById(user.getId());
//		if (testId == null ) {
//			Log log = new Log(0, "editUserInfo", "error", "không tìm thấy user", Config.LOG_TYPE_USER);
//			logDao.addLog(log);
//			return Response
//				      .status(Response.Status.INTERNAL_SERVER_ERROR)
//				      .entity("Không tìm thấy user")
//				      .build(); 
//		}
//		
//		if (testId.getLastUpdate() != user.getLastUpdate()) {
//			Log log = new Log(0, "editUserInfo", "error", "dữ liệu cũ", Config.LOG_TYPE_USER);
//			logDao.addLog(log);
//			return Response
//				      .status(Response.Status.INTERNAL_SERVER_ERROR)
//				      .entity("Dữ liệu cũ")
//				      .build();
//		}
//				
//		//check mail
//		UserInfo test = userDao.getUserByEmail(user.getEmail());
//		if (test != null && !test.getId().equals(user.getId())) {
//			Log log = new Log(0, "editUserInfo", "error", "email không tồn tại", Config.LOG_TYPE_USER);
//			logDao.addLog(log);
//			return Response
//				      .status(Response.Status.INTERNAL_SERVER_ERROR)
//				      .entity("Trắng email")
//				      .build(); 
//		}
//		
//		// edit user info
//		userDao.saveUser(user);
//		UserInfo info = userDao.getUserById(user.getId());
//		Log log = new Log(0, "editUserInfo", "error", "cập nhật user thành công", Config.LOG_TYPE_USER);
//		logDao.addLog(log);
//		return Response
//			      .status(Response.Status.OK)
//			      .entity(info)
//			      .build();
//	}
	
	@Path("/removeUserInfo")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserInfo(UserInfo user) {
		try {
			UserInfo info = userDao.getUserById(user.getId());
			info.setStatusId(Config.USER_INACTIVE);
			userDao.saveUser(info);
			Log log = new Log(0, "removeUserInfo", "success", "xóa user thành công", Config.LOG_TYPE_USER);
			logDao.addLog(log);
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			Log log = new Log(0, "removeUserInfo", "error", "xóa user thất bại", Config.LOG_TYPE_USER);
			logDao.addLog(log);
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("failed")
				      .build();
		}
	}
	
	@Path("/getUserInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserInfo(@QueryParam("id") long id){
		try {
			UserInfo info = userDao.getUserById(id);
			Log log = new Log(0, "getUserInfo", "success", "lấy thông tin userId:" +info.getId(), Config.LOG_TYPE_USER);
			logDao.addLog(log);
			return Response
				      .status(Response.Status.OK)
				      .entity(info)
				      .build();
		} catch(Exception e) {
			Log log = new Log(0, "getUserInfo", "error", "Không tìm thấy user", Config.LOG_TYPE_USER);
			logDao.addLog(log);
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Không tìm thấy user")
				      .build();
		}
	}
	
	@Path("/addUserInfo")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserInfo(UserInfo user) {
		Log log = new Log();
		log.setType(Config.LOG_TYPE_USER);
		log.setAction("addUserInfo");
		log.setUserId(user.getEmployId());
		// check null
		if (user.getName() == null || user.getEmail() == null ) {
			log.setResult("error");
			log.setContent("Thiếu tên hoặc email");
			logDao.addLog(log);
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Thiếu tên hoặc email")
				      .build();
		}
		//check mail
		UserInfo test = userDao.getUserByEmail(user.getEmail());
		if (test != null) {
			log.setResult("error");
			log.setContent("Trùng email");
			logDao.addLog(log);
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Trùng email")
				      .build(); 
		}
		
		// call register
		
		user.setUsername(user.getEmail());
		userDao.addUser(user);
		UserInfo info = userDao.getUserByEmail(user.getEmail());
		userDao.callRegister(user);
		log.setResult("success");
		log.setContent("success" );
		logDao.addLog(log);
		return Response
			      .status(Response.Status.OK)
			      .entity(info)
			      .build();
	}
	
	@Path("/addUserInfoByRegiser")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserInfoByRegiser(UserInfo user) {
		Log log = new Log();
		log.setType(Config.LOG_TYPE_USER);
		log.setAction("addUserInfo");
		log.setUserId(user.getEmployId());
		// check null
		if (user.getName() == null || user.getEmail() == null ) {
			log.setResult("error");
			log.setContent("Thiếu tên hoặc email");
			logDao.addLog(log);
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Thiếu tên hoặc email")
				      .build();
		}
		//check mail
		UserInfo test = userDao.getUserByEmail(user.getEmail());
		if (test != null) {
			return Response
				      .status(Response.Status.OK)
				      .entity(test)
				      .build();
		}
		
		// add user info
		userDao.addUser(user);
		UserInfo info = userDao.getUserByEmail(user.getEmail());
		log.setResult("success");
		log.setContent("success" );
		logDao.addLog(log);
		return Response
			      .status(Response.Status.OK)
			      .entity(info)
			      .build();
	}
	
	@Path("/getUserInfos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserInfo> getUserInfos(@Context UriInfo uriDetails){
		
	    CommonUtils.getLog().info("service getUserInfos was called by " + uriDetails.getAbsolutePath());  
		ArrayList<UserInfo> listInfo = new ArrayList<UserInfo>();
		listInfo.addAll(userDao.getUserInfos());
		Log log = new Log(0, "getUserInfos", "success", "Lấy thành công danh sách users", Config.LOG_TYPE_USER);
		logDao.addLog(log);
		return listInfo;
	}
	
	@Path("/getAllIds")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Long> getAllIds(){
		
		ArrayList<UserInfo> listInfo = new ArrayList<UserInfo>();
		ArrayList<Long> listId = new ArrayList<Long>();
		listInfo.addAll(userDao.getUserInfos());
		for (int i = 0 ; i < listInfo.size(); i++)
		{
			listId.add(listInfo.get(i).getId());
		}
		return listId;
	}
	
}

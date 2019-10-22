package service.user;

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

import dao.UserDao;
import model.Group;
import model.GroupRole;
import model.UserInfo;
import model.UserGroup;
import model.UserRole;
import utils.Config;

@Path("/user")
public class UserService {
	UserDao userDao = new UserDao();
	@Path("/editUserInfo")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editUserInfo(UserInfo user) {
		// check null
		if (user.getId() == null || user.getName() == null || user.getEmail() == null ) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Thiếu id, tên hoặc email")
				      .build();
		}
		
		//check id
		UserInfo testId = userDao.getUserById(user.getId());
		if (testId == null ) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Không tìm thấy user")
				      .build(); 
		}
				
		//check mail
		UserInfo test = userDao.getUserByEmail(user.getEmail());
		if (test != null && !test.getId().equals(user.getId())) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Trùng email")
				      .build(); 
		}
		
		// edit user info
		userDao.saveUser(user);
		UserInfo info = userDao.getUserById(user.getId());
		return Response
			      .status(Response.Status.OK)
			      .entity(info)
			      .build();
	}
	
	@Path("/removeUserInfo")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserInfo(UserInfo user) {
		try {
			UserInfo info = userDao.getUserById(user.getId());
			info.setStatusId(Config.USER_INACTIVE);
			userDao.saveUser(info);
			return Response
				      .status(Response.Status.OK)
				      .entity("success")
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
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
			return Response
				      .status(Response.Status.OK)
				      .entity(info)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
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
		// check null
		if (user.getName() == null || user.getEmail() == null ) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Thiếu tên hoặc email")
				      .build();
		}
		//check mail
		UserInfo test = userDao.getUserByEmail(user.getEmail());
		if (test != null) {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Trùng email")
				      .build(); 
		}
		
		// add user info
		userDao.addUser(user);
		UserInfo info = userDao.getUserByEmail(user.getEmail());
		return Response
			      .status(Response.Status.OK)
			      .entity(info)
			      .build();
	}
	
	@Path("/getUserInfos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserInfo> getUserInfos(	@QueryParam("departmentId") int departmentId,
											@QueryParam("projectId") int projectId,
											@QueryParam("groupId") int groupId){
		ArrayList<UserInfo> listInfo = new ArrayList<UserInfo>();
		listInfo.addAll(userDao.getUserInfos());
		return listInfo;
	}
	
	@Path("/changePassword")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String changePassword(UserInfo user) {
		return "success";
	}
	
	@Path("/listDepartmentUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserInfo> listDepartmentUser(){
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		for(int i =1 ; i< 6; i ++) {
			Long a = new Long(i);
			UserInfo test = new UserInfo();
			list.add(test);
		}
		return list;
	}
}

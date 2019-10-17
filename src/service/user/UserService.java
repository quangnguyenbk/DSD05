package service.user;

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
import model.User;
import model.UserGroup;
import model.UserRole;

@Path("/user")
public class UserService {
	
	@Path("/editUserInfo")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User editUserInfo(User user) {
		Long a = new Long("123");
		User test = new User(a, "123", 5, "nam", a, "456@asdfas", a);
		return test;
	}
	
	@Path("/getUserInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getAllUserGroup(){
		Long a = new Long("123");
		User test = new User(a, "123", 5, "nam", a, "456@asdfas", a);
		return test;
	}
	
	@Path("/addUserInfo")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User addUserInfo(User user) {
		Long a = new Long("123");
		User test = new User(a, "123", 5, "nam", a, "456@asdfas", a);
		return test;
	}
	
	@Path("/listUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> listUser(){
		ArrayList<User> list = new ArrayList<User>();
		for(int i =1 ; i< 6; i ++) {
			Long a = new Long(i);
			User test = new User(a, "123", 5, "nam", a, "456@asdfas", a);
			list.add(test);
		}
		return list;
	}
	
	@Path("/changePassword")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String changePassword(User user) {
		return "success";
	}
	
	@Path("/listDepartmentUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> listDepartmentUser(){
		ArrayList<User> list = new ArrayList<User>();
		for(int i =1 ; i< 6; i ++) {
			Long a = new Long(i);
			User test = new User(a, "123", 5, "nam", a, "456@asdfas", a);
			list.add(test);
		}
		return list;
	}
}

package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.UserInfo;
import utils.Config;

public class UserDao {
	public void addUser(UserInfo user) {
		user.setDateCreated((new Date()).getTime());
		user.setLastUpdate((new Date()).getTime());
		user.setStatusId(Config.USER_ACTIVE);
		ofy().save().entity(user).now(); 
	}
	
	public void saveUser(UserInfo user) {
		user.setLastUpdate((new Date()).getTime());
		ofy().save().entity(user).now(); 
	}
	
	public List<UserInfo> getUserInfos(){
		return  ofy().load().type(UserInfo.class).list();
	}
	
	public UserInfo getUserById(Long id) {
		return ofy().load().type(UserInfo.class).id(id).now(); 
	}
	
	public UserInfo getUserByEmail(String email) {
		return ofy().load().type(UserInfo.class).filter("email", email).first().now(); 
	}
	
	public int getTotalUserByEmail(String email) {
		return ofy().load().type(UserInfo.class).filter("email", email).list().size(); 
	}
}

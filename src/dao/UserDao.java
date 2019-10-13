package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import model.User;

public class UserDao {
	public void addUser(User user) {
		ofy().save().entity(user).now(); 
	}
	
	public User getUser(String name) {
		return ofy().load().type(User.class).filter("name", name).first().now(); 
	}
}

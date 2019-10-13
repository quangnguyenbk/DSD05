package service.permission;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.UserDao;
import model.User;

@Path("/user")
public class UserExampleService {
	
	
	@Path("/addUser")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUserProfile(User user) {
        //we can make use of UserProfile now
        UserDao dao = new UserDao();
        dao.addUser(user);
        System.out.println("First name = " + user.getName());
        System.out.println("Last name = " + user.getAge());
        return dao.getUser(user.getName());
        
	}
}

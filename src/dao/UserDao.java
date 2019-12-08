package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.UserInfo;
import utils.Config;
import utils.RequestGet;
import utils.RequestPost;

public class UserDao {
	ObjectMapper mapper = new ObjectMapper();
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
	
	public UserInfo callRegister(UserInfo userInfo) {
		
		String data ="";
		long userId = 0;
		try {
		    data = mapper.writeValueAsString(userInfo);
		    System.out.println(data);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		String res = RequestPost.send(Config.POST_REGISTER_USER, data);
		if ( res != null) {
			try {
				JSONObject obj = (JSONObject)new JSONParser().parse(res.toString());
				JSONObject ob = (JSONObject) obj;
				if (ob.containsKey("userId")) {
					userId =(Long.valueOf((String)ob.get("userId")));
					System.out.println(ob.get("userId").toString());
				}
			} catch (Exception e) {
			}
		}
		
		if (userId !=0) return getUserById(userId);
		return null;
	}
	
//	public void importUsers(){
//		List<UserInfo> users = new ArrayList<UserInfo>();
//		//
//		try {
//			StringBuffer response = RequestGet.send(Config.GET_ALL_USER);
//			JSONArray obj = (JSONArray)new JSONParser().parse(response.toString());
//			JSONArray array = (JSONArray) obj;
//			for (int i = 0 ; i< array.size(); i++) {
//				JSONObject ob = (JSONObject) array.get(i);
//				if (ob.containsKey("_id")) {
//					UserInfo info = new UserInfo();
//					info.setId(id);
//				}
//			}
//		} catch (Exception e) {
//		}
//	}
}

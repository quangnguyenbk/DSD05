package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import CallService.NetGet;
import model.Group;
import model.GroupPermission;
import model.Permission;
import model.UserGroup;
import model.UserInfo;
import model.UserPermission;
import utils.Config;
import utils.RequestGet;

public class PermissionDao {
	Logger log = Logger.getLogger("test");
	//group
	public Long addGroup(Group group) {
		group.setCreatedDate((new Date()).getTime());
		group.setLastUpdated((new Date()).getTime());
		group.setStatusId(Config.USER_ACTIVE);
		return ofy().save().entity(group).now().getId(); 
	}
	
	public Group getGroupById(long id) {
		return ofy().load().type(Group.class).id(id).now(); 
	}
	
	public List<String> getAllGroup(){
		List<String> ids = new ArrayList<String>();
		//
		try {
			StringBuffer response = RequestGet.send(Config.API_LIST_ALL);
			for (int i = 0; i< 10; i++) {
				if (response == null)
					response = RequestGet.send(Config.API_LIST_ALL);
				else break;
			}
			log.warning("reponse:" + response.toString());
			JSONArray obj = (JSONArray)new JSONParser().parse(response.toString());
			JSONArray array = (JSONArray) obj;
			for (int i = 0 ; i< array.size(); i++) {
				JSONObject ob = (JSONObject) array.get(i);
				if (ob.containsKey("_id")) {
//					ids.add(Long.valueOf((String)ob.get("_id")));
					ids.add(((String)ob.get("_id")));
				}
			}
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		return  ids;
	}
	
	public void saveGroup(Group group) {
		group.setLastUpdated((new Date()).getTime());
		ofy().save().entity(group).now(); 
	}
	
	//group permission
	public boolean checkPermissionOfGroup(long permissionId, long groupId) {
		return ofy().load().type(GroupPermission.class)
				.filter("permissionId", permissionId)
				.filter("groupId", groupId)
				.filter("statusId", Config.DEFAULT_ACTIVE).list().size() > 0? true: false;
	}
	
	public Long addGroupPermission(GroupPermission groupPermission) {
		groupPermission.setCreatedDate((new Date()).getTime());
		groupPermission.setLastUpdated((new Date()).getTime());
		groupPermission.setStatusId(Config.DEFAULT_ACTIVE);
		return ofy().save().entity(groupPermission).now().getId(); 
	}
	
	public GroupPermission getGroupPermissionById(long id) {
		return ofy().load().type(GroupPermission.class).id(id).now(); 
	}
	
	public void saveGroupPermission(GroupPermission groupPermission) {
		groupPermission.setLastUpdated((new Date()).getTime());
		ofy().save().entity(groupPermission).now(); 
	}
	
	public List<GroupPermission> getAllGroupPermissions() {
		  List<GroupPermission> listGP = ofy().load().type(GroupPermission.class).filter("statusId", Config.DEFAULT_ACTIVE).list();
		  return listGP;
	}
	
	public List<Permission> getGroupPermissions(long id) {
		  List<GroupPermission> listGP = ofy().load().type(GroupPermission.class).filter("groupId", id).filter("statusId", Config.DEFAULT_ACTIVE).list();
		  List<Permission> pers = new ArrayList<Permission>();
		  for (int i = 0; i< listGP.size(); i++) {
			  Permission per = getPermissionById(listGP.get(i).getPermissionId());
			  pers.add(per);
		  }
		  return pers;
	}
	
	//permission
	public Permission getPermissionById(long id) {
		return ofy().load().type(Permission.class).id(id).now(); 
	}
	
	public long addPermission(Permission permission) {
		permission.setCreatedDate((new Date()).getTime());
		permission.setLastUpdated((new Date()).getTime());
		return ofy().save().entity(permission).now().getId(); 
	}
	
	
	//user permisson
	public Long addUserPermission(UserPermission userPermission) {
		userPermission.setCreatedDate((new Date()).getTime());
		userPermission.setLastUpdated((new Date()).getTime());
		userPermission.setStatusId(Config.DEFAULT_ACTIVE);
		return ofy().save().entity(userPermission).now().getId(); 
	}
	
	public UserPermission getUserPermissionById(long id) {
		return ofy().load().type(UserPermission.class).id(id).now(); 
	}
	
	public void removeUserPermission(long userId, long permissionId) {
		List<UserPermission> us =ofy().load().type(UserPermission.class).filter("userId", userId).filter("permissionId", permissionId).list();
		ofy().delete().entities(us);
	}
	
	public List<Permission> getUserPermissions(long id) {
		  List<UserPermission> listGP = ofy().load().type(UserPermission.class).filter("userId", id).filter("statusId", Config.DEFAULT_ACTIVE).list();
		  List<Permission> pers = new ArrayList<Permission>();
		  for (int i = 0; i< listGP.size(); i++) {
			  Permission per = getPermissionById(listGP.get(i).getPermissionId());
			  pers.add(per);
		  }
		  return pers;
	}
}

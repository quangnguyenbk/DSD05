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
import model.GroupPermission;
import model.Permission;
import model.Module;
import model.ModuleDepartment;
import model.UserGroup;
import model.UserInfo;
import model.UserPermission;
import utils.Config;
import utils.RequestGet;

public class PermissionDao {
	Logger log = Logger.getLogger("test");
	//group
	
	public List<Long> getAllGroup(){
		List<Long> ids = new ArrayList<Long>();
		//
		try {
			StringBuffer response = RequestGet.send(Config.API_LIST_ALL);
//			for (int i = 0; i< 10; i++) {
//				if (response == null)
//					response = RequestGet.send(Config.API_LIST_ALL);
//				else break;
//			}
			log.warning("reponse:" + response.toString());
			JSONArray obj = (JSONArray)new JSONParser().parse(response.toString());
			JSONArray array = (JSONArray) obj;
			for (int i = 0 ; i< array.size(); i++) {
				JSONObject ob = (JSONObject) array.get(i);
				if (ob.containsKey("id")) {
					ids.add(Long.valueOf((Long)ob.get("id")));
				}
			}
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		return  ids;
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
	
	public List<GroupPermission> getGroupPermission(long groupId, long permissionId) {
		return ofy().load().type(GroupPermission.class).filter("groupId", groupId).filter("permissionId", permissionId).list(); 
	}
	public void deleteGroupPermission(List<GroupPermission> groupPermissions) {
		ofy().delete().entities(groupPermissions);
	}
	public void saveGroupPermission(GroupPermission groupPermission) {
		groupPermission.setLastUpdated((new Date()).getTime());
		ofy().save().entity(groupPermission).now(); 
	}
	
	public List<GroupPermission> getAllGroupPermissions() {
		  List<GroupPermission> listGP = ofy().load().type(GroupPermission.class).filter("statusId", Config.DEFAULT_ACTIVE).list();
		  return listGP;
	}
	
	public List<Permission> getGroupPermissions(long groupId) {
		  List<GroupPermission> listGP = ofy().load().type(GroupPermission.class).filter("groupId", groupId).filter("statusId", Config.DEFAULT_ACTIVE).list();
		  List<Permission> pers = new ArrayList<Permission>();
		  for (int i = 0; i< listGP.size(); i++) {
			  Permission per = getPermissionById(listGP.get(i).getPermissionId());
			  if (per != null)
				  pers.add(per);
		  }
		  return pers;
	}
	
	//permission
	public Permission getPermissionById(long id) {
		return ofy().load().type(Permission.class).id(id).now(); 
	}
	
	public List<Permission> getPermissionByModuleId(long moduleId) {
		return ofy().load().type(Permission.class).filter("moduleId", moduleId).list(); 
	}
	
	public List<Permission> getPermissionByName(String name) {
		return ofy().load().type(Permission.class).filter("name", name).list(); 
	}
	
	public long addPermission(Permission permission) {
		permission.setLastUpdated((new Date()).getTime());
		return ofy().save().entity(permission).now().getId(); 
	}
	
	public List<Permission> getAllPermissions(long moduleId){
		if (moduleId !=0) 
			return getPermissionByModuleId(moduleId);
		return ofy().load().type(Permission.class).list(); 
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
	
	//module
	public Module getModuleById(long id) {
		return ofy().load().type(Module.class).id(id).now(); 
	}
	
	public long addModule(Module module) {
		module.setCreatedDate((new Date()).getTime());
		module.setLastUpdated((new Date()).getTime());
		return ofy().save().entity(module).now().getId(); 
	}
	
	public long saveModule(Module module) {
		module.setLastUpdated((new Date()).getTime());
		return ofy().save().entity(module).now().getId(); 
	}
	
	public List<Module> getAllModules() {
		return ofy().load().type(Module.class).list();
	}
	
	public long addModuleDepartment(ModuleDepartment module) {
		module.setCreatedDate((new Date()).getTime());
		module.setLastUpdated((new Date()).getTime());
		return ofy().save().entity(module).now().getId(); 
	}
	
	public void deleteModuleDepartment(ModuleDepartment module) {
		List<ModuleDepartment> us =ofy().load().type(ModuleDepartment.class).filter("moduleId", module.getModuleId()).filter("departmentId", module.getDepartmentId()).list();
		ofy().delete().entities(us);
	}
	
	public List<Module> getDepartmentModules(long departmentId){
		List<ModuleDepartment> listRD = ofy().load().type(ModuleDepartment.class).filter("departmentId", departmentId).list();
		List<Module> result = new ArrayList<Module>();
		  for (int i = 0; i< listRD.size(); i++) {
			  Module module = getModuleById(listRD.get(i).getModuleId());
			  result.add(module);
		  }
		  return result;
	}
	
	// call another api
	public List<Long> getDepartment(long groupId){
		List<Long> ids = new ArrayList<Long>();
		//
		try {
			StringBuffer response = RequestGet.send(Config.GET_ALL_POSITION);
			log.warning("reponse:" + response.toString());
			JSONArray obj = (JSONArray)new JSONParser().parse(response.toString());
			JSONArray array = (JSONArray) obj;
			for (int i = 0 ; i< array.size(); i++) {
				JSONObject ob = (JSONObject) array.get(i);
				if (ob.containsKey("id")) {
					if (Long.valueOf((Long)ob.get("id")) == groupId) {
						ids.add(Long.valueOf((Long)ob.get("organizationId")));
					}
				}
			}
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		return  ids;
	}
	
	public List<Long> getGroup(long userId){
		List<Long> ids = new ArrayList<Long>();
		//
		try {
			StringBuffer response = RequestGet.send(Config.GET_USER_INFORMATION + userId);
			log.warning("reponse:" + response.toString());
			JSONArray obj = (JSONArray)new JSONParser().parse(response.toString());
			JSONArray array = (JSONArray) obj;
			for (int i = 0 ; i< array.size(); i++) {
				JSONObject ob = (JSONObject) array.get(i);
				if (ob.containsKey("userId")) {
					if (Long.valueOf((Long)ob.get("userId")) == userId) {
						ids.add(Long.valueOf((Long)ob.get("positionId")));
					}
				}
			}
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		return  ids;
	}
	
	public List<Permission> getDepartmentPermissions(long departmentId) {
		List<Module> modules = getDepartmentModules(departmentId);
		List<Permission> permissions = new ArrayList<Permission>();
		for (int i = 0 ; i < modules.size() ; i++) {
			permissions.addAll(getPermissionByModuleId(modules.get(i).getId()));
		}
		return permissions;
	}
}

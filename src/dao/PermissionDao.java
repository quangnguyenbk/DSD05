package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Group;
import model.GroupPermission;
import model.Permission;
import model.UserGroup;
import model.UserInfo;
import utils.Config;

public class PermissionDao {
	
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
	
	public List<Group> getAllGroup(){
		return  ofy().load().type(Group.class).list();
	}
	
	public void saveGroup(Group group) {
		group.setLastUpdated((new Date()).getTime());
		ofy().save().entity(group).now(); 
	}
	
	//group permission
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
	
	public List<Permission> getGroupPermissions(long id) {
		  List<GroupPermission> listGP = ofy().load().type(GroupPermission.class).filter("groupId", id).filter("statusId", Config.DEFAULT_ACTIVE).list();
		  ArrayList<Long> temp = new ArrayList<Long>();
		  ArrayList<Permission> listPermissions = new ArrayList<Permission>();
		  for (int i = 0 ; i< listGP.size(); i++) {
			 temp.add(listGP.get(i).getPermissionId()); 
			 listPermissions.add(getPermissionById(listGP.get(i).getPermissionId()));
		  }
		  return listPermissions;
	}
	
	//permission
	public Permission getPermissionById(long id) {
		return ofy().load().type(Permission.class).id(id).now(); 
	}
	
	public long addPermission(Permission permission) {
		permission.setCreatedDate((new Date()).getTime());
		permission.setLastUpdated((new Date()).getTime());
		permission.setStatusId(Config.DEFAULT_ACTIVE);
		return ofy().save().entity(permission).now().getId(); 
	}
	
	//user group
	
	public Long addUserToGroup(UserGroup ug) {
		ug.setCreatedDate((new Date()).getTime());
		ug.setLastUpdated((new Date()).getTime());
		ug.setStatusId(Config.DEFAULT_ACTIVE);
		return ofy().save().entity(ug).now().getId(); 
	}
	
	public UserGroup getUserGroup(long groupId, long userId) {
		return ofy().load().type(UserGroup.class).filter("groupId", groupId).filter("userId", userId).filter("statusId", Config.DEFAULT_ACTIVE).list().get(0);
	}
	
	public void saveUserGroup(UserGroup userGroup) {
		userGroup.setLastUpdated((new Date()).getTime());
		ofy().save().entity(userGroup).now(); 
	}
}

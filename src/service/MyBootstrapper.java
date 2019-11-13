package service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

import model.CiteriaJobpositionKPI;
import model.CiteriaProjectKPI;
import model.DepartmentCriterialKPI;
import model.Group;
import model.GroupPermission;
import model.Log;
import model.Permission;
import model.UserGroup;
import model.UserInfo;
import model.UserPermission;

public class MyBootstrapper implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ObjectifyService.run(new Work<Void>() {

			@Override
			public Void run() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		ObjectifyService.register(DepartmentCriterialKPI.class);
		ObjectifyService.register(CiteriaJobpositionKPI.class);
		ObjectifyService.register(CiteriaProjectKPI.class);
		ObjectifyService.register(UserInfo.class);
		ObjectifyService.register(Group.class);
		ObjectifyService.register(UserGroup.class);
		ObjectifyService.register(GroupPermission.class);
		ObjectifyService.register(Permission.class);
		ObjectifyService.register(UserPermission.class);
		ObjectifyService.register(Log.class);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
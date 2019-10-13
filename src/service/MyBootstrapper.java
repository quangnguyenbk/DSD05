package service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

import model.User;

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
		ObjectifyService.register(User.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
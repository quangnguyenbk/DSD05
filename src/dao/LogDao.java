package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.cmd.Query;

import model.Log;

public class LogDao {
	public void addLog(Log log) {
		log.setDateCreated((new Date()).getTime());
		ofy().save().entity(log).now(); 
	}
	
	public List<Log> getLogs(int type, long fromDate, long thruDate, long userId){
		Query<Log> query = null;
		query = ofy().load().type(Log.class).filter("type", type);
			
		if (thruDate == 0) {
			thruDate = (new Date()).getTime();
		}
		query = ofy().load().type(Log.class).filter("type", type).filter("dateCreated >", fromDate).filter("dateCreated <", thruDate);
		if (userId != 0) {
			query = ofy().load().type(Log.class).filter("type", type)
					.filter("dateCreated >", fromDate)
					.filter("dateCreated <", thruDate)
					.filter("userId", userId);
		}
		
		return  query.list();
	}
}

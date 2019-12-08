package dao;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CiteriaJobpositionKPI;
import model.DataConfig;
import model.DepartmentCriterialKPI;
import model.UserInfo;
import utils.Config;

public class ConfigKPIDao {
	public void addDataKPI(DataConfig data) {
		data.setCreated_at((new Date()).getTime());
		ofy().save().entity(data).now(); 
	}
	
	public void saveDataKPI(DataConfig data) {
		data.setUpdated_at((new Date()).getTime());
		ofy().save().entity(data).now(); 
	}
	
	public DataConfig getDataKPI(long kpiId, long month, long year) {
		return ofy().load().type(DataConfig.class).filter("kpiId", kpiId).filter("month", month).filter("year", year).first().now(); 
	}

}

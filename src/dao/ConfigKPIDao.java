package dao;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CiteriaJobpositionKPI;
import model.DepartmentCriterialKPI;
import utils.Config;

public class ConfigKPIDao {
	public void addDepartmentCriterialKPI(DepartmentCriterialKPI department) {
		department.setStatus(Config.USER_ACTIVE);
		department.setDateCreated((new Date()).getTime());
		department.setLastUpdate((new Date()).getTime());
		ofy().save().entity(department).now(); 
	}
	public List<DepartmentCriterialKPI> getAllDepartmentCriterialKPI(long departmentId) {
		System.out.println(departmentId);
		return ofy().load().type(DepartmentCriterialKPI.class).filter("departmentId", departmentId).filter("status", Config.USER_ACTIVE).list(); 
	}
	public void updateDepartmentCriterialKPI (DepartmentCriterialKPI department) {
		department.setLastUpdate((new Date()).getTime());
		ofy().save().entity(department).now();
	}
	public void deleteDepartmentCriterialKPI (Long id) {
		DepartmentCriterialKPI department =  ofy().load().type(DepartmentCriterialKPI.class).id(id).now();
		department.setLastUpdate((new Date()).getTime());
		department.setStatus(Config.USER_INACTIVE);
		ofy().save().entity(department).now();
	}
	public DepartmentCriterialKPI getDepartmentCriterialKPI (long departmentId, long criteriaId) {
		return ofy().load().type(DepartmentCriterialKPI.class).filter("departmentId", departmentId).filter("criteriaId", criteriaId).filter("status", Config.USER_ACTIVE).first().now();
	}
	public DepartmentCriterialKPI getIdDepartmentCriterialKPI (Long id) {
		return ofy().load().type(DepartmentCriterialKPI.class).id(id).now();
	}
	
	
	
	
	
	public void addCiteriaJobpositionKPI(CiteriaJobpositionKPI jobposition) {
		jobposition.setStatus(Config.USER_ACTIVE);
		jobposition.setDateCreated((new Date()).getTime());
		jobposition.setLastUpdate((new Date()).getTime());
		ofy().save().entity(jobposition).now(); 
	}
	public List<CiteriaJobpositionKPI> getAllCiteriaJobpositionKPI(long departmentId) {
		return ofy().load().type(CiteriaJobpositionKPI.class).filter("departmentId", departmentId).filter("status", Config.USER_ACTIVE).list(); 
	}
	public void updateCiteriaJobpositionKPI (CiteriaJobpositionKPI jobposition) {
		jobposition.setLastUpdate((new Date()).getTime());
		ofy().save().entity(jobposition).now();
	}
	public void deleteCiteriaJobpositionKPI (Long id) {
		CiteriaJobpositionKPI jobposition =  ofy().load().type(CiteriaJobpositionKPI.class).id(id).now();
		jobposition.setLastUpdate((new Date()).getTime());
		jobposition.setStatus(Config.USER_INACTIVE);
		ofy().save().entity(jobposition).now();
	}
	public CiteriaJobpositionKPI getCiteriaJobpositionKPI (long departmentId, long jobPositionId, long criterialId) {
		return ofy().load().type(CiteriaJobpositionKPI.class).filter("departmentId", departmentId).filter("jobPositionId", jobPositionId).filter("criterialId", criterialId).filter("status", Config.USER_ACTIVE).first().now();
	}
	public CiteriaJobpositionKPI getIdCiteriaJobpositionKPI (Long id) {
		return ofy().load().type(CiteriaJobpositionKPI.class).id(id).now();
	}

}

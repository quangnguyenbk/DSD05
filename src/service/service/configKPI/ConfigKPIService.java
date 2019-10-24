package service.service.configKPI;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.UserDao;
import model.CiteriaJobpositionKPI;
import model.CiteriaProjectKPI;
import model.DepartmentCriterialKPI;


@Path("/configKPI")
public class ConfigKPIService {
	
	@Path("/getDepartmentCriterialKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DepartmentCriterialKPI> getDepartmentCriterialKPI() {
		ArrayList<DepartmentCriterialKPI> list = new ArrayList<DepartmentCriterialKPI>();
		for (int i = 1; i< 4; i ++) {
			long test = new Long("1");
			long id = new Long(i);
			DepartmentCriterialKPI group = new DepartmentCriterialKPI( id, 1, i, 100 , 25, test );
			list.add(group);
		}
		return list;
        
	}
	
	@Path("/addDepartmentCriterialKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DepartmentCriterialKPI addDepartmentCriterialKPI(DepartmentCriterialKPI departmentCriterialKPI) {
		long test = new Long("1");
		long id = new Long("1");
		DepartmentCriterialKPI criteria = new DepartmentCriterialKPI( id, 1, id, 100 , 25, test);
		return criteria;    
	}
	
	@Path("/updateDepartmentCriterialKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DepartmentCriterialKPI updateDepartmentCriterialKPI(DepartmentCriterialKPI departmentCriterialKPI) {
		long test = new Long("1");
		long id = new Long("1");
		DepartmentCriterialKPI criteria = new DepartmentCriterialKPI( id, 1, id, 100 , 25, test);
		return criteria;    
	}
	
	@Path("/deleteDepartmentCriterialKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateDepartmentCriterialKPI() {
		String test = new String("Delete success");
		return test;    
	}
	
	@Path("/getCiteriaJobpositionKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CiteriaJobpositionKPI> getCiteriaJobpositionKPI() {
		ArrayList<CiteriaJobpositionKPI> list = new ArrayList<CiteriaJobpositionKPI>();
		for (int i = 1; i< 4; i ++) {
			long test = new Long("1");
			long id = new Long(i);
			CiteriaJobpositionKPI group = new CiteriaJobpositionKPI( id, 1, id, id, 100 , 25, test);
			list.add(group);
		}
		return list;
        
	}
	
	@Path("/addCiteriaJobpositionKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CiteriaJobpositionKPI addCiteriaJobpositionKPI(CiteriaJobpositionKPI citeriaJobpositionKPI) {
		long test = new Long("1");
		long id = new Long("1");
		CiteriaJobpositionKPI criteria = new CiteriaJobpositionKPI( id, 1, id, id, 100 , 25, test);
		return criteria;    
	}
	
	@Path("/updateCiteriaJobpositionKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CiteriaJobpositionKPI updatev(CiteriaJobpositionKPI citeriaJobpositionKPI) {
		long test = new Long("1");
		long id = new Long("1");
		CiteriaJobpositionKPI criteria = new CiteriaJobpositionKPI( id, 1, id, id, 100 , 25, test);
		return criteria;    
	}
	
	@Path("/deleteCiteriaJobpositionKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCiteriaJobpositionKPI() {
		String test = new String("Delete success");
		return test;    
	}
	
	@Path("/getCiteriaProjectKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CiteriaProjectKPI> getCiteriaProjectKPI() {
		ArrayList<CiteriaProjectKPI> list = new ArrayList<CiteriaProjectKPI>();
		for (int i = 1; i< 4; i ++) {
			long test = new Long("1");
			long id = new Long(i);
			CiteriaProjectKPI group = new CiteriaProjectKPI( id, 1, id, 100 , 25, test);
			list.add(group);
		}
		return list;
        
	}
	
	@Path("/addCiteriaProjectKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CiteriaProjectKPI addCiteriaProjectKPI(CiteriaProjectKPI citeriaProjectKPI) {
		long test = new Long("1");
		long id = new Long("1");
		CiteriaProjectKPI criteria = new CiteriaProjectKPI( id, 1, id, 100 , 25, test);
		return criteria;    
	}
	
	@Path("/updateCiteriaProjectKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CiteriaProjectKPI updateCiteriaProjectKPI(CiteriaProjectKPI citeriaProjectKPI) {
		long test = new Long("1");
		long id = new Long("1");
		CiteriaProjectKPI criteria = new CiteriaProjectKPI( id, 1, id, 100 , 25, test);
		return criteria;    
	}
	
	@Path("/deleteCiteriaProjectKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCiteriaProjectKPI() {
		String test = new String("Delete success");
		return test;    
	}
}

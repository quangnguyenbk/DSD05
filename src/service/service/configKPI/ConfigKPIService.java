package service.service.configKPI;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ConfigKPIDao;
import model.CiteriaJobpositionKPI;
import model.CiteriaProjectKPI;
import model.DepartmentCriterialKPI;
import model.ResponseData;



@Path("/configKPI")
public class ConfigKPIService {
	ConfigKPIDao configKPIDao = new ConfigKPIDao();
	@Path("/getDepartmentCriterialKPI")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartmentCriterialKPI(@QueryParam("departmentId") long departmentId) {
		try {
			ArrayList<DepartmentCriterialKPI> list = new ArrayList<DepartmentCriterialKPI>();
			list.addAll(configKPIDao.getAllDepartmentCriterialKPI(departmentId));
			return Response
				      .status(Response.Status.OK)
				      .entity(list)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		}
	}
	@Path("/addDepartmentCriterialKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDepartmentCriterialKPI(DepartmentCriterialKPI departmentCriterialKPI) {
		try {
			ResponseData response = new ResponseData();
			// check null
			if (departmentCriterialKPI.getDepartmentId() == 0 || departmentCriterialKPI.getCriteriaId() == 0) {
				response.setMessage("Thiếu dữ liệu");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			//check exist
			DepartmentCriterialKPI department = configKPIDao.getDepartmentCriterialKPI(departmentCriterialKPI.getDepartmentId(), departmentCriterialKPI.getCriteriaId());
			if (department != null) {
				response.setMessage("Đã tồn tại tiêu chí này");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			
			// add DepartmentCriterialKPI
			configKPIDao.addDepartmentCriterialKPI(departmentCriterialKPI);
			DepartmentCriterialKPI info = configKPIDao.getDepartmentCriterialKPI(departmentCriterialKPI.getDepartmentId(), departmentCriterialKPI.getCriteriaId());
			return Response
				      .status(Response.Status.OK)
				      .entity(info)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		}
	}
	
	@Path("/updateDepartmentCriterialKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDepartmentCriterialKPI(DepartmentCriterialKPI departmentCriterialKPI) {
		try {
			ResponseData response = new ResponseData();
			//check exist
			DepartmentCriterialKPI department = configKPIDao.getDepartmentCriterialKPI(departmentCriterialKPI.getDepartmentId(), departmentCriterialKPI.getCriteriaId());
			System.out.println(department);
			if (department == null) {
				response.setMessage("Không tồn tại tiêu chí này");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			// add DepartmentCriterialKPI
			configKPIDao.updateDepartmentCriterialKPI(departmentCriterialKPI);
			DepartmentCriterialKPI info = configKPIDao.getDepartmentCriterialKPI(departmentCriterialKPI.getDepartmentId(), departmentCriterialKPI.getCriteriaId());
			return Response
				      .status(Response.Status.OK)
				      .entity(info)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		}
	}
	
	@Path("/deleteDepartmentCriterialKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDepartmentCriterialKPI(@QueryParam("id") Long id) {
		try {
			configKPIDao.deleteDepartmentCriterialKPI(id);
			ResponseData response = new ResponseData();
			response.setMessage("Xóa thành công");
			return Response
				      .status(Response.Status.OK)
				      .entity(response)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		} 
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

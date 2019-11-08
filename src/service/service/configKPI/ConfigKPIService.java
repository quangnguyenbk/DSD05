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

import CallService.NetGet;
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
				response.setMessage("Thiáº¿u dá»¯ liá»‡u");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			//call service check permission
			StringBuffer data = NetGet.main("http://dsd05-dot-my-test-project-252009.appspot.com/configKPI/checkUserPermission");
			if(data.toString() == "false") {
				response.setMessage("User không có quyền thêm tiêu chí");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			StringBuffer data1 = NetGet.main("http://dsd05-dot-my-test-project-252009.appspot.com/configKPI/checkExitDepartment");
			if(data1.toString() == "false") {
				response.setMessage("Không có phòng ban này trong danh sách phòng ban");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			StringBuffer data2 = NetGet.main("http://dsd05-dot-my-test-project-252009.appspot.com/configKPI/checkExitCiteria");
			if(data2.toString() == "false") {
				response.setMessage("Không tiêu chí này trong lưới tiêu chí");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			//check exist
			DepartmentCriterialKPI department = configKPIDao.getDepartmentCriterialKPI(departmentCriterialKPI.getDepartmentId(), departmentCriterialKPI.getCriteriaId());
			if (department != null) {
				response.setMessage("Khong ton tai tieu chi nay trong cau hinh phong ban");
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
			Long id = departmentCriterialKPI.getId();
			//check exist
			DepartmentCriterialKPI department = configKPIDao.getIdDepartmentCriterialKPI(id);
			if (department == null) {
				response.setMessage("KhĂ´ng tá»“n táº¡i tiĂªu chĂ­ nĂ y");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			if (department.getCriteriaId() != departmentCriterialKPI.getCriteriaId() || department.getDepartmentId() != departmentCriterialKPI.getDepartmentId()) {
				response.setMessage("KhĂ´ng trĂ¹ng khá»›p mĂ£ phĂ²ng ban vĂ  mĂ£ tiĂªu chĂ­");
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
	public Response deleteDepartmentCriterialKPI(@QueryParam("id") Long id) {
		try {
			configKPIDao.deleteDepartmentCriterialKPI(id);
			ResponseData response = new ResponseData();
			response.setMessage("XĂ³a thĂ nh cĂ´ng");
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
	public Response getCiteriaJobpositionKPI(@QueryParam("departmentId") long departmentId) {
		try {
			ArrayList<CiteriaJobpositionKPI> list = new ArrayList<CiteriaJobpositionKPI>();
			list.addAll(configKPIDao.getAllCiteriaJobpositionKPI(departmentId));
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
	
	@Path("/addCiteriaJobpositionKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCiteriaJobpositionKPI(CiteriaJobpositionKPI citeriaJobpositionKPI) {
		try {
			ResponseData response = new ResponseData();
			// check null
			if (citeriaJobpositionKPI.getDepartmentId() == 0 || citeriaJobpositionKPI.getCriterialId() == 0 || citeriaJobpositionKPI.getJobPositionId() == 0) {
				response.setMessage("Thiếu dữ liệu");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			//check exist
			CiteriaJobpositionKPI jobposition = configKPIDao.getCiteriaJobpositionKPI(citeriaJobpositionKPI.getDepartmentId(),citeriaJobpositionKPI.getJobPositionId(),citeriaJobpositionKPI.getCriterialId());
			if (jobposition != null) {
				response.setMessage("Tiêu chí nay đã tồn tại");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			
			// add DepartmentCriterialKPI
			configKPIDao.addCiteriaJobpositionKPI(citeriaJobpositionKPI);
			CiteriaJobpositionKPI info = configKPIDao.getCiteriaJobpositionKPI(citeriaJobpositionKPI.getDepartmentId(),citeriaJobpositionKPI.getJobPositionId(),citeriaJobpositionKPI.getCriterialId());
			System.out.println(info);
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
	
	@Path("/updateCiteriaJobpositionKPI")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCiteriaJobpositionKPI(CiteriaJobpositionKPI citeriaJobpositionKPI) {
		try {
			ResponseData response = new ResponseData();
			Long id = citeriaJobpositionKPI.getId();
			//check exist
			CiteriaJobpositionKPI jobposition = configKPIDao.getIdCiteriaJobpositionKPI(id);
			if (jobposition == null) {
				response.setMessage("Không có tiêu chí này");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			if (jobposition.getCriterialId() != citeriaJobpositionKPI.getCriterialId() || jobposition.getDepartmentId() != citeriaJobpositionKPI.getDepartmentId() || jobposition.getJobPositionId() != citeriaJobpositionKPI.getJobPositionId()) {
				response.setMessage("Không trùng khớp với tiêu chí nào");
				return Response
					      .status(Response.Status.INTERNAL_SERVER_ERROR)
					      .entity(response)
					      .build(); 
			}
			// add DepartmentCriterialKPI
			configKPIDao.updateCiteriaJobpositionKPI(citeriaJobpositionKPI);
			CiteriaJobpositionKPI info = configKPIDao.getCiteriaJobpositionKPI(citeriaJobpositionKPI.getDepartmentId(),citeriaJobpositionKPI.getJobPositionId(),citeriaJobpositionKPI.getCriterialId());
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
	
	@Path("/deleteCiteriaJobpositionKPI")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCiteriaJobpositionKPI(@QueryParam("id") Long id) {
		try {
			configKPIDao.deleteCiteriaJobpositionKPI(id);
			ResponseData response = new ResponseData();
			response.setMessage("XĂ³a thĂ nh cĂ´ng");
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
	
//	@Path("/getCiteriaProjectKPI")
//	@GET
//    @Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<CiteriaProjectKPI> getCiteriaProjectKPI() {
//		ArrayList<CiteriaProjectKPI> list = new ArrayList<CiteriaProjectKPI>();
//		for (int i = 1; i< 4; i ++) {
//			long test = new Long("1");
//			long id = new Long(i);
//			CiteriaProjectKPI group = new CiteriaProjectKPI( id, 1, id, 100 , 25, test);
//			list.add(group);
//		}
//		return list;
//        
//	}
//	
//	@Path("/addCiteriaProjectKPI")
//	@POST
//    @Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public CiteriaProjectKPI addCiteriaProjectKPI(CiteriaProjectKPI citeriaProjectKPI) {
//		long test = new Long("1");
//		long id = new Long("1");
//		CiteriaProjectKPI criteria = new CiteriaProjectKPI( id, 1, id, 100 , 25, test);
//		return criteria;    
//	}
//	
//	@Path("/updateCiteriaProjectKPI")
//	@POST
//    @Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public CiteriaProjectKPI updateCiteriaProjectKPI(CiteriaProjectKPI citeriaProjectKPI) {
//		long test = new Long("1");
//		long id = new Long("1");
//		CiteriaProjectKPI criteria = new CiteriaProjectKPI( id, 1, id, 100 , 25, test);
//		return criteria;    
//	}
//	
//	@Path("/deleteCiteriaProjectKPI")
//	@GET
//    @Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public String updateCiteriaProjectKPI() {
//		String test = new String("Delete success");
//		return test;    
//	}
	@Path("/checkExitDepartment")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkExitDepartment(@QueryParam("id") Long id) {
		try {
			ResponseData response = new ResponseData();
			response.setCheckData(true);
			return Response
				      .status(Response.Status.OK)
				      .entity("true")
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		}   
	}
	@Path("/checkUserPermission")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkUserPermission(@QueryParam("id") Long id) {
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity("true")
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		}   
	}
	@Path("/checkExitCiteria")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkCiteriaJob(@QueryParam("id") Long id) {
		try {
			return Response
				      .status(Response.Status.OK)
				      .entity("true")
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("get failed")
				      .build();
		}   
	}
}

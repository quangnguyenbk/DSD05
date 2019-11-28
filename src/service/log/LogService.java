package service.log;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.LogDao;
import dao.UserDao;
import model.Log;
import model.UserInfo;
import utils.Config;

@Path("/log")
public class LogService {
	LogDao logDao = new LogDao();
	UserDao userDao = new UserDao();
	@Path("/getLogUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogUser(@QueryParam("fromDate") long fromDate, @QueryParam("thruDate") long thruDate, @QueryParam("userId") long userId){
		try {
			List<Log> logs = logDao.getLogs(Config.LOG_TYPE_USER, fromDate, thruDate, userId);
			return Response
				      .status(Response.Status.OK)
				      .entity(logs)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Không tìm thấy log")
				      .build();
		}
	}
	
	@Path("/getLogKPI")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogKPI(@QueryParam("fromDate") long fromDate, @QueryParam("thruDate") long thruDate, @QueryParam("userId") long userId){
		try {
			List<Log> logs = logDao.getLogs(Config.LOG_TYPE_KPI, fromDate, thruDate, userId);
			return Response
				      .status(Response.Status.OK)
				      .entity(logs)
				      .build();
		} catch(Exception e) {
			System.out.println(e.toString());
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("Không tìm thấy log")
				      .build();
		}
	}
}

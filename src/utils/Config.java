package utils;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import dao.PermissionDao;
import dao.UserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.impl.DefaultJwtParser;
import model.UserInfo;
import service.permission.PermissionService;

public class Config {
	//User Status
	public static final int USER_INACTIVE = 1;
	public static final int USER_ACTIVE = 0;
	
	//Group Status
	public static final int GROUP_ACTIVE = 0;
	
	public static final int LIMIT_LOG =1000;
	
	public static final long DEFAULT_LONG = 0;
	public static final long DEFAULT_ACTIVE = 0;
	public static final long DEFAULT_INACTIVE = 1;
	
	public static final String STRING_EMPTY ="";
	public static final String GENDER_MALE ="Nam";
	public static final String GENDER_FEMALE ="Nu";
	public static final int LOG_TYPE_USER = 0;
	public static final int LOG_TYPE_KPI = 1;
	
	//link
	public static final String API_UPDATE_KPI ="http://206.189.34.124:5000/api/group8/kpis/";
	public static final String API_LIST_ALL ="http://18.217.21.235:8083/api/v1/position/listAll";
	public static final String GET_DEPARTMENT_API ="http://18.217.21.235:8083/api/v1/position/listAll";
	public static final String GET_ALL_POSITION = "http://18.217.21.235:8083/api/v1/position/listAll";
	public static final String GET_USER_INFORMATION = "http://18.217.21.235:8083/api/v1/userOrganization/findByUserId?userId=";
	public static final String POST_REGISTER_USER ="https://api-ptpmpt-18.herokuapp.com/api/auth/register";
	public static final String GET_ALL_USER = "https://pmptn13.herokuapp.com/users";
	public static final String GET_ALL_ORGANIZATION = "http://18.217.21.235:8083/api/v1/organizationalStructure/listOrganization";

	//permission
	public static final long ADMIN_PERMISSION = new Long("6115139271000064");
	
	
	static UserDao userDao = new UserDao();
	static PermissionDao permissionDao = new PermissionDao();
	static PermissionService permissionService = new PermissionService();
	
	
	public static UserInfo getUserFromHeader(HttpHeaders httpHeaders) {
		UserInfo info = new UserInfo();

		try {
			String token = httpHeaders.getHeaderString("Authorization");
			token = token.split(" ")[1];
			System.out.println(token);
			String[] splitToken = token.split("\\.");
			String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";

			DefaultJwtParser parser = new DefaultJwtParser();
	        Jwt<?, ?> jwt = parser.parse(unsignedToken);
		    System.out.println(jwt.toString());
		    String data = jwt.getBody().toString();
	    	if (data.indexOf("userId=")!= -1) {
	    		int start = data.indexOf("userId=") + 7;
	    		int last = data.indexOf(",", data.indexOf("userId=") + 7);
	    		data.substring(start, last);
	    		info = userDao.getUserById(Long.valueOf(data.substring(start, last)));
	    		return info;
	    	}
		     
		} catch (Exception ex) {     // (4)
			ex.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static boolean checkPermissionUser(UserInfo info, long permissionId) {
		if (info == null) return false;
		return permissionDao.checkPermissionOfUser(info.getId(), permissionId);
	}
}

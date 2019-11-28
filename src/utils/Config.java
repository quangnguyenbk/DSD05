package utils;

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
	
	public static final int LOG_TYPE_USER = 0;
	public static final int LOG_TYPE_KPI = 1;
	
	//link
	public static final String API_UPDATE_KPI ="http://206.189.34.124:5000/api/group8/kpis/";
	
	public static final String API_LIST_ALL ="http://18.217.21.235:8083/api/v1/position/listAll";
	
	public static final String GET_DEPARTMENT_API ="http://18.217.21.235:8083/api/v1/position/listAll";
	
	public static final String GET_ALL_POSITION = "http://18.217.21.235:8083/api/v1/position/listAll";
	
	public static final String GET_USER_INFORMATION = "http://18.217.21.235:8083/api/v1/userOrganization/findByUserId?userId=";
	
}

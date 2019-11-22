package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class RequestGet {
	public static Logger log = Logger.getLogger("a");
	public static StringBuffer send(String link) {
		try {

//			URL url = new URL(link);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String readLine = null;
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//			conn.setConnectTimeout(15* 1000);
//			 BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
			 URL url = new URL(link);
		        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			 StringBuffer response = new StringBuffer();
			 while ((readLine = in .readLine()) != null) {
			  response.append(readLine);
			  } 
			in.close();
//			conn.disconnect();
			return response;
		  } catch (Exception e) {
			  log.warning(e.getMessage());
		  }
		return null;
	}
}

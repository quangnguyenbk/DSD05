package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class RequestPut {
	public static String send(String link, String jsonInputString) {
		try {
			URL url = new URL(link);
		    HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    con.setRequestMethod("PUT");
		    con.setDoOutput(true);
		    con.setRequestProperty("Content-Type", "application/json");
		    con.setRequestProperty("Accept", "application/json");
			System.out.println("POST Response Code :: " + jsonInputString);
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(jsonInputString);
            osw.flush();
            osw.close();
			// For POST only - END
            System.out.println("POST Response Code :: " + con.getResponseMessage());
            System.out.println("POST Response Code :: " + con.getContent());
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
				return response.toString();
			} else {
				System.out.println("POST request not worked");
				return "POST request not worked";
			}
		} catch (Exception e) {
			return e.toString();
		}
	}
}

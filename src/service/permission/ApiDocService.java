package service.permission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/swagger")
public class ApiDocService {
	@GET
	@Produces({MediaType.TEXT_HTML})
	public FileInputStream getDoc() {
		try {
			File f = new File("C:\\Users\\Quang\\eclipse-workspace2\\DSD05\\WebContent\\WEB-INF\\swagger-ui\\dist\\index.html");
			return new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}

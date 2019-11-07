package utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CommonUtils {
	public static Logger logger ;
	
	public static Logger getLog() {
		if (logger == null) { 
			logger = Logger.getLogger("DSD05");
		    FileHandler fh;  
		    try {  
//		        //file name only
//		        File file = new File("log.txt");
//		        if(file.createNewFile()){
//		            System.out.println("file.txt File Created in Project root directory");
//		        }else System.out.println("File file.txt already exists in the project root directory");
//		        // This block configure the logger with handler and formatter  
		    	fh = new FileHandler("log.txt",Config.LIMIT_LOG, 1,  true);  
		        logger.addHandler(fh);
		        fh.setFormatter(new SimpleFormatter() {
		            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

		            @Override
		            public synchronized String format(LogRecord lr) {
		                return String.format(format,
		                        new Date(lr.getMillis()),
		                        lr.getLevel().getLocalizedName(),
		                        lr.getMessage()
		                );
		            }
		        });  
		        logger.info("123");
		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
		}
		return logger;
	}
	
}

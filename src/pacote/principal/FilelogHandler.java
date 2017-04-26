package pacote.principal;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class FilelogHandler {
	
	public void inicializaLog(Logger logger, FileHandler fh) {
		try {
			File diretorio = new File("logs");
			diretorio.mkdir();
			// This block configure the logger with handler and formatter
			fh = new FileHandler("logs/" + DataHandler.dataAtualFileName() + ".log");
			logger.addHandler(fh);
			//SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(new Formatter() {
	            @Override
	            public String format(LogRecord record) {
	                SimpleDateFormat logTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	                Calendar cal = new GregorianCalendar();
	                cal.setTimeInMillis(record.getMillis());
	                return record.getLevel()
	                		+ " "
	                        + logTime.format(cal.getTime())
	                        + " || "
	                        + record.getSourceClassName().substring(
	                                record.getSourceClassName().lastIndexOf(".")+1,
	                                record.getSourceClassName().length())
	                        + "."
	                        + record.getSourceMethodName()
	                        + "() : "
	                        + record.getMessage() + "\n";
	            }
	        });

		} catch (SecurityException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
}

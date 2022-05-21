package clase15.laboratorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilidadesFecha {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	

	//java.time.LocalDate
	static String getLocalDateAsString(LocalDate fecha) {
		return fecha.format(dtf);
	}

	static LocalDate getStringAsLocalDate(String fecha) throws ParseException {
		return LocalDate.parse(fecha, dtf);
	}
	
	
	//java.util.Date
	static String getDateAsString(Date fecha) {
		return sdf.format(fecha);
	}

	static Date getStringAsDate(String fecha) throws ParseException {
		return sdf.parse(fecha);
	}
	
}

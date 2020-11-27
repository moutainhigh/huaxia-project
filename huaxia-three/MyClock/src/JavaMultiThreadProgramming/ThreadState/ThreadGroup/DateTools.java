package JavaMultiThreadProgramming.ThreadState.ThreadGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
	public static Date parse(String formatPatten,String dateString){
		try {
			return new SimpleDateFormat(formatPatten).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String format(String formatPatten,Date date) throws ParseException{
		return new SimpleDateFormat(formatPatten).format(date).toString();
	}
}

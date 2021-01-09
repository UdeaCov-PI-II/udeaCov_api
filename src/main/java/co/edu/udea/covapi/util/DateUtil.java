package co.edu.udea.covapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DATE_PATTERN = "dd-MM-yyyy HH:mm";

    public static Date convert(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.parse(dateStr);
    }

    public static String convert(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(date);
    }
}

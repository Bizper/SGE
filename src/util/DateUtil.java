package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat hour = new SimpleDateFormat("hh:mm:ss:SS");

    public static String getDate() {
        return sf.format(new Date());
    }

    public static String getHour() {
        return hour.format(new Date());
    }

}

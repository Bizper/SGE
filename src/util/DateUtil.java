package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss:SS");

    public static String getDate() {
        return sf.format(new Date());
    }

    public static String getTime(long time) {
        if(time < 60) return String.valueOf(time) + "s";
        else {
            return String.valueOf(time / 60) + "m" + String.valueOf(time % 60) + "s";
        }
    }

    public static String getHour() {
        return hour.format(new Date());
    }

}

package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDate() {
        return sf.format(new Date());
    }

}

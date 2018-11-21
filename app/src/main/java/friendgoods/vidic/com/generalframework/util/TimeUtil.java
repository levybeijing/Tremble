package friendgoods.vidic.com.generalframework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

//    毫秒数->分秒
    public static String FormatForMS(long l){
        l=l-28800000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date(l);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

}

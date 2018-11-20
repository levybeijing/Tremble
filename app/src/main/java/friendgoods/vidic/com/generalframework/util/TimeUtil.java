package friendgoods.vidic.com.generalframework.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

//    毫秒数->分秒
    public static String FormatForMS(long l){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(l);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

}

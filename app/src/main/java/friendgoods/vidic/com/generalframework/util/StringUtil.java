package friendgoods.vidic.com.generalframework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
//    是否是手机号
    public static boolean isPhoneNumber(String phone){

        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
//  6-20位 字母或者数字
    public static boolean isPwdOk(String pwd){
        String regex = "^[0-9A-Za-z]{6,20}$";
        if (pwd.length() < 6) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(pwd);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
}

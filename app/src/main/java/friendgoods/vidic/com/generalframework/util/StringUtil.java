package friendgoods.vidic.com.generalframework.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final char[] CHARS = {
            '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
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
    //    获取随机文件名
    public static String getRandomName(int length){
        String s="";
        for (int i = 0; i < length; i++) {
            int random = new Random().nextInt(57);
            s+=CHARS[random];
        }
        return s;
    }
}

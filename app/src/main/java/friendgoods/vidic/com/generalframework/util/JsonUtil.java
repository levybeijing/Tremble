package friendgoods.vidic.com.generalframework.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/21.
 */
public class JsonUtil {

    public static Boolean getInt(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            int state = obj.getInt("state");
            String message = obj.getString("message");
            boolean yes="请重新登录".equals(message)||state==0;
            Log.e("=============", "JsonUtil: "+yes);
            return yes;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package friendgoods.vidic.com.generalframework;

import android.app.Activity;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenCheck {

    public static void toLogin(Activity activit, String json) {
        if (getInt(json)) {
            Intent intent = new Intent("friendgoods.vidic.com.generalframework.LoginReceiver");
            intent.setPackage(activit.getPackageName());
            intent.setAction("action.LOGIN.OTHER");
            activit.sendBroadcast(intent);
        }
    }

    public static Boolean getInt(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String message = obj.getString("message");
            return "请重新登录".equals(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}

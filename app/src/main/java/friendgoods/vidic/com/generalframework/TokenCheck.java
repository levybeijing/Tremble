package friendgoods.vidic.com.generalframework;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import friendgoods.vidic.com.generalframework.util.JsonUtil;

public class TokenCheck {
    public static void toLogin(Activity activit, String json) {
        if (JsonUtil.getInt(json)) {
            Intent intent = new Intent("friendgoods.vidic.com.generalframework.LoginReceiver");
            intent.setPackage(activit.getPackageName());
            intent.setAction("action.LOGIN.OTHER");
            activit.sendBroadcast(intent);
        }
    }
}

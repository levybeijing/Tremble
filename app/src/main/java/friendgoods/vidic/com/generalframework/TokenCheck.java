package friendgoods.vidic.com.generalframework;

import android.app.Activity;
import android.content.Intent;

import friendgoods.vidic.com.generalframework.util.JsonUtil;

public class TokenCheck {
    public static void toLogin(Activity activit, String json) {
        if ("0".equals(JsonUtil.getString("state", json))) {
            Intent intent = new Intent(activit, LoginReceiver.class);
            intent.setAction("action.LOGIN.OTHER");
            activit.startActivity(intent);
        }
    }
}

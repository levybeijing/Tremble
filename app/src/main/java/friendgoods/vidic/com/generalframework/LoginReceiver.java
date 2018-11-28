package friendgoods.vidic.com.generalframework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import friendgoods.vidic.com.generalframework.activity.LoginCodeActivity;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;

import static android.content.Context.MODE_PRIVATE;

public class LoginReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "action.LOGIN.OTHER":
                Log.e("=============", "action_LOGIN_OTHERPLACE: ");
                Intent intent1 = new Intent(context, LoginCodeActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                BaseActivity.finishAll();
                SharedPreferences userinfo = context.getSharedPreferences("userinfo", MODE_PRIVATE);
                SharedPreferences.Editor edit = userinfo.edit();
                edit.clear();
                edit.commit();
                break;
        }
    }
}

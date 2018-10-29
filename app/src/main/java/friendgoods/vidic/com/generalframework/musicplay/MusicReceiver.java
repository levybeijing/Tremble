package friendgoods.vidic.com.generalframework.musicplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MusicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case "android.intent.action.ServiceStart":
//                MusicService.getInstance().onCreate();
                break;
            case "android.intent.action.ServiceEnd":
//                MusicService.getInstance().onDestroy();
                break;
        }
    }
}

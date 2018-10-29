package friendgoods.vidic.com.generalframework.util;

import android.app.Activity;
import android.content.Intent;

import java.util.Map;

import static friendgoods.vidic.com.generalframework.MyApplication.getApplication;

public class IntentUtils {

    public static void startActivity(Activity activity,Class<?> activityClass,boolean isCloss){
        activity.startActivity(new Intent(activity,activityClass));
        if (isCloss){
            activity.finish();
        }
    }

    public static void putExtra(Class<?> activityClass, Map<String,String> map){
        String key;
        String value;

        Intent intent = new Intent(getApplication(), activityClass);
        for (Map.Entry<String,String> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            intent.putExtra(key,value);
        }
        getApplication().startActivity(intent);
    }

    public static String getExtra(Activity activty,String key){
        Intent intent= activty.getIntent();
        String value=intent.getStringExtra(key);
        return value;
    }


}

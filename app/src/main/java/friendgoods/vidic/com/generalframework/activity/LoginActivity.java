package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import friendgoods.vidic.com.generalframework.MainActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.PicassoUtils;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

}
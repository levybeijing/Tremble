package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.io.File;

import friendgoods.vidic.com.generalframework.MainActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.CommonProgressDialog;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.PackageInfoUtils;
import friendgoods.vidic.com.generalframework.util.PicassoUtils;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class SpleashActivity extends Activity {

    //延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 2000;
    private static final long ANIMATION_TIME = 300;
    public static SharedPreferences sp;
    private String myVersion;
    private boolean islog = true;
    private ImageView mSpleashBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSpleashBg = findViewById(R.id.spleash_iv_bg);
        initView();
        RequestVersion();

    }

    private void initView(){
        PicassoUtils.setIntImage(R.mipmap.shop_car_normal,mSpleashBg);
    }

    private void RequestVersion() {
        goHome();
    }

    private void goHome() {
        //图片渐变模糊度始终
        AlphaAnimation gradient = new AlphaAnimation(1.0f, 0.0f);
        //渐变时间
        gradient.setDuration(ANIMATION_TIME);

        gradient.setFillAfter(true);
        //展示图片渐变动画
        this.findViewById(R.id.spleash_iv_bg).startAnimation(gradient);

        gradient.setAnimationListener(new Animation.AnimationListener() {

            /**
             * 动画开始时
             */
            @Override
            public void onAnimationStart(Animation animation) {
            }

            /**
             * 重复动画时
             */
            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            /**
             * 动画结束时
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                String userId = (String)SharedPreferencesUtils.getParam(SpleashActivity.this, "userId", "");

                if(userId.equals("")){
                    ToastUtils.shortToast(userId+"");
                    IntentUtils.startActivity(SpleashActivity.this,MainActivity.class,true);
//                    IntentUtils.startActivity(SpleashActivity.this,LoginActivity.class,true);

                }else {
                    ToastUtils.shortToast(userId+"");
                    IntentUtils.startActivity(SpleashActivity.this,MainActivity.class,true);
                }

                //发送
//                RxBus.getInstance().post(new StudentEvent("007","小明"));
                //接收
//                Observable<StudentEvent> rxSbscription=RxBus.getInstance().toObserverable(StudentEvent.class)
//                        .subscribe(new Action1<StudentEvent>() {
//                            @Override
//                            public void call(StudentEvent studentEvent) {
//                                textView.setText("id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName());
//                            }
//                        });
            }
        });

    }
}
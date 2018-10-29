package friendgoods.vidic.com.generalframework.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import friendgoods.vidic.com.generalframework.PermissionsUtils;
import friendgoods.vidic.com.generalframework.R;
/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class SpleashActivity extends Activity {

    //延迟3秒
//    private static final long SPLASH_DELAY_MILLIS = 2000;
    private static final long ANIMATION_TIME = 1000;
    public static SharedPreferences sp;
//    private String myVersion;
//    private boolean islog = true;
    private ImageView mSpleashBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSpleashBg = findViewById(R.id.spleash_iv_bg);

        String[] permession =new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        PermissionsUtils.getInstance().chekPermissions(this, permession, new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {
                goHome();
            }

            @Override
            public void forbitPermissons() {

            }
        });

    }
    private void goHome() {
        //图片渐变模糊度始终
        AlphaAnimation gradient = new AlphaAnimation(1.0f, 0.8f);
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
                SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
                int sex = userinfo.getInt("sex", 0);
                String userId = userinfo.getString("userId", "");
                boolean bindwx = userinfo.getBoolean("bindwx", false);

                if(userId.equals("")){
                    startActivity(new Intent(SpleashActivity.this,LoginCodeActivity.class));
                } else if(!bindwx){
                    startActivity(new Intent(SpleashActivity.this,WXBindActivity.class));
                }else if (sex==0){
                    startActivity(new Intent(SpleashActivity.this,IntroduceActivity.class));
                }else{
                    startActivity(new Intent(SpleashActivity.this,MainActivity.class));
                }
                finish();
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}
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
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        动态权限
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
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
                int sex = userinfo.getInt("sex", 0);
                int userId = userinfo.getInt("userId", 0);
                String wx = userinfo.getString("wx", "");
                String phone = userinfo.getString("phone", "");

                //两条线路 一个是先手机后微信 一个是先微信后手机
                if(userId==0||wx.equals("")||phone.equals("")){
                    startActivity(new Intent(SpleashActivity.this,LoginCodeActivity.class));
                } else if (sex==0){
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
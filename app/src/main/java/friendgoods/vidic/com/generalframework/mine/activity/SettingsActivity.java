package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.login.LoginCodeActivity;
import friendgoods.vidic.com.generalframework.activity.MusicService;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class SettingsActivity extends BaseActivity {
    private static int SHAKE=1;
    private static int VOICE=1;
    private int volume;
    private AudioManager mAudioManager;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;
    private boolean requestIsOk=true;
    private SwitchCompat voice;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //
        userId = (int) SharedPFUtils.getParam(this, "userId", 0)+"";
        initView();
    }
    private void initView() {
        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();

        ImageView iv_back = findViewById(R.id.iv_top_set);

        //检测上次请求
        if (!requestIsOk){
            request(SHAKE,VOICE);
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        voice = findViewById(R.id.switch_voice);

        boolean isVoide = sharedPreferences.getBoolean("voice", true);
        voice.setChecked(isVoide);
        VOICE=isVoide?1:0;

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        edit.putInt("volume",volume);
        edit.commit();
        voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    VOICE=1;
                    edit.putBoolean("voice",true);
                    edit.commit();
                    Toast.makeText(SettingsActivity.this,"开启",Toast.LENGTH_SHORT).show();
                    startService(new Intent(SettingsActivity.this,MusicService.class));
                }else{
                    VOICE=0;
                    edit.putBoolean("voice",false);
                    edit.commit();
                    Toast.makeText(SettingsActivity.this,"关闭",Toast.LENGTH_SHORT).show();
//                    MusicService.getInstance().stopSelf();
//                    MusicService.getInstance().onDestroy();
                    Intent intent2 = new Intent(SettingsActivity.this, MusicService.class);
                    stopService(intent2);
                }

                request(SHAKE,VOICE);
            }
        });
        TextView version = findViewById(R.id.tv_version);
        PackageManager pm = getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(getPackageName(),0);
            version.setText(pi.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        findViewById(R.id.exit_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }
//
    public boolean request(int i,int j){
        OkGo.post(UrlCollect.modifySettings)//
                .tag(this)//
                .params("userId", userId)
                .params("shake",""+i)
                .params("voice",""+j)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(SettingsActivity.this,s);
                        //  失败则设置 requestIsOk值 重新请求
                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if (!"请求成功".equals(message))
                                requestIsOk=false;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return false;
    }
    //    退出登录
    public void clear() {
        edit.clear();
        edit.commit();
        SharedPFUtils.setParam(SettingsActivity.this, "loginstatus", false);
        startActivity(new Intent(this,LoginCodeActivity.class));
        finishAll();
    }
}

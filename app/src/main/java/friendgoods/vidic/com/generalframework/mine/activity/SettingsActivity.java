package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
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

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.musicplay.MusicService;
import okhttp3.Call;
import okhttp3.Response;

public class SettingsActivity extends Activity {
    private static int SHAKE=1;
    private static int VOICE=1;
    private int volume;
    private AudioManager mAudioManager;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;

    private boolean requestIsOk=true;
    private SwitchCompat voice;
    private SwitchCompat shake;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //
        initView();
    }
    private void initView() {
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();

        tv = findViewById(R.id.tv_weixin_settings);


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
                    int i = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if (i==0)
                        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_PLAY_SOUND);
                    Toast.makeText(SettingsActivity.this,"开启",Toast.LENGTH_SHORT).show();
                    startService(new Intent(SettingsActivity.this,MusicService.class));
                }else{
                    VOICE=0;
                    edit.putBoolean("voice",false);
                    edit.commit();
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND);
                    Toast.makeText(SettingsActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                    MusicService.getInstance().onDestroy();
                }
                request(SHAKE,VOICE);
            }
        });
        shake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //设置震动标识
                Vibrator vibrator = (Vibrator) (getSystemService(Service.VIBRATOR_SERVICE));
                if (isChecked){
                    SHAKE=1;
                    edit.putBoolean("shake",true);
                    edit.commit();
                    Toast.makeText(SettingsActivity.this,"开启",Toast.LENGTH_SHORT).show();
                }else{
                    SHAKE=0;
                    vibrator.cancel();
                    edit.putBoolean("shake",false);
                    edit.commit();
                    Toast.makeText(SettingsActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                }
                request(SHAKE,VOICE);
            }
        });
    }
    public boolean request(int i,int j){
        OkGo.post(UrlCollect.modifySettings)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("shake",""+i)
                .params("voice",""+j)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
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

    @Override
    protected void onResume() {
        super.onResume();
        voice = findViewById(R.id.switch_voice);
        shake = findViewById(R.id.switch_shake);
        //私有数据
        boolean bindwx = sharedPreferences.getBoolean("bindwx", false);
        tv.setText(bindwx?"已绑定":"未绑定");
        boolean isShake = sharedPreferences.getBoolean("shake", true);
        boolean isVoide = sharedPreferences.getBoolean("voice", true);
        shake.setChecked(isShake);
        voice.setChecked(isVoide);
        SHAKE=isShake?1:0;
        VOICE=isVoide?1:0;
    }
}

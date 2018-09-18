package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import friendgoods.vidic.com.generalframework.R;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //获取信息  尽量提前
//        Intent intent = getIntent();


        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_top_set);
        SwitchCompat voice = findViewById(R.id.switch_voice);
        SwitchCompat shake = findViewById(R.id.switch_shake);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                if (isChecked){
                    Toast.makeText(SettingsActivity.this,"开启",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SettingsActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });
        shake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //设置震动标识
//                Vibrator vibrator = (Vibrator) (getSystemService(Service.VIBRATOR_SERVICE));

                if (isChecked){
                    Toast.makeText(SettingsActivity.this,"开启",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SettingsActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

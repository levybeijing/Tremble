package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.MallActivity;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class RelaxModelActivity extends BaseActivity implements View.OnClickListener {
    private int number=0;
    private TextView tv_number;
    private ScaleAnimation animation;
    private long gametime;
    private boolean note=true;
    private ImageView iv_note,iv_detail;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxmodel);
        gametime=System.currentTimeMillis();


        userId = (int)SharedPFUtils.getParam(this,"userId",0)+"";

        initView();
    }

    private void initView() {
        tv_number = findViewById(R.id.tv_number_relaxmodel);
        Typeface font=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
            tv_number.setTypeface(font);
        }
        //        缩放动画
        animation = new ScaleAnimation(
                1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        );
        animation.setDuration(100);

        findViewById(R.id.iv_click_relaxmodel).setOnClickListener(this);
        findViewById(R.id.iv_exit_relaxmodel).setOnClickListener(this);
        findViewById(R.id.tv_gotomall_relaxmodel).setOnClickListener(this);

        iv_note = findViewById(R.id.iv_note_relaxmodell);
        iv_note.setOnClickListener(this);
        iv_detail = findViewById(R.id.iv_notedetail_relaxmodel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_click_relaxmodel:
                tv_number.setText(++number+"");
                tv_number.setAnimation(animation);
                break;
            case R.id.iv_exit_relaxmodel:
                gametime=System.currentTimeMillis()-gametime;
                addrecord();
                finish();
                break;
            case R.id.tv_gotomall_relaxmodel:
//                跳转
                gametime=System.currentTimeMillis()-gametime;
                startActivity(new Intent(RelaxModelActivity.this,MallActivity.class));
                finish();
                break;
            case R.id.iv_note_relaxmodell:
                if (note){
                    iv_detail.setVisibility(View.VISIBLE);
                }else{
                    iv_detail.setVisibility(View.INVISIBLE);
                }
                note=!note;
                break;
        }
    }
    private void addrecord() {
        OkGo.post(UrlCollect.addRecord)//
            .tag(this)//
                .params("userId",userId)
                .params("time", gametime+"")
                .params("shakeNum", number+"")
                .params("type", "0")
                .params("roomId", "0")
                .params("status", "0")//0（手动）1（脚动）
                .params("mode", "4")//1（挑战）2（故事）3（pk）4（休闲）
                .execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gametime=System.currentTimeMillis()-gametime;
        addrecord();
    }
}
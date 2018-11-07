package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.bean.ChallengeModelBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.MallActivity;
import okhttp3.Call;
import okhttp3.Response;

public class ChalModelActivity extends AppCompatActivity implements View.OnClickListener {
    private int number=0;
    private TextView tv_number,tv_time;
    private ScaleAnimation animation;
    private ImageView iv_click;
    private String time;
    private int minites;
    private int seconds;
    private boolean havetime=true;
    private int aimNumber;
    private long gametime;
    private boolean requsetOk=true;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
           if (msg.what==100){
               tv_time.setText((minites<10?"0"+minites:minites+"")+":"+(seconds<10?"0"+seconds:seconds+""));
           }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengemodel);

        initView();
    }

    private void initView() {
        tv_number = findViewById(R.id.tv_number_challengemodel);
        tv_time = findViewById(R.id.tv_time_challengemodel);

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
        animation.setDuration(500);

        iv_click = findViewById(R.id.iv_click_challengemodel);
        iv_click.setOnClickListener(this);
        findViewById(R.id.iv_exit_challengemodel).setOnClickListener(this);
        findViewById(R.id.tv_gotomall_challengemodel).setOnClickListener(this);
//
        request();
    }
    private Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {
            while (havetime){
                try {
                    Thread.sleep(1000);
                    if (minites!=0&&seconds==0){
                        seconds=59;
                        minites--;
                    }
                    if (minites==0&&seconds==0){
                        havetime=false;
                    }
                    Message message = handler.obtainMessage();
                    message.what=100;
                    seconds--;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_click_challengemodel:
                if (!requsetOk){
                    Toast.makeText(this, "网络延迟,请重新进入", Toast.LENGTH_SHORT).show();
                }
                if (number==aimNumber){
                    Toast.makeText(this, "挑战成功", Toast.LENGTH_SHORT).show();
                }
                tv_number.setText(++number+"");
                tv_number.setAnimation(animation);
                if (!thread.isAlive()){
                    thread.start();
                    gametime=System.currentTimeMillis();
                }
                break;
            case R.id.iv_exit_challengemodel:
                finish();
                break;
            case R.id.tv_gotomall_challengemodel:

                finish();
//                跳转
                startActivity(new Intent(ChalModelActivity.this,MallActivity.class));
                break;
        }
    }
    private void addrecord() {
        OkGo.post(UrlCollect.addRecord)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("time", gametime)
                .params("shakeNum", number)
                .params("type", "0")
                .params("roomId", "0")
                .params("status", "0")//0（手动）1（脚动）
                .params("mode", "1")//1（挑战）2（故事）3（pk）4（休闲）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
    private void request() {
        OkGo.post(UrlCollect.getChallengeMode)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=======", "onSuccess: "+s);
                        ChallengeModelBean bean = new Gson().fromJson(s, ChallengeModelBean.class);
                        time = bean.getData().get(0).getTime();
                        aimNumber=bean.getData().get(0).getNum();
                        tv_time.setText(time);
                        if (time!=null){
                            String[] split = time.split(":");
                            seconds=Integer.parseInt(split[split.length-1]);
                            minites=Integer.parseInt(split[split.length-2]);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gametime=System.currentTimeMillis()-gametime;
        addrecord();
        thread.stop();
        handler=null;
    }
}

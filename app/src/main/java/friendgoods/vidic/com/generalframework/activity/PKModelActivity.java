package friendgoods.vidic.com.generalframework.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.widget.OnTimeSelectListener;
import friendgoods.vidic.com.generalframework.widget.TimePickerBuilder;
import friendgoods.vidic.com.generalframework.widget.TimePickerView;
import okhttp3.Call;
import okhttp3.Response;

public class PKModelActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv1_timer,tv2_timer,tv3_timer,tv4_timer,tv5_timer,tv6_timer;
//    时间选择器
    private TimePickerView pvCustomTime;
//   是否正在游戏
    private static boolean isGaming=false;
//   是否是房主
    private static boolean isHost=true;
//   是否已经设置时间
    private static boolean haveTime=false;
    private LinearLayout ll;
    private ImageView readyno;
    private ImageView readyyes;
    private ImageView startyes;
    private ImageView startno;
    //
    private static int pkCount=0;
    private static int lastTime=2;

    private ImageView light2;
    private ImageView three;
    private ImageView two;
    private ImageView one;
    private ImageView click;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    three.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    two.setVisibility(View.INVISIBLE);
                    one.setVisibility(View.VISIBLE);
                    break;
                case 0:
                    one.setVisibility(View.INVISIBLE);
                    click.setVisibility(View.VISIBLE);
                    click.setClickable(true);
//                    开始游戏
                    Toast.makeText(PKModelActivity.this, "开始游戏", Toast.LENGTH_SHORT).show();
//                    倒计时开始   结束时 停止点击  然后网络访问 重置数据
//                    请求成功的话
//                    pkCount=0;

                    break;
            }
        }
    };
//    均需网络访问才能赋值
    private ImageView person1;
    private ImageView light1;
    private ImageView icon1;
    private TextView name1;
    private ImageView person3;
    private ImageView light3;
    private ImageView icon3;
    private TextView name3;
    private List<Integer> numlist=new ArrayList<>();
    private TextView name2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkmodel);

        initView();
    }
//
    private void initView() {
        ll = findViewById(R.id.ll_timer_pk);
        ll.setOnClickListener(this);

        findViewById(R.id.iv_sure_pkmodel).setOnClickListener(this);
        findViewById(R.id.iv_exit_pkmodel).setOnClickListener(this);

        tv1_timer= findViewById(R.id.tv1_timer_pk);
        tv2_timer= findViewById(R.id.tv2_timer_pk);
        tv3_timer= findViewById(R.id.tv3_timer_pk);
        tv4_timer= findViewById(R.id.tv4_timer_pk);
        tv5_timer= findViewById(R.id.tv5_timer_pk);
        tv6_timer= findViewById(R.id.tv6_timer_pk);
//
        initCustomTimePicker();

        //1
        person1 = findViewById(R.id.iv_person_one_pkmodel);
        light1 = findViewById(R.id.iv_light_one_pkmodel);
        icon1 = findViewById(R.id.iv_icon_one_pkmodel);
        name1 = findViewById(R.id.tv_name_one_pkmodel);
        //2 本人
        ImageView person2 = findViewById(R.id.iv_person_two_pkmodel);
        light2 = findViewById(R.id.iv_light_two_pkmodel);
        ImageView icon2 = findViewById(R.id.iv_icon_two_pkmodel);
        name2 = findViewById(R.id.tv_name_two_pkmodel);
        //3
        person3 = findViewById(R.id.iv_person_three_pkmodel);
        light3 = findViewById(R.id.iv_light_three_pkmodel);
        icon3 = findViewById(R.id.iv_icon_three_pkmodel);
        name3 = findViewById(R.id.tv_name_three_pkmodel);
//按钮
        click = findViewById(R.id.iv_click_pkmodel);
//倒计时
        one = findViewById(R.id.iv_one_pkmodel);
        two = findViewById(R.id.iv_two_pkmodel);
        three = findViewById(R.id.iv_three_pkmodel);
//        准备
        readyyes = findViewById(R.id.iv_readyyes_pkmodel);
        readyyes.setOnClickListener(this);
        readyno = findViewById(R.id.iv_readyno_pkmodel);
//        开始
        startyes = findViewById(R.id.iv_startyes_pkmodel);
        startyes.setOnClickListener(this);
        startno = findViewById(R.id.iv_startno_pkmodel);
//        TODO:
        if (isHost){
            light2.setVisibility(View.VISIBLE);
//            startno.setVisibility(View.VISIBLE);
            startyes.setVisibility(View.VISIBLE);
            addroom();
        }else{
            ll.setClickable(false);
            readyyes.setVisibility(View.VISIBLE);
            joinRoom();
        }
//

//        tv_number = findViewById(R.id.tv_number_storymodel);
//        Typeface font=null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            font = getResources().getFont(R.font.edo);
//            tv_number.setTypeface(font);
//        }
//        //        缩放动画
//        animation = new ScaleAnimation(
//                1.0f, 2.0f, 1.0f, 2.0f,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
//        );
//        animation.setDuration(100);
    }


    public void getLlstOfTime() {
        numlist.add(Integer.parseInt(tv1_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv2_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv3_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv4_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv5_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv6_timer.getText().toString()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_timer_pk:
                if (!isGaming)
                    pvCustomTime.show();
                break;
            case R.id.iv_sure_pkmodel:
                if (!isHost)
                    break;
                if (!checkTime()){
                    Toast.makeText(this, "请至少设置10秒", Toast.LENGTH_SHORT).show();
                    tv1_timer.setText(String.valueOf(0));
                    tv2_timer.setText(String.valueOf(0));
                    tv3_timer.setText(String.valueOf(0));
                    tv4_timer.setText(String.valueOf(0));
                    tv5_timer.setText(String.valueOf(0));
                    tv6_timer.setText(String.valueOf(0));
                    return;
                }
                break;
            case R.id.iv_exit_pkmodel:
                exitRoom();
                finish();
                break;
            case R.id.iv_startyes_pkmodel:
                isGaming=true;
                startyes.setVisibility(View.INVISIBLE);
                three.setVisibility(View.VISIBLE);
//                倒计时 开始游戏
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(lastTime>=0){
                                Thread.sleep(1000);
                                Message message=Message.obtain();
                                message.what=lastTime--;
                                handler.sendMessage(message);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.iv_readyyes_pkmodel:
//                准备好了
                readyyes.setVisibility(View.INVISIBLE);
                readyno.setVisibility(View.VISIBLE);
                light2.setVisibility(View.VISIBLE);

                break;
            case R.id.iv_click_pkmodel:
//
                name2.setText(++pkCount);
                break;
        }
    }

    private void addroom() {
        OkGo.post(UrlCollect.addRoom)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("status", "0")//0  手动  1脚动
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
    private void joinRoom() {
        OkGo.post(UrlCollect.intoRoomJudge)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
//                .params("roomId", roomId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }
                });
    }

    private void exitRoom() {
        OkGo.post(UrlCollect.updateRoomUserStatus)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
//                .params("roomId", roomId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }
                });
    }

    //至少定义10秒钟
    private boolean checkTime() {
        getLlstOfTime();
        for (int i = 0; i < 5; i++) {
            if (numlist.get(i)!=0){
                return true;
            }
        }
        return false;
    }

    private void initCustomTimePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                ToastUtils.shortToast(getTime(date));
                String datetime = getTime(date);
// 在这里定义规则 通过的 设置haveTime 为true
                tv1_timer.setText(String.valueOf(datetime.charAt(0)));
                tv2_timer.setText(String.valueOf(datetime.charAt(1)));
                tv3_timer.setText(String.valueOf(datetime.charAt(3)));
                tv4_timer.setText(String.valueOf(datetime.charAt(4)));
                tv5_timer.setText(String.valueOf(datetime.charAt(6)));
                tv6_timer.setText(String.valueOf(datetime.charAt(7)));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setContentTextSize(18)
                .setType(new boolean[]{false, false, false, true, true, true})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        需要保持连接么?掉线重连? 在restart方法中保存数据?pkcount
    }
}

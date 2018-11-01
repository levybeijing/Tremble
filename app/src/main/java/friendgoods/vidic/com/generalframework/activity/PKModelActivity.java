package friendgoods.vidic.com.generalframework.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.socketlib.com.library.ContentServiceHelper;
import app.socketlib.com.library.listener.SocketResponseListener;
import app.socketlib.com.library.socket.SessionManager;
import app.socketlib.com.library.socket.SocketConfig;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.bean.SocStatusBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.widget.OnTimeSelectListener;
import friendgoods.vidic.com.generalframework.widget.TimePickerBuilder;
import friendgoods.vidic.com.generalframework.widget.TimePickerView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class PKModelActivity extends AppCompatActivity implements View.OnClickListener, SocketResponseListener {
    private TextView tv1_timer,tv2_timer,tv3_timer,tv4_timer,tv5_timer,tv6_timer;
//    时间选择器
    private TimePickerView pvCustomTime;
//   是否正在游戏
    private static boolean isGaming=false;
//   是否是房主
    private static boolean isHost=true;
//
    private LinearLayout ll;
    private ImageView readyno;
    private ImageView readyyes;
    private ImageView startyes;
    private ImageView startno;
    //点击数
    private static int pkCount=0;
//    倒数计时开始
    private static int lastTime=2;

//    private ImageView light2;
    private ImageView three;
    private ImageView two;
    private ImageView one;
    private int SOCGAME=300;
    private ImageView click;
    //    socket赋值
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
    private String roomId;
    private long gametime;
    private int SOCSTATUS=200;

//    计时器
    private int x;
    private int y;
    private int z;
    private Thread Threetoone = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (lastTime >= 0) {
                    Thread.sleep(1000);
                    Message message = Message.obtain();
                    message.what = lastTime--;
                    handler.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    //倒计时三秒
    private static final int PORT = 8081;
    private static final String Host = "192.168.1.153";
// id集合
//    private List<Integer> usersList=new ArrayList<>();
//    WeakReference weak=new WeakReference(handler);
    private  Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
//                计时器
                case 400://SOCGAME
                    if (x>0||y>0||z>0){
                        if (z==0){
                            if (y==0){
                                z=59;
                                y=59;
                                x--;
                            }else{
                                z=59;
                                y--;
                            }
                        }else {
                            z--;
                        }
                        tv1_timer.setText(x/10+"");
                        tv2_timer.setText(x%10+"");
                        tv3_timer.setText(y/10+"");
                        tv4_timer.setText(y%10+"");
                        tv5_timer.setText(z/10+"");
                        tv6_timer.setText(z%10+"");
                    }else{
                        isGaming=false;
                        gametime=System.currentTimeMillis()-gametime;
                        addrecord();
                    }
                    break;
                case 300://SOCGAME

                    break;
                case 200://SOCSTATUS
//                    假如都准备好了  可以start 然后开始
                    startyes.setVisibility(View.VISIBLE);
//假如是非房主  游戏开始 倒计时
                    readyno.setVisibility(View.INVISIBLE);
//                    Threetoone.start();
                    break;
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
                    isGaming=true;
//                    倒计时开始
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(isGaming){
                                    Thread.sleep(1000);
                                    Message message=Message.obtain();
                                    message.what=400;
                                    handler.sendMessage(message);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
            }
        }
    };
//   通过软应用来实现 避免内存泄漏
    WeakReference soft=new WeakReference(sHandler);
    Handler handler= (Handler) soft.get();

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkmodel);
        //非房主状态收到roomid
        Uri data = getIntent().getData();
        if (data!=null)
            roomId = data.getQueryParameter("id");

        api = WXAPIFactory.createWXAPI(this, UrlCollect.WXAppID);
        api.registerApp(WXAppID);

        

        gametime=System.currentTimeMillis();

//        初始化socket lib
        SocketConfig socketConfig = new SocketConfig.Builder(getApplicationContext())
                .setIp(Host)//ip
                .setPort(PORT)//端口
                .setReadBufferSize(10240)//readBuffer
                .setIdleTimeOut(30)//客户端空闲时间,客户端在超过此时间内不向服务器发送数据,则视为idle状态,则进入心跳状态
//                .setTimeOutCheckInterval(10)//客户端连接超时时间,超过此时间则视为连接超时
                .setRequestInterval(10)//请求超时间隔时间
//                .setHeartbeatRequest("(1,1)\r\n")//与服务端约定的发送过去的心跳包
//                .setHeartbeatResponse("(10,10)\r\n") //与服务端约定的接收到的心跳包
                .builder();
        ContentServiceHelper.bindService(this, socketConfig);
        SessionManager.getInstance().setReceivedResponseListener(this);

        Log.e("========", "设置完成: ");
        initView();
    }

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
//        light2 = findViewById(R.id.iv_light_two_pkmodel);
        ImageView icon2 = findViewById(R.id.iv_icon_two_pkmodel);
        name2 = findViewById(R.id.tv_name_two_pkmodel);
        name2.setText(MyApplication.NAME);
        if (MyApplication.USERICON!=null)
            Picasso.with(this).load(MyApplication.USERICON).into(icon2);
        int sex = (int) SharedPFUtils.getParam(this, "sex", 0);
        switch (sex){
            case 11:
                person2.setImageDrawable(getResources().getDrawable(R.mipmap.man_one));
                break;
            case 12:
                person2.setImageDrawable(getResources().getDrawable(R.mipmap.man_two));
                break;
            case 21:
                person2.setImageDrawable(getResources().getDrawable(R.mipmap.woman_one));
                break;
            case 22:
                person2.setImageDrawable(getResources().getDrawable(R.mipmap.woman_two));
                break;
        }
        //3
        person3 = findViewById(R.id.iv_person_three_pkmodel);
        light3 = findViewById(R.id.iv_light_three_pkmodel);
        icon3 = findViewById(R.id.iv_icon_three_pkmodel);
        name3 = findViewById(R.id.tv_name_three_pkmodel);
//邀请好有监听
        name1.setOnClickListener(this);
        name3.setOnClickListener(this);
//点击按钮
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
//
        if (isHost){
//            light2.setVisibility(View.VISIBLE);
            startno.setVisibility(View.VISIBLE);
//            startyes.setVisibility(View.VISIBLE);
            addroom();
        }else{
            ll.setClickable(false);
            readyyes.setVisibility(View.VISIBLE);
            joinRoom();
        }
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
//                非游戏状态下  计时器有点击事件 非房主无反应
                if (!isGaming||!isHost)
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
//上传时间
                String time=tv1_timer.getText().toString()+ tv2_timer.getText()+":"+
                            tv3_timer.getText()+tv4_timer.getText()+":"+
                            tv5_timer.getText()+ tv6_timer.getText();
                requestTime(time);
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
                requestStart();
                Threetoone.start();
                break;
            case R.id.iv_readyyes_pkmodel:
//               不是房主 准备好了
                readyyes.setVisibility(View.INVISIBLE);
                readyno.setVisibility(View.VISIBLE);
//                light2.setVisibility(View.VISIBLE);
//                TODO:socket发送状态
//                ContentServiceHelper.sendClientMsg( "\n");
                changeStatus();
                break;
            case R.id.iv_click_pkmodel:
                name2.setText(++pkCount);
//                SocStatusBean status = new SocStatusBean();
//                status.setUserId();

                ContentServiceHelper.sendClientMsg(pkCount + "\n");
                break;
            case R.id.tv_name_one_pkmodel:
            case R.id.tv_name_three_pkmodel:
                if (!name1.getText().equals("邀请好友"))
                    return;
                if (!name3.getText().equals("邀请好友"))
                    return;
//                微信url分享界面
                WXWebpageObject webpaget=new WXWebpageObject();
                webpaget.webpageUrl="http://www.dt.pub/share/#/?roomId="+roomId;
                WXMediaMessage msg=new WXMediaMessage(webpaget);
                msg.title="抖腿大乐斗";
                msg.description="一玩就上瘾的游戏!";
                SendMessageToWX.Req req=new SendMessageToWX.Req();
                req.message=msg;
                req.scene=SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);
                break;
        }
    }

    @Override
    public void socketMessageReceived(String msg) {
        Log.e("===========", "socketMessageReceived: "+msg);
    }
//房主开始游戏
    private void requestStart() {
        OkGo.post(UrlCollect.updateRoom)//
                .tag(this)//
                .params("roomId", roomId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        不必提示
                    }
                });
    }

// 非房主改变状态
    private void changeStatus() {
        OkGo.post(UrlCollect.updateRoomStatus)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("roomId", roomId)
                .params("status", "2")//0 退出 1 未准备  2 已准备
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        不必提示
                    }
                });
    }

//房主上传时间
    private void requestTime(String time) {
        OkGo.post(UrlCollect.updateRoomTime)//
                .tag(this)//
                .params("roomId", MyApplication.USERID)
                .params("time", time)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        不必提示
                    }
                });
    }
//房主创建 房间
    private void addroom() {
        OkGo.post(UrlCollect.addRoom)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("status", "0")//0  手动  1脚动
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (null!=s){
                            JSONObject jo= null;
                            try {
                                jo = new JSONObject(s);
                                JSONObject data = jo.getJSONObject("data");
                                roomId=data.getInt("roomId")+"";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
//    加入房间
    private void joinRoom() {
        OkGo.post(UrlCollect.intoRoomJudge)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("roomId", roomId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }
                });
    }
//  退出房间
    private void exitRoom() {
        OkGo.post(UrlCollect.updateRoomUserStatus)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("roomId", roomId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
//    游戏结束????谁发送
    private void gameOver() {
        OkGo.post(UrlCollect.overRoom)//
                .tag(this)//
                .params("userId", "")//1,2,3（多用户，拼接）
                .params("roomId", "")
                .params("time", "")//00:56:21
                .params("status", "")//0（手动）1（脚动）
                .params("shakeNum", "")//12312,43134,51356,（对应用户，拼接） 抖动数
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
//增加游戏记录
    private void addrecord() {
        gametime=System.currentTimeMillis()-gametime;
        OkGo.post(UrlCollect.addRecord)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("time", gametime+"")
                .params("shakeNum", pkCount+"")
                .params("type", "0")//0（自己）1（好友）  用得着传好友么?
                .params("roomId", roomId+"")
                .params("status", "0")//0（手动）1（脚动）
                .params("mode", "3")//1（挑战）2（故事）3（pk）4（休闲）
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
                z=datetime.charAt(6)*10+datetime.charAt(7);
                y=datetime.charAt(3)*10+datetime.charAt(4);
                x=datetime.charAt(0)*10+datetime.charAt(1);

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
        exitRoom();
        if (!isHost)
//            todo:
        ContentServiceHelper.unBindService(this);
    }
}

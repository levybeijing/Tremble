package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
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

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.bean.AddRoomBean;
import friendgoods.vidic.com.generalframework.activity.bean.GamerBean;
import friendgoods.vidic.com.generalframework.activity.bean.ListGamerBean;
import friendgoods.vidic.com.generalframework.activity.bean.PKSocketBean;
import friendgoods.vidic.com.generalframework.activity.bean.PKSocketBeanx;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.widget.OnTimeSelectListener;
import friendgoods.vidic.com.generalframework.widget.TimePickerBuilder;
import friendgoods.vidic.com.generalframework.widget.TimePickerView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class PKModelActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv1_timer,tv2_timer,tv3_timer,tv4_timer,tv5_timer,tv6_timer;
//    时间选择器
    private TimePickerView pvCustomTime;
//   是否正在游戏
    private static boolean isGaming=false;
//   是否是房主
    private static boolean isHost=true;
//
    private static boolean havetime=false;

    private static boolean haveready=false;

    private LinearLayout ll;
    private ImageView readyno;
    private ImageView readyyes;
    private ImageView startyes;
    private ImageView startno;
//  点击数
    private static int pkCount=0;
//  倒数计时开始
    private static int lastTime=2;

    private ImageView three;
    private ImageView two;
    private ImageView one;
    private ImageButton click;
    //    socket赋值
    private ImageView person1;
    private ImageView light1;
    private ImageView light2;
    private ImageView icon1;
    private TextView name1;
    private ImageView person3;
    private ImageView light3;
    private ImageView icon3;
    private TextView name3;
    private List<Integer> numlist=new ArrayList<>();
    private TextView name2;
    private int roomId;
    private long gametime;
    private ScaleAnimation animation,animation1;

//    计时器
    private int x;
    private int y;
    private int z;
//    三秒倒计时
    private Thread Threetoone = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (lastTime >= 0) {
                    Thread.sleep(1000);
                    Message message = Message.obtain();
                    message.what = lastTime--;
                    sHandler.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    private  Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
//                计时器
                case 400://SOCGAME
                    setTime();
                    break;
                case 2:
                    three.setVisibility(View.GONE);
                    two.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    two.setVisibility(View.GONE);
                    one.setVisibility(View.VISIBLE);
                    break;
                case 0:
                    one.setVisibility(View.GONE);
                    readyno.setVisibility(View.GONE);
                    readyyes.setVisibility(View.GONE);
                    startyes.setVisibility(View.GONE);
                    startno.setVisibility(View.GONE);

                    click.setVisibility(View.VISIBLE);
                    click_left.setVisibility(View.VISIBLE);
                    click_righr.setVisibility(View.VISIBLE);
                    if (!idlist.get(0).equals("0"))
                        name1.setText("0");
                    if (!idlist.get(1).equals("0"))
                        name3.setText("0");
                    name2.setText("0");
                    //                    开始游戏
//                    倒计时开始   结束时 停止点击  然后网络访问 重置数据
                    isGaming=true;
//                    游戏时间开始
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(isGaming){
                                    Thread.sleep(1000);
                                    Message message=Message.obtain();
                                    message.what=400;
                                    sHandler.sendMessage(message);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    Toast.makeText(PKModelActivity.this, "开始游戏", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private ImageView iv_sure;
    private ImageView click_left;
    private ImageView click_righr;
    private String time;
    private ImageView iv_note,iv_detail;
    private boolean note=true;
    private void setTime() {
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
//           发送游戏结束衔接
            click.setClickable(false);
            isGaming=false;
            havetime=false;
            haveready=false;
            gametime=System.currentTimeMillis()-gametime;
            Toast.makeText(this, "游戏结束", Toast.LENGTH_SHORT).show();
//            game over
            PKSocketBean end =new PKSocketBean();
            end.setType("6");
            end.setRoomId(roomId+"");
            end.setUserId(currentId+"");
            sendMessage(new Gson().toJson(end));
//            jump
            Intent intent=new Intent(this,PKRankActivity.class);
            intent.putExtra("roomId",roomId);
            startActivity(intent);
            finish();
            addrecord();
        }
    }

    //   通过软应用来实现 避免内存泄漏
//    WeakReference soft=new WeakReference(sHandler);
//    Handler handler= (Handler) soft.get();

    private IWXAPI api;
    private String currentId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkmodel);
        api = WXAPIFactory.createWXAPI(this, UrlCollect.WXAppID);
        api.registerApp(WXAppID);
        gametime=System.currentTimeMillis();
        currentId= ""+(int) SharedPFUtils.getParam(this,"userId",0);
//        connect();
        initView();
    }

    private void initView() {
        ll = findViewById(R.id.ll_timer_pk);
        ll.setOnClickListener(this);

        tv1_timer= findViewById(R.id.tv1_timer_pk);
        tv2_timer= findViewById(R.id.tv2_timer_pk);
        tv3_timer= findViewById(R.id.tv3_timer_pk);
        tv4_timer= findViewById(R.id.tv4_timer_pk);
        tv5_timer= findViewById(R.id.tv5_timer_pk);
        tv6_timer= findViewById(R.id.tv6_timer_pk);

        initCustomTimePicker();

        iv_sure = findViewById(R.id.iv_sure_pkmodel);
        iv_sure.setOnClickListener(this);
        findViewById(R.id.iv_exit_pkmodel).setOnClickListener(this);
//

        //1
        person1 = findViewById(R.id.iv_person_one_pkmodel);
        light1 = findViewById(R.id.iv_light_one_pkmodel);
        icon1 = findViewById(R.id.iv_icon_one_pkmodel);
        name1 = findViewById(R.id.tv_name_one_pkmodel);
        //2 本人
        ImageView person2 = findViewById(R.id.iv_person_two_pkmodel);
        ImageView icon2 = findViewById(R.id.iv_icon_two_pkmodel);
        light2= findViewById(R.id.iv_light_two_pkmodel);

        name2 = findViewById(R.id.tv_name_two_pkmodel);
        name2.setText((String)SharedPFUtils.getParam(PKModelActivity.this,"name",""));
        String icon = (String) SharedPFUtils.getParam(PKModelActivity.this, "icon", "");
        if (icon!=null) {
            Picasso.with(this).load(icon).into(icon2);
        }
//        to do better
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
        click.setOnClickListener(this);
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
////非房主状态收到roomid
        Uri data = getIntent().getData();
        if (data!=null){
            roomId = Integer.parseInt(data.getQueryParameter("id"));
//            String friendId = data.getQueryParameter("friendId");
//            toBeFriend(friendId);
            Log.e("===========roomId", ""+roomId);
            isHost=false;
            ll.setClickable(false);
            readyyes.setVisibility(View.VISIBLE);
            light2.setVisibility(View.INVISIBLE);
            iv_sure.setVisibility(View.GONE);
            connect();
        }else{
            startno.setVisibility(View.VISIBLE);
            light2.setVisibility(View.VISIBLE);
            createroom();
        }
        //        缩放动画
        animation = new ScaleAnimation(
                1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f
        );
        animation.setDuration(100);
        animation1 = new ScaleAnimation(
                1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation1.setDuration(100);
        click_left = findViewById(R.id.iv_click_left);
        click_righr = findViewById(R.id.iv_click_right);
//
        iv_note = findViewById(R.id.iv_note_pkmodel);
        iv_note.setOnClickListener(this);
        iv_detail = findViewById(R.id.iv_notedetail_pkmodel);

    }
    //获取当前时间值
    public void getListOfTime() {
        numlist.clear();
        numlist.add(Integer.parseInt(tv1_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv2_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv3_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv4_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv5_timer.getText().toString()));
        numlist.add(Integer.parseInt(tv6_timer.getText().toString()));
        x=numlist.get(0)*10+numlist.get(1);
        y=numlist.get(2)*10+numlist.get(3);
        z=numlist.get(4)*10+numlist.get(5);
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
                getListOfTime();
                if (x==0&&y==0&&z==0){
                    Toast.makeText(this, "时间不能为0", Toast.LENGTH_SHORT).show();
                    break;
                }
                time = (x>9?x+"":"0"+x)+":"
                        +(y>9?y+"":"0"+y)+":"
                        +(z>9?z+"":"0"+z);
//settime
                PKSocketBean settime=new PKSocketBean();
                settime.setType("3");
                settime.setRoomId(roomId+"");
                settime.setTime(time);
                settime.setUserId(currentId+"");
                Log.e("===========确认时间", new Gson().toJson(settime));
                havetime=true;
                sendMessage(new Gson().toJson(settime));
                if (isHost&&haveready){
                    startyes.setVisibility(View.VISIBLE);
                    startno.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.iv_exit_pkmodel:
//exit
                PKSocketBean exit=new PKSocketBean();
                if (isHost){
                    exit.setType("10");
                    if (isGaming)
                        exit.setType("11");
                }else{
                    exit.setType("8");
                }
                exit.setRoomId(roomId+"");
                exit.setUserId(currentId+"");
                sendMessage(new Gson().toJson(exit));
                finish();
                break;
//                房主逻辑
            case R.id.iv_startyes_pkmodel:

                PKSocketBean start=new PKSocketBean();
                start.setType("4");
                start.setRoomId(roomId+"");
                start.setUserId(currentId+"");
                sendMessage(new Gson().toJson(start));
                Log.e("===========socketUrl", ""+new Gson().toJson(start));
                isGaming=true;
                startyes.setVisibility(View.INVISIBLE);
                three.setVisibility(View.VISIBLE);
//start
                Threetoone.start();
                break;
            case R.id.iv_readyyes_pkmodel:
                readyyes.setVisibility(View.INVISIBLE);
                readyno.setVisibility(View.VISIBLE);
                light2.setVisibility(View.VISIBLE);
//ready
                PKSocketBean ready=new PKSocketBean();
                ready.setRoomId(roomId+"");
                ready.setType("2");
                ready.setUserId(currentId+"");
                ready.setStatus("2");//0取消 1准备
                sendMessage(new Gson().toJson(ready));
                Log.e("===========socketUrl", ""+new Gson().toJson(ready));
                break;
            case R.id.iv_click_pkmodel:
                name2.setText(++pkCount+"");
                click_left.setAnimation(animation1);
                click_righr.setAnimation(animation);
//send count
                PKSocketBean count=new PKSocketBean();
                count.setType("5");
                count.setRoomId(roomId+"");
                count.setUserId(currentId+"");
                count.setNum(pkCount+"");
                sendMessage(new Gson().toJson(count));
                break;
            case R.id.tv_name_one_pkmodel:
            case R.id.tv_name_three_pkmodel:
                if (!isHost){
                    return;
                }
                if (!name1.getText().equals("邀请好友"))
                    return;
                if (!name3.getText().equals("邀请好友"))
                    return;
//                微信url分享界面
                WXWebpageObject webpaget=new WXWebpageObject();
                webpaget.webpageUrl="http://www.dt.pub/share/#/?roomId="+roomId+"&friendId"+MyApplication.USERID;
                WXMediaMessage msg=new WXMediaMessage(webpaget);
                msg.title="抖腿大乐斗";
                msg.description="一玩就上瘾的游戏!";
                SendMessageToWX.Req req=new SendMessageToWX.Req();
                req.message=msg;
                req.scene=SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);
                break;
            case R.id.iv_note_pkmodel:
                if (note){
                    iv_detail.setVisibility(View.VISIBLE);
                }else{
                    iv_detail.setVisibility(View.INVISIBLE);
                }
                note=!note;
                break;
        }
    }
    //接收socket信息 后期引入心跳机制
    private WebSocketConnection mConnect=new WebSocketConnection();
    private List<String> idlist=new ArrayList<>();
    private void connect() {
        final String socketUrl="ws://www.dt.pub/shakeLeg/socket/"+currentId;
        try {
            mConnect.connect(socketUrl, new WebSocketHandler() {

                private PKSocketBean pkbean;

                @Override
                public void onOpen() {
                    idlist.add("0");
                    idlist.add("0");
//                    Log.e("===========socketUrl", ""+socketUrl);
//            send socket
                    if (!isHost){
                        PKSocketBean join=new PKSocketBean();
                        join.setType("1");
                        join.setRoomId(roomId+"");
                        join.setMaster("0");
                        join.setUserId(currentId+"");
//                        Log.e("===========currentId", ""+currentId);
                        sendMessage(new Gson().toJson(join));
                        Log.e("===========join", ""+new Gson().toJson(join));
                    }else{
                        PKSocketBean add=new PKSocketBean();
                        add.setRoomId(roomId+"");
                        add.setType("1");
                        add.setUserId(currentId+"");
                        add.setMaster("1");
                        sendMessage(new Gson().toJson(add));
//                        Log.e("===========currentId", ""+currentId);
                        Log.e("===========add", ""+new Gson().toJson(add));
                    }
//                    Log.e("==============", "Status:Connect to " + socketUrl);
                }

                @Override
                public void onTextMessage(String payload) {
//keeping receive message
                    Log.e("==============", payload);

                    Map map =(Map) JSON.parse(payload);
                    String type = (String) map.get("type");
                    if (!type.equals("7")){
                        pkbean = new Gson().fromJson(payload, PKSocketBean.class);
                    }
                    Log.e("==============idlist", idlist.get(0)+"*"+idlist.get(1));

                    switch (type){
                        case "1":
                            List<GamerBean> list1 = pkbean.getUser();
//  remove current user
                            for (int i = 0; i <list1.size() ; i++) {
                                int userId = (int) SharedPFUtils.getParam(PKModelActivity.this, "userId", 0);
                                if (userId==list1.get(i).getUserId()){
                                    list1.remove(i);
                                }
                            }
                            if (list1.size()==0){
                                break;
                            }
                            if (idlist.get(0).equals(list1.get(0).getUserId()+"")||idlist.get(0).equals("0")){
                                setOne(list1.get(0));
                                idlist.remove(0);
                                idlist.add(0,list1.get(0).getUserId()+"");
                                break;
                            }
                            if (idlist.get(1).equals(list1.get(0).getUserId()+"")||idlist.get(1).equals("0")){
                                setThree(list1.get(0));
                                idlist.remove(1);
                                idlist.add(1,list1.get(0).getUserId()+"");
                                break;
                            }
                            if (list1.size()==1){
                                break;
                            }
                            if (idlist.get(0).equals(list1.get(1).getUserId()+"")||idlist.get(0).equals("0")){
                                setOne(list1.get(1));
                                idlist.remove(0);
                                idlist.add(0,list1.get(1).getUserId()+"");
                                break;
                            }
                            if (idlist.get(1).equals(list1.get(1).getUserId()+"")||idlist.get(1).equals("0")) {
                                setThree(list1.get(1));
                                idlist.remove(1);
                                idlist.add(1,list1.get(1).getUserId()+"");
                                break;
                            }
                            break;
                        case "2":
//  ready
                            String userId = pkbean.getUserId();
                            if (idlist.get(0).equals(userId)&& pkbean.getStatus().equals("2")){
                                light1.setVisibility(View.VISIBLE);
                                haveready=true;
                                if (isHost&&havetime){
                                    startyes.setVisibility(View.VISIBLE);
                                    startno.setVisibility(View.INVISIBLE);
                                }
                            }else{
                                light1.setVisibility(View.INVISIBLE);
                            }
                            if (idlist.get(1).equals(userId)&& pkbean.getStatus().equals("2")){
                                light3.setVisibility(View.VISIBLE);
                                haveready=true;
                                if (isHost&&havetime){
                                    startyes.setVisibility(View.VISIBLE);
                                    startno.setVisibility(View.INVISIBLE);
                                }
                            }else{
                                light3.setVisibility(View.INVISIBLE);
                            }
                            break;
                        case "3":
 // settime
                            if (isHost)
                                break;
                            String time1 = pkbean.getTime();
                            Log.e("=============", time1);
                            tv1_timer.setText(time1.charAt(0)+"");
                            tv2_timer.setText(time1.charAt(1)+"");
                            tv3_timer.setText(time1.charAt(3)+"");
                            tv4_timer.setText(time1.charAt(4)+"");
                            tv5_timer.setText(time1.charAt(6)+"");
                            tv6_timer.setText(time1.charAt(7)+"");
                            x=Integer.parseInt(time1.charAt(0)+"")*10+Integer.parseInt(time1.charAt(1)+"");
                            y=Integer.parseInt(time1.charAt(3)+"")*10+Integer.parseInt(time1.charAt(4)+"");
                            z=Integer.parseInt(time1.charAt(6)+"")*10+Integer.parseInt(time1.charAt(7)+"");
                            time=time1;
//                            Log.e("=============", "onTextMessage: "+x+y+z);
                            break;
                        case "4":
// start game
                            if (!isHost){
                                isGaming=true;
                                three.setVisibility(View.VISIBLE);
                                readyno.setVisibility(View.INVISIBLE);
                                Threetoone.start();
                            }
                            break;
                        case "5":
// synchronize the count of game   {type:5,roomId:1,userId:1,num:1}
                            String userId2 = pkbean.getUserId();
                            if (idlist.get(0).equals(userId2)){
                                name1.setText(pkbean.getNum()+"");
                            }
                            if (idlist.get(1).equals(userId2)){
                                name3.setText(pkbean.getNum()+"");
                            }
                            break;
                        case "6":
//game over
//no operation ,the solution is on top?
                            break;
                        case "7":
//the server send to homeowner the information of room {type:7, user:{}}
//                            String s= pkbean.getUser();
//  remove current user
                            PKSocketBeanx pkSocketBeanx = new Gson().fromJson(payload, PKSocketBeanx.class);
                            GamerBean gamerBean = pkSocketBeanx.getUser();
                            if (idlist.get(0).equals(gamerBean.getUserId()+"")||idlist.get(0).equals("0")){
                                setOne(gamerBean);
                                idlist.remove(0);
                                idlist.add(0,gamerBean.getUserId()+"");
                                break;
                            }
                            if (idlist.get(1).equals(gamerBean.getUserId()+"")||idlist.get(1).equals("0")){
                                setThree(gamerBean);
                                idlist.remove(1);
                                idlist.add(1,gamerBean.getUserId()+"");
                                break;
                            }
                            break;
                        case "8":
// member exit
                            String userId3 = pkbean.getUserId();
                            if (userId3.equals(idlist.get(0))){
                                clearOne();
                                idlist.remove(0);
                                idlist.add(0,"0");
                            }
                            if (userId3.equals(idlist.get(1))){
                                clearThree();
                                idlist.remove(1);
                                idlist.add(1,"0");
                            }
                            break;
                        case "9":
// error message
                            Toast.makeText(PKModelActivity.this, pkbean.getMessage(), Toast.LENGTH_SHORT).show();
                            break;
                        case "10":
// hoster exit before game
                            if (!isHost){
                                finish();
                            }
                            break;
                        case "11":
// hoster exit when gaming
                            if (isHost)
                                break;
                            String userId4 = pkbean.getUserId();
                            if (userId4.equals(idlist.get(0))){
                                clearOne();
                                idlist.remove(0);
                                idlist.add(0,"0");
                            }
                            if (userId4.equals(idlist.get(1))){
                                clearThree();
                                idlist.remove(1);
                                idlist.add(1,"0");
                            }
                            break;
                    }
                }
                @Override
                public void onClose(int code, String reason) {
                    Log.e("=============", "Connection lost..");
                    finish();
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }
    //房主创建 房间
    private void createroom() {
//        Log.e("==============addroom", "");
        OkGo.post(UrlCollect.addRoom)//
                .tag(this)//
                .params("userId", currentId)
                .params("status", "0")//0  手动  1脚动
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("==============addroom", s);
                        if (null!=s){
                            JSONObject jo= null;
                            try {
                                jo = new JSONObject(s);
                                JSONObject data = jo.getJSONObject("data");
                                roomId=data.getInt("roomId");
                                connect();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                });
    }
//增加游戏记录
    private void addrecord() {
//        Log.e("==============addrecord", "");
        OkGo.post(UrlCollect.addRecord)//
                .tag(this)//
                .params("userId", currentId+"")
                .params("time", time)
                .params("shakeNum", pkCount+"")
                .params("type", "0")//0（自己）1（好友）  用得着传好友么?
                .params("roomId", roomId+"")
                .params("status", "0")//0（手动）1（脚动）
                .params("mode", "3")//1（挑战）2（故事）3（pk）4（休闲）
                .params("degree", "1")//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "addrecord: "+s);

                    }
                });
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
        isHost=true;
//exit
        PKSocketBean exit=new PKSocketBean();
        if (isHost){
            exit.setType("10");
            if (isGaming)
                exit.setType("11");
        }else{
            exit.setType("8");
        }
        exit.setRoomId(roomId+"");
        exit.setUserId(currentId+"");
        sendMessage(new Gson().toJson(exit));
        mConnect.disconnect();
    }

//控件操作
    private void  setOne(GamerBean bean){
        name1.setText(bean.getName());
        Picasso.with(PKModelActivity.this).load(bean.getPhoto()).into(icon1);
        Picasso.with(PKModelActivity.this).load(UrlCollect.baseIamgeUrl+File.separator+bean.getLogo()).into(person1);
        if (bean.getStatus()==2){
            light1.setVisibility(View.VISIBLE);
        }else{
            light1.setVisibility(View.INVISIBLE);
        }
    }

    private void  setThree(GamerBean bean){
        name3.setText(bean.getName());
        Picasso.with(PKModelActivity.this).load(bean.getPhoto()).into(icon3);
        Picasso.with(PKModelActivity.this).load(UrlCollect.baseIamgeUrl+File.separator+bean.getLogo()).into(person3);
        if (bean.getStatus()==2){
            light3.setVisibility(View.VISIBLE);
        }else{
            light3.setVisibility(View.INVISIBLE);
        }
    }

    private void clearOne(){
        name1.setText("邀请好友");
        icon1.setImageDrawable(null);
        person1.setImageDrawable(null);
        light1.setVisibility(View.INVISIBLE);
    }

    private void clearThree(){
        name3.setText("邀请好友");
        icon3.setImageDrawable(null);
        person3.setImageDrawable(null);
        light3.setVisibility(View.INVISIBLE);
    }
    /**
     * 发送消息
     *
     * @param msg
     */
    private void sendMessage(String msg) {
        if (mConnect.isConnected()) {
            mConnect.sendTextMessage(msg);
        } else {
            Log.i("===========", "no connection!!");
        }
    }

    private void toBeFriend(String hostid) {
        OkGo.post(UrlCollect.inviteFriend)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("weChat", hostid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
}

package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.fragment.AllRankFragment;
import friendgoods.vidic.com.generalframework.activity.fragment.ModelFragment;
import friendgoods.vidic.com.generalframework.activity.fragment.RankFragment;
import friendgoods.vidic.com.generalframework.activity.fragment.MineFragment;
import friendgoods.vidic.com.generalframework.activity.fragment.WeekRankFragment;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static boolean mBackKeyPressed = false;
    private RadioButton  model_rg,rank_rg;
    private static int preFt;
    private List<Fragment> list=new ArrayList<>();
    public static MainActivity instance = null;
    private boolean loginstatus;
    private static boolean intopk=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
//
        MyApplication.data = getIntent().getData();
        if (MyApplication.data!=null){
            PKModelActivity.roomId=Integer.parseInt(MyApplication.data .getQueryParameter("id"));
            PKModelActivity.friendId=Integer.parseInt(MyApplication.data .getQueryParameter("friendId"))+"";
            intopk=true;
            MyApplication.data=null;
        }
//        判断登录状态
        loginstatus = (boolean) SharedPFUtils.getParam(this, "loginstatus", false);
        if (!loginstatus){
            startActivity(new Intent(this,LoginCodeActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    protected void initView() {

        model_rg = findViewById(R.id.dou_rg);
        rank_rg = findViewById(R.id.rank_rg);

        list.add(new RankFragment());
        list.add(new ModelFragment());
        list.add(new MineFragment());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, list.get(1)).commit();
        model_rg.setChecked(true);
        preFt=1;
        //        背景音乐服务启动
        if ((boolean)SharedPFUtils.getParam(this,"voice",true)){
            startService(new Intent(this,MusicService.class));
        }
        //        礼物界面传入
        int type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            switchContent(0);
            rank_rg.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank_rg:
                switchContent(0);
                break;
            case R.id.dou_rg:
                switchContent(1);
                break;
            case R.id.mine_rg:
                switchContent(2);
                break;
        }
    }

    public void switchContent(int i) {
        if (i==preFt){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (list.get(i).isAdded()){
            transaction.show(list.get(i)).hide(list.get(preFt)).commit();
        }else{
            transaction.add(R.id.frame_layout,list.get(i)).hide(list.get(preFt)).commit();
        }
        preFt=i;
    }

    @Override
    protected void onResume() {
        super.onResume();
        PKModelActivity.isHost=true;
        PKModelActivity.degree=1;
        //        持久化保存?
        if (intopk){
            Intent intent=new Intent(this,PKModelActivity.class);
            intent.putExtra("forpk",true);
//            intent.putExtra("roomId",Integer.parseInt(MyApplication.data .getQueryParameter("id")));
//            intent.putExtra("friendId",Integer.parseInt(MyApplication.data .getQueryParameter("friendId")));
            startActivity(intent);
            intopk=false;
        }

    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtils.shortToast("再按一次退出程序");
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);//延时两秒，如果超出则擦错第一次按键记录
        } else {
            this.finish();
            System.exit(0);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

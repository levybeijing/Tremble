package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.SingleWallActivity;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.bean.MyWallBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerMine;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterFriendName;
import friendgoods.vidic.com.generalframework.bean.FansBangBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class FriendNameActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView rv;
    private AdapterFriendName adapter;
    private List<FansBangBean.DataBean.PageInfoBean.ListBean> list;
    private String userId;
    private String wallId;
    private RelativeLayout container;
    private float scale;
    private String logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendname);
        //获取特定ID
        userId = getIntent().getStringExtra("userId");
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_friendname).setOnClickListener(this);
        findViewById(R.id.tv_info_friendname).setOnClickListener(this);
        findViewById(R.id.tv_vipwall_friendname).setOnClickListener(this);
        findViewById(R.id.tv_fans_friendname).setOnClickListener(this);
        findViewById(R.id.tv_history_friendname).setOnClickListener(this);
        findViewById(R.id.iv_history_friendname).setOnClickListener(this);
        findViewById(R.id.tv_pubsend_friendname).setOnClickListener(this);

        container = findViewById(R.id.container_friendname);
//
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int wid = (int) (density*343);
        scale = wid/325.0f;

        rv = findViewById(R.id.rv_friendname);
        GridLayoutManager  manager=new GridLayoutManager(this,3);
        rv.setLayoutManager(manager);
        adapter = new AdapterFriendName(this);
        rv.setAdapter(adapter);

        request();

        adapter.setOnItemClickListener(new OnItemClickListenerMine() {
            @Override
            public void onItemClick(int i) {
                //点击就进入粉丝霸榜
                Intent intent = new Intent(FriendNameActivity.this, SingleWallActivity.class);
                intent.putExtra("wallId",i);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestWall();
    }

    private void requestWall() {
        OkGo.post(UrlCollect.getPresentsWall)//
                .tag(this)//
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "onSuccess: "+s);
                        MyWallBean myWallBean = new Gson().fromJson(s, MyWallBean.class);
                        logo=myWallBean.getData().getLogo();
                        wallId=myWallBean.getData().getId()+"";
                        List<MyWallBean.DataBean.AxleBean> axle = myWallBean.getData().getAxle();
                        for (int i = 0; i < axle.size(); i++) {
                            MyWallBean.DataBean.AxleBean bean = axle.get(i);
                            int realx= (int) (Float.parseFloat(bean.getXaxle())*scale);
                            int realy= (int) (Float.parseFloat(bean.getYaxle())*scale);
                            int realhight= (int) (Integer.parseInt(bean.getHigh())*scale);
                            int realweith= (int) (Integer.parseInt(bean.getWide())*scale);
                            Log.e("=============", "onSuccess: "+Float.parseFloat(bean.getXaxle()));
                            Log.e("=============", "onSuccess: "+Float.parseFloat(bean.getYaxle()));
                            Log.e("=============", "onSuccess: "+Integer.parseInt(bean.getHigh()));
                            Log.e("=============", "onSuccess: "+Integer.parseInt(bean.getWide()));


                            Log.e("=============", "onSuccess: "+realx);
                            Log.e("=============", "onSuccess: "+realy);
                            Log.e("=============", "onSuccess: "+realhight);
                            Log.e("=============", "onSuccess: "+realweith);
                            //传入自己的真实像素
                            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                    realweith, realhight);
                            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
                            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
                            //实现随机出现  限定坐标 父控件宽高-子空间宽高  不能保存移动后位置 ? 还是在别的地方
                            lp.leftMargin=realx;
                            lp.topMargin= realy;
                            ImageView view=new ImageView(FriendNameActivity.this);
                            Picasso.with(FriendNameActivity.this).load(UrlCollect.baseIamgeUrl+File.separator+bean.getUrl()).into(view);
                            view.setLayoutParams(lp);
                            container.addView(view);
                        }
                    }
                });
    }

    private void request() {
        OkGo.post(UrlCollect.wallOfFriend)//
                .tag(this)//
                .params("userId", userId)
                .params("page", "1")
                .params("pageSize", "10")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        FansBangBean fansBangBean = new Gson().fromJson(s, FansBangBean.class);
                        list = fansBangBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_friendname:
                finish();
                break;
            case R.id.tv_info_friendname:
                //重用我的记录界面
                Intent intent1=new Intent(FriendNameActivity.this,MyRecordActivity.class);
                intent1.putExtra("userId",userId);
                startActivity(intent1);
                break;
            case R.id.tv_vipwall_friendname:
                //跳转VIP赠送墙
                Intent intent2=new Intent(FriendNameActivity.this,VIPSendWallActivity.class);
                intent2.putExtra("userId",userId);
                intent2.putExtra("logo",logo);
                intent2.putExtra("wallId",wallId);
                startActivity(intent2);
                break;
            case R.id.tv_fans_friendname:
                Intent intent=new Intent(FriendNameActivity.this,FansBangActivity.class);
                if (list.size()!=0)
                    intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
                startActivity(intent);
                break;
            case R.id.tv_history_friendname:
            case R.id.iv_history_friendname:
                //
                Intent intent3=new Intent(FriendNameActivity.this,PersonRecordActivity.class);
                intent3.putExtra("wallId",wallId);
                startActivity(intent3);
                break;
            case R.id.tv_pubsend_friendname:
                //userId
                Intent intent4=new Intent(FriendNameActivity.this,PublicWallActivity.class);
                intent4.putExtra("userId",userId);
                intent4.putExtra("wallId",wallId);
                intent4.putExtra("logo",logo);
                startActivity(intent4);
                break;
        }
    }
}

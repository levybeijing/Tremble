package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerMine;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterFriendName;
import friendgoods.vidic.com.generalframework.bean.FansBangBean;
import okhttp3.Call;
import okhttp3.Response;

public class FriendNameActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv;
    private AdapterFriendName adapter;
    private List<FansBangBean.DataBean.PageInfoBean.ListBean> list;
    private String userId;
    private String wallId;
    private RelativeLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendname);
        //获取特定ID

        Intent intent = getIntent();
        //参数1
        userId = intent.getStringExtra("userId");
        //参数2 本机用户
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

        rv = findViewById(R.id.rv_friendname);
        GridLayoutManager  manager=new GridLayoutManager(this,3);
        rv.setLayoutManager(manager);
        adapter = new AdapterFriendName(this);
        rv.setAdapter(adapter);

        request();

        requestWall();

        adapter.setOnItemClickListener(new OnItemClickListenerMine() {
            @Override
            public void onItemClick(int i) {
                //点击就进入粉丝霸榜
                startActivity(new Intent(FriendNameActivity.this,FansBangActivity.class));
            }
        });
    }

    private void requestWall() {
        OkGo.post(UrlCollect.getPresentsWall)//
                .tag(this)//
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("$$$$$$$$$$$$$$$$$$", "onSuccess: "+s);
                        //获取墙的ID  参数3

                        //获取所有参数 绘制图片
//                        container.addView();

                    }
                });
    }

    private void request() {
        OkGo.post(UrlCollect.wallOfFriend)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("page", "0")
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
                startActivity(intent3);
                break;
            case R.id.tv_pubsend_friendname:
                //userId
                Intent intent4=new Intent(FriendNameActivity.this,PublicWallActivity.class);
                intent4.putExtra("userId",userId);
                intent4.putExtra("wallId",wallId);
                startActivity(intent4);
                break;
        }
    }
}

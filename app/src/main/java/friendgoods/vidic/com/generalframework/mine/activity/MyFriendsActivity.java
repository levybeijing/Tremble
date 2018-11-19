package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPosition;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyFriends;
import friendgoods.vidic.com.generalframework.bean.MyFriendsBean;
import okhttp3.Call;
import okhttp3.Response;

public class MyFriendsActivity extends BaseActivity {

    private RecyclerView rv;
    private AdapterMyFriends adapter;
    private String WallId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myfriends);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_myfriends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //数据导入
        rv = findViewById(R.id.rv_myfriends);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyFriends(MyFriendsActivity.this);
        adapter.setHasStableIds(true);
        rv.setAdapter(adapter);
        //网络访问
        request();

        adapter.setOnItemClickListener(new OnItemClickListenerPosition() {
            @Override
            public void onItemClick(int userId) {
                //跳转到详情页面
                Intent intent=new Intent(MyFriendsActivity.this,FriendNameActivity.class);
                intent.putExtra("userId",userId+"");
                //获取特定数据 传入
                startActivity(intent);
            }
        });
    }

    private void request() {
        OkGo.post(UrlCollect.myFriends)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "1")
                .params("pageSize", "7")
                .params("type", "1")
                .params("status", "1")//0（手）1（脚）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("&&&&&&&&&&&&&&&&&&&&&&&", "onSuccess: "+s);
                        MyFriendsBean myFriendsBean = new Gson().fromJson(s, MyFriendsBean.class);
                        List<MyFriendsBean.DataBean.PageInfoBean.ListBean> list = myFriendsBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}

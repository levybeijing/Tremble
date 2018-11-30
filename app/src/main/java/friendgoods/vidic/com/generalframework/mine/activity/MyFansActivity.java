package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
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
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyFans;
import friendgoods.vidic.com.generalframework.bean.MyFansBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MyFansActivity extends BaseActivity {

    private RecyclerView rv;
    private AdapterMyFans adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_myfans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //数据导入
        rv = findViewById(R.id.rv_fans);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyFans(MyFansActivity.this);
        rv.setAdapter(adapter);

        request();
    }

    private void request() {

        OkGo.post(UrlCollect.fansList)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(this,"userId",0))
                .params("page", "1")
                .params("pageSize", "17")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(MyFansActivity.this,s);

                        MyFansBean myFansBean = new Gson().fromJson(s, MyFansBean.class);
//                        Log.e("========MyFansActivity", "onSuccess: "+s);
                        List<MyFansBean.DataBean.PageInfoBean.ListBean> list = myFansBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });

    }
}

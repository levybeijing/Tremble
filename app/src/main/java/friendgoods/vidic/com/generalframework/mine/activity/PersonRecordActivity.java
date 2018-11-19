package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterPersonRecord;
import friendgoods.vidic.com.generalframework.bean.MyRecordBean;
import okhttp3.Call;
import okhttp3.Response;

public class PersonRecordActivity extends BaseActivity {

    private TextView tv_name;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personrecord);
        //TODO:??????????界面放在哪里?
        //intent传入然后网络访问
        Intent intent = getIntent();
        //
        String userId = intent.getStringExtra("userId");

        initView();

    }

    private void initView() {

        findViewById(R.id.iv_back_personrecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv = findViewById(R.id.rv_personrecord);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        //网络访问  传入个人ID  获取个人详情内容request(int id);
        request();
    }

    private void request() {
        OkGo.post(UrlCollect.recordOfGame)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "0")
                .params("pageSize", "10")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("MyRecordActivity", "onSuccess: "+s);
                        MyRecordBean recordBean = new Gson().fromJson(s, MyRecordBean.class);
                        AdapterPersonRecord adapter =new AdapterPersonRecord(PersonRecordActivity.this,recordBean.getData().getPageInfo().getList());
                        rv.setAdapter(adapter);

                    }
                });
    }
}

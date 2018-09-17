package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.bean.MyRecordBean;
import friendgoods.vidic.com.generalframework.mine.bean.UserInfoBean;
import okhttp3.Call;
import okhttp3.Response;

public class MyRecordActivity extends Activity {

    private TextView tv_name;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String imageUrl = intent.getStringExtra("imageUrl");
        //
        initView();


    }

    private void initView() {

        findViewById(R.id.iv_back_myrecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置自定义字体样式
        tv_name = findViewById(R.id.tv_username);
        Typeface font = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
        }
        tv_name.setTypeface(font);
        //
        rv = findViewById(R.id.rv_myrecord);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        //网络访问
        request();
        //下拉刷新
        final SwipeRefreshLayout srl = findViewById(R.id.srl_myrecord);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //网络请求
                srl.setRefreshing(false);
            }
        });

        //上拉加载更多

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
                        Log.e("MyRecordActivity", "onSuccess: "+s);
                        MyRecordBean recordBean = new Gson().fromJson(s, MyRecordBean.class);

                        AdapterMyRecord adapter =new AdapterMyRecord(MyRecordActivity.this,recordBean.getData().getPageInfo().getList());
                        rv.setAdapter(adapter);

                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    }
                });
    }
}

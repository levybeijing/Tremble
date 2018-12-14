package friendgoods.vidic.com.generalframework.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.MDGridRvDividerDecoration;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyGifts;
import friendgoods.vidic.com.generalframework.bean.MyGiftsBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MyGiftsActivity extends BaseActivity {

    private RecyclerView rv;
    private AdapterMyGifts adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygifts);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_mygifts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv = findViewById(R.id.rv_mygifts);
        GridLayoutManager manager=new GridLayoutManager(this,4);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyGifts(MyGiftsActivity.this);
        rv.setAdapter(adapter);
//添加带颜色分割线
        rv.addItemDecoration(new MDGridRvDividerDecoration(
                this));
//        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.,5,Color.BLUE));

        repuest();
    }

    private void repuest() {

        OkGo.post(UrlCollect.myGifts)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(this,"userId",0)+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("###############", "onSuccess: "+s);
                        TokenCheck.toLogin(MyGiftsActivity.this,s);

                        MyGiftsBean bean = new Gson().fromJson(s, MyGiftsBean.class);
                        List<MyGiftsBean.DataBean> data = bean.getData();
                        adapter.setData(data);
                    }
                });
    }
}

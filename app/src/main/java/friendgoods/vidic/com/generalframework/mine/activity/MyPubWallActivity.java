package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyPubWall;
import friendgoods.vidic.com.generalframework.bean.IconSetBean;
import okhttp3.Call;
import okhttp3.Response;

public class MyPubWallActivity extends Activity{

    private TextView date;
    private TextView energy;
    private RelativeLayout rl;
    private RecyclerView rv;
    private AdapterMyPubWall adapter;
    private int testWallId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygiftswall);
        Intent intent = getIntent();
        testWallId = intent.getIntExtra("testWallId", -1);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_mygiftwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl = findViewById(R.id.container_mygiftwall);
        //
        date = findViewById(R.id.tv_date_mygiftwall);
        energy = findViewById(R.id.tv_energy_mygiftwall);
        //
        rv = findViewById(R.id.rv_mygiftwall);
        GridLayoutManager manager=new GridLayoutManager(this,10);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyPubWall(this);
        rv.setAdapter(adapter);
//      请求获取头像集合
        request();
//        请求墙和底部人物集
        requestWall();
    }

    private void requestWall() {
        OkGo.post(UrlCollect.getPresentsWalls)
                .tag(this)
                .params("userId",MyApplication.USERID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }

    private void request() {
        OkGo.post(UrlCollect.iconsOfSendGift)
                .tag(this)
                .params("presentsWallId",testWallId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Toast.makeText(MyPubWallActivity.this, s, Toast.LENGTH_SHORT).show();
//                        IconSetBean iconSetBean = new Gson().fromJson(s, IconSetBean.class);
//                        List<IconSetBean.DataBean> data = iconSetBean.getData();
//                        adapter.setData(data);
                    }
                });
    }
}

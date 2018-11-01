package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.MyWallBean;
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
    private int scale;
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
        scale=rl.getWidth()/325;
        //日期能量值
        date = findViewById(R.id.tv_date_mygiftwall);
        energy = findViewById(R.id.tv_energy_mygiftwall);
        //
        rv = findViewById(R.id.rv_mygiftwall);
        GridLayoutManager manager=new GridLayoutManager(this,10);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyPubWall(this);
        rv.setAdapter(adapter);
//      请求墙和底部人物集
        requestWall();
    }

    private void requestWall() {
        OkGo.post(UrlCollect.getPresentsWalls)
                .tag(this)
                .params("userId",MyApplication.USERID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        MyWallBean myWallBean = new Gson().fromJson(s, MyWallBean.class);
                        adapter.setData(myWallBean.getData().getUserPhoto());
                        List<MyWallBean.DataBean.AxleBean> axle = myWallBean.getData().getAxle();
                        for (int i = 0; i < axle.size(); i++) {
                            MyWallBean.DataBean.AxleBean bean = axle.get(i);
                            int realx=Integer.parseInt(bean.getXaxle())*scale;
                            int realy=Integer.parseInt(bean.getYaxle())*scale;
                            int realhight=Integer.parseInt(bean.getHigh())*scale;
                            int realweith=Integer.parseInt(bean.getWide())*scale;
                            //传入自己的真实像素
                            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                    realweith, realhight);
                            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
                            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
                            //实现随机出现  限定坐标 父控件宽高-子空间宽高  不能保存移动后位置 ? 还是在别的地方
                            lp.leftMargin=realx;
                            lp.topMargin= realy;
                            ImageView view=new ImageView(MyPubWallActivity.this);
                            Picasso.with(MyPubWallActivity.this).load(UrlCollect.baseIamgeUrl+File.separator+bean.getUrl()).into(view);
                            view.setLayoutParams(lp);
                            rv.addView(view);
                        }
                    }
                });
    }
}

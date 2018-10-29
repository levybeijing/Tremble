package friendgoods.vidic.com.generalframework.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPubWall;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterVIPSendWall;
import friendgoods.vidic.com.generalframework.bean.MyGiftsListBean;
import friendgoods.vidic.com.generalframework.mine.customview.MoveImageView;
import okhttp3.Call;
import okhttp3.Response;

public class VIPSendWallActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv;
    private AdapterVIPSendWall adapter;
    private RelativeLayout view;
    private List<String> giftId;
    private double scale;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipsendwall);
        giftId=new ArrayList<>();

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_vipwall).setOnClickListener(this);
        findViewById(R.id.iv_makesure_vipwall).setOnClickListener(this);
        findViewById(R.id.iv_mall_vipwall).setOnClickListener(this);
        //设置头像集合  获取好友ID  网络访问
        view = findViewById(R.id.container_vipwall);

        rv = findViewById(R.id.rv_vipwall);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        adapter = new AdapterVIPSendWall(VIPSendWallActivity.this);

        rv.setAdapter(adapter);
        //底部礼物集合
        OkGo.post(UrlCollect.myGifts)//
                .tag(this)//
                .params("userId", "27")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        MyGiftsListBean myGiftsListBean = new Gson().fromJson(s, MyGiftsListBean.class);
                        List<MyGiftsListBean.DataBean> data = myGiftsListBean.getData();
                        adapter.setData(data);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    }
                });

        adapter.setOnItemClickListener(new OnItemClickListenerPubWall() {
            @Override
            public void onItemClick(String sx,String sy,String surl,String id) {
                int x=Integer.parseInt(sx);
                int y=Integer.parseInt(sy);

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                //获取
                int dpi = dm.densityDpi;
                //图片尺寸放大缩小比率
                scale = view.getWidth()/325;
                //实际图片尺寸
                int realwidth= (int) (x* scale);
                int realheight= (int) (y* scale);
                //获取限定范围 以父控件为参照
                int left = view.getLeft();
                int top = view.getTop();
                int right = view.getRight();
                int bottom = view.getBottom();
                //传入父控件的左上右下
                MoveImageView iv=new MoveImageView(VIPSendWallActivity.this,left,top,right,bottom);
                //加载图片
                Picasso.with(VIPSendWallActivity.this).load("http://wx1.sinaimg.cn/orj360/006pnLoLgy1ft6yichmarj30j60j675x.jpg").into(iv);
                //传入自己的真实像素
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        realwidth, realheight);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
                //实现随机出现  限定坐标 父控件宽高-子空间宽高
                int xx=new Random().nextInt(right-left-realwidth);
                lp.leftMargin=xx;
                int yy = new Random().nextInt(bottom - top - realheight);
                lp.topMargin= yy;
                iv.setLayoutParams(lp);//设置布局参数
                view.addView(iv);//加载图片
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_vipwall:
                finish();
                break;
            case R.id.iv_makesure_vipwall:

                view.removeAllViews();
                break;
            case R.id.iv_mall_vipwall:

                view.removeAllViews();
                break;
        }
    }
}

package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.MyApplication;
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
    private int scale;

    private String receiveId;
    private String wallId;
    private StringBuffer yaxle;
    private StringBuffer xaxle;
    private StringBuffer gift;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipsendwall);

        Intent intent = getIntent();
        receiveId = intent.getStringExtra("userId");
        wallId = intent.getStringExtra("wallId");

        gift = new StringBuffer();
        xaxle = new StringBuffer();
        yaxle = new StringBuffer();
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
//        获取礼物集合
        requestGift();
//        TODO:点击事件后  数量的变化  非零判断
        adapter.setOnItemClickListener(new OnItemClickListenerPubWall() {
            @Override
            public void onItemClick(String sx,String sy,String surl,String id) {
                int x=Integer.parseInt(sx);
                int y=Integer.parseInt(sy);

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                //获取
//                int dpi = dm.densityDpi;
                //图片尺寸放大缩小比率
                scale = view.getWidth()/325;
                //实际图片尺寸
                int realwidth= x* scale;
                int realheight= y* scale;
                //获取限定范围 以父控件为参照
                int left = view.getLeft();
                int top = view.getTop();
                int right = view.getRight();
                int bottom = view.getBottom();
                //传入父控件的左上右下
                MoveImageView iv=new MoveImageView(VIPSendWallActivity.this,left,top,right,bottom);
                //加载图片
                Picasso.with(VIPSendWallActivity.this).load(surl).into(iv);
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
                //获取容器内所有控件 获取位置
                if (gift.length()==0){
                    yaxle.append(iv.getTop()/scale);
                    xaxle.append(iv.getLeft()/scale);
                    gift.append(id);
                }else{
                    yaxle.append(","+iv.getTop()/scale);
                    xaxle.append(","+iv.getLeft()/scale);
                    gift.append(","+id);
                }
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
                sendGift("1");
                view.removeAllViews();
                break;
            case R.id.iv_mall_vipwall:
                startActivity(new Intent(VIPSendWallActivity.this,MallActivity.class));
                view.removeAllViews();
                break;
        }
    }
    private void requestGift() {
        //底部礼物集合
        OkGo.post(UrlCollect.myGifts)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        MyGiftsListBean myGiftsListBean = new Gson().fromJson(s, MyGiftsListBean.class);
                        List<MyGiftsListBean.DataBean> data = myGiftsListBean.getData();
                        adapter.setData(data);
                    }
                });
    }
    private void sendGift(String status){
        OkGo.post(UrlCollect.sendGift)//
                .tag(this)//
                .params("giftId", String.valueOf(gift))//
                .params("userId",receiveId)//接收人ID
                .params("fansId", MyApplication.USERID)
                .params("xaxle", String.valueOf(xaxle))//
                .params("yaxle", String.valueOf(yaxle))//
                .params("presentsWallId",wallId)//墙的ID
                .params("status",status)
                .params("url","")//status为1的时候上传
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if ("请求成功".equals(message)){
                                view.removeAllViews();
                                Toast.makeText(VIPSendWallActivity.this, "已送达", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

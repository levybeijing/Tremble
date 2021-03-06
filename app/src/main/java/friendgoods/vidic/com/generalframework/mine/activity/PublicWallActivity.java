package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.bean.MyWallBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPubWall;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterPubWall;
import friendgoods.vidic.com.generalframework.bean.MyGiftsListBean;
import friendgoods.vidic.com.generalframework.mine.customview.MoveImageView;
import friendgoods.vidic.com.generalframework.mine.customview.customiconset.CircleImageView;
import friendgoods.vidic.com.generalframework.mine.customview.customiconset.PileLayout;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class PublicWallActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView rv;
    private AdapterPubWall adapter;
    private FrameLayout view;
    private PileLayout set;
    private TextView name;
    private TextView energy;
    private ImageView icon;
    private float scale;
    private String receiveId;
    private String wallId,logo;
    private StringBuffer yaxle;
    private StringBuffer xaxle;
    private StringBuffer gift;
    //用于网络访问请求收集参数
    private List<MoveImageView> imageList=new ArrayList<>();
    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicwall);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int wid = (int) (density*343);
        scale = wid/325.0f;

        Intent intent = getIntent();
        //跳转
        gift = new StringBuffer();
        xaxle = new StringBuffer();
        yaxle = new StringBuffer();

        receiveId = intent.getStringExtra("userId");
        wallId = intent.getStringExtra("wallId");
        logo = intent.getStringExtra("logo");
        initView();
    }

    private void initView() {

//        findViewById(R.id.iv_back_pubwall).setOnClickListener(this);
        name = findViewById(R.id.tv_name_pubwall);
        energy = findViewById(R.id.tv_energy_pubwall);
        icon = findViewById(R.id.iv_icon_pubwall);
        userId = (int) SharedPFUtils.getParam(this, "userId", 0);
        name.setText((String)SharedPFUtils.getParam(this,"name",""));
        Picasso.with(PublicWallActivity.this).load((String)SharedPFUtils.getParam(this,"icon","")).into(icon);
        energy.setText((int)SharedPFUtils.getParam(this,"integral",0)+"");
//
        ImageView iv_logo = findViewById(R.id.xingxiang_pubwall);
        Picasso.with(PublicWallActivity.this).load(UrlCollect.baseIamgeUrl+File.separator+logo).into(iv_logo);

        findViewById(R.id.iv_makesure_pubwall).setOnClickListener(this);
        findViewById(R.id.iv_mall_pubwall).setOnClickListener(this);
        //头像集
        set = findViewById(R.id.customiconset_pubwall);
        //容器
        view = findViewById(R.id.container_pubwall);

        rv = findViewById(R.id.rv_pubwall);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        adapter = new AdapterPubWall(PublicWallActivity.this);
        rv.setAdapter(adapter);

        //头像集访问
        OkGo.post(UrlCollect.getPresentsWall)//
                .tag(this)//
                .params("userId", receiveId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "444444444444: "+s);
                    TokenCheck.toLogin(PublicWallActivity.this,s);
                    MyWallBean myWallBean = new Gson().fromJson(s, MyWallBean.class);
                    List<MyWallBean.DataBean.UserPhotoBean> userPhoto = myWallBean.getData().getUserPhoto();
                    for (int i = 0; i < userPhoto.size(); i++) {
                        CircleImageView imageView = (CircleImageView) LayoutInflater.from(PublicWallActivity.this).inflate(R.layout.item_praise, set, false);
                        Glide.with(PublicWallActivity.this).load(userPhoto.get(i)).into(imageView);
                        set.addView(imageView);
                    }
                    }
                });
        adapter.setOnItemClickListener(new OnItemClickListenerPubWall() {

            @Override
            public void onItemClick(String sx, String sy, String surl, String id, int remove, int score) {
//                宽高 图片尺寸?
            float x=Float.parseFloat(sx);
            float y=Float.parseFloat(sy);

            if (remove==0){
                Toast.makeText(PublicWallActivity.this, "该礼物没有了,去商城购买", Toast.LENGTH_SHORT).show();
                return;
            }
            //实际图片尺寸
            int realwid= (int) (x* scale)-1;
            int realhei= (int) (y* scale)-1;
            //获取限定范围 以父控件为参照
            int left = view.getLeft();
            int top = view.getTop();
            int right = view.getRight();
            int bottom = view.getBottom();
            //传入父控件的左上右下
            MoveImageView iv=new MoveImageView(PublicWallActivity.this,left,top,right,bottom);
            //传入自己的真实像素
            iv.setFocusable(true);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    realwid, realhei);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
            //实现随机出现  限定坐标 父控件宽高-子空间宽高  不能保存移动后位置 ? 还是在别的地方
            int rx=right-left-realwid;
            if (rx<=0){
                lp.leftMargin=0;
            }else{
                lp.leftMargin=new Random().nextInt(rx);
            }
            int ry=bottom-top-realhei;
            if (ry<=0){
                lp.topMargin= 0;
            }else{
                lp.topMargin=new Random().nextInt(ry);
            }
            iv.setLayoutParams(lp);
            view.addView(iv);
            Picasso.with(PublicWallActivity.this).load(surl).into(iv);
            imageList.add(iv);
            //获取容器内所有控件 获取位置
            if (gift.length()==0){
                gift.append(id);
            }else{
                gift.append(","+id);
            }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_makesure_pubwall:
                //点击上传
               sendGift();
                break;
            case R.id.iv_mall_pubwall:
                //保存未完成?
                startActivity(new Intent(PublicWallActivity.this,MallActivity.class));
                view.removeAllViews();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestGift();
    }
    private void requestGift() {
        //底部礼物集合
        OkGo.post(UrlCollect.myGifts)//
                .tag(this)//
                .params("userId",userId+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(PublicWallActivity.this,s);

                        MyGiftsListBean myGiftsListBean = new Gson().fromJson(s, MyGiftsListBean.class);
                        List<MyGiftsListBean.DataBean> data = myGiftsListBean.getData();
                        adapter.setData(data);
                    }
                });
    }
    private void sendGift(){
        if (imageList.size()==0){
            return;
        }
        for (int i = 0; i < imageList.size(); i++) {
            if (i==0){
                yaxle.append(imageList.get(i).getY()/scale);
                xaxle.append(imageList.get(i).getX()/scale);
            }else{
                yaxle.append(","+imageList.get(i).getY()/scale);
                xaxle.append(","+imageList.get(i).getX()/scale);
            }
        }
        OkGo.post(UrlCollect.sendGift)//
                .tag(this)//
                .params("giftId", String.valueOf(gift))//
                .params("userId",receiveId)//接收人ID
                .params("fansId", userId)
                .params("xaxle", String.valueOf(xaxle))//
                .params("yaxle", String.valueOf(yaxle))//
                .params("presentsWallId",wallId)//墙的ID
                .params("status","0")
                .params("url","")//status为1的时候上传
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            TokenCheck.toLogin(PublicWallActivity.this,s);

                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if ("请求成功".equals(message)){
                                view.removeAllViews();
                                imageList.clear();
                                Toast.makeText(PublicWallActivity.this, "已送达", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

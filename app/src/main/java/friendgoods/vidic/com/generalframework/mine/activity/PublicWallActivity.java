package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPubWall;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterPubWall;
import friendgoods.vidic.com.generalframework.bean.IconSetBean;
import friendgoods.vidic.com.generalframework.bean.MyGiftsListBean;
import friendgoods.vidic.com.generalframework.bean.UserInfoBean;
import friendgoods.vidic.com.generalframework.mine.customview.MoveImageView;
import friendgoods.vidic.com.generalframework.mine.customview.customiconset.CircleImageView;
import friendgoods.vidic.com.generalframework.mine.customview.customiconset.PileLayout;
import okhttp3.Call;
import okhttp3.Response;

public class PublicWallActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rv;
    private AdapterPubWall adapter;
    private RelativeLayout view;
    private PileLayout set;
    private TextView name;
    private TextView energy;
    private ImageView icon;
    private static double scale;
    private String receiveId;
    private String wallId;
    private StringBuffer yaxle;
    private StringBuffer xaxle;
    private StringBuffer gift;
    //用于网络访问请求收集参数
    private List<MoveImageView> imageList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicwall);

        Intent intent = getIntent();
        //跳转
        gift = new StringBuffer();
        xaxle = new StringBuffer();
        yaxle = new StringBuffer();
        receiveId = intent.getStringExtra("userId");
        wallId = intent.getStringExtra("wallId");
        initView();
    }

    private void initView() {
//
        name = findViewById(R.id.tv_name_pubwall);
        energy = findViewById(R.id.tv_energy_pubwall);
        icon = findViewById(R.id.iv_icon_pubwall);
        name.setText(MyApplication.NAME);
        Picasso.with(PublicWallActivity.this).load(MyApplication.USERICON).into(icon);
        energy.setText(MyApplication.USERINTEGRAL+"");
//
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
                });
        //头像集访问
        OkGo.post(UrlCollect.iconsOfSendGift)//
                .tag(this)//
                .params("presentsWallId", receiveId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        IconSetBean iconSetBean = new Gson().fromJson(s, IconSetBean.class);
                        List<IconSetBean.DataBean> data = iconSetBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            CircleImageView imageView = (CircleImageView) LayoutInflater.from(PublicWallActivity.this).inflate(R.layout.item_praise, set, false);
                            Picasso.with(PublicWallActivity.this).load(data.get(i).getPhoto()).into(imageView);
                            set.addView(imageView);
                        }
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
//                int dpi = dm.densityDpi;
                //图片尺寸放大缩小比率
                scale = view.getWidth()/325;
//                Log.e("#############", "onItemClick: "+scale);
                //实际图片尺寸
                int realwidth= (int) (x* scale);
                int realheight= (int) (y* scale);
                //获取限定范围 以父控件为参照
                int left = view.getLeft();
                int top = view.getTop();
                int right = view.getRight();
                int bottom = view.getBottom();
                //传入父控件的左上右下
                MoveImageView iv=new MoveImageView(PublicWallActivity.this,left,top,right,bottom);
                //加载图片
                Picasso.with(PublicWallActivity.this).load("http://wx1.sinaimg.cn/orj360/006pnLoLgy1ft6yichmarj30j60j675x.jpg").into(iv);
                //传入自己的真实像素
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        360, 360);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
                //实现随机出现  限定坐标 父控件宽高-子空间宽高  不能保存移动后位置 ? 还是在别的地方
                int xx=new Random().nextInt(right-left-360);
                lp.leftMargin=xx;
                int yy = new Random().nextInt(bottom - top - 360);
                lp.topMargin= yy;
                //设置布局参数
                iv.setLayoutParams(lp);
                //加载控件
                view.addView(iv);
                //?????网络访问的集合
                imageList.add(iv);
                //?????如何收集参数
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
            case R.id.iv_makesure_pubwall:
                //获取容器内所有控件 获取位置点击上传
                OkGo.post(UrlCollect.sendGift)//
                        .tag(this)//
                        .params("giftId", String.valueOf(gift))//
                        .params("userId",receiveId)//接收人ID
                        .params("fansId", MyApplication.USERID)
                        .params("xaxle", String.valueOf(xaxle))//
                        .params("yaxle", String.valueOf(yaxle))//
                        .params("presentsWallId",wallId)//墙的ID
                        .params("status","0")
                        .params("url","")//status为1的时候上传
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                //TODO:假如成功 清屏 失败:上传未完成
//                                Log.e("!!!!!!!!!!!!!!!!!!!!", "onSuccess: "+s);
                                view.removeAllViews();
                            }
                        });
                break;
            case R.id.iv_mall_pubwall:
                //保存未完成


                startActivity(new Intent(PublicWallActivity.this,MallActivity.class));
                view.removeAllViews();
                break;
        }

    }
}

package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.bean.MyWallBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyPubWall;
import friendgoods.vidic.com.generalframework.bean.IconSetBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MyPubWallActivity extends BaseActivity {

    private TextView date;
    private TextView energy;
    private RelativeLayout rl;
    private RecyclerView rv;
    private AdapterMyPubWall adapter;
    private float scale;
    private ImageView xignxiang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygiftswall);
        initView();
    }

    private void initView() {
//
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int wid = (int) (density*343);
        scale = wid/325.0f;
 //日期能量值
        date = findViewById(R.id.tv_date_mygiftwall);
        energy = findViewById(R.id.tv_energy_mygiftwall);
//
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String strat = new SimpleDateFormat("MM-dd").format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String end = new SimpleDateFormat("MM-dd").format(calendar.getTime());
        date.setText(strat+"~"+end);
//
        xignxiang = findViewById(R.id.xingxiang_mygiftwall);

        findViewById(R.id.iv_back_mygiftwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl = findViewById(R.id.container_mygiftwall);
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
        OkGo.post(UrlCollect.getPresentsWall)
                .tag(this)
                .params("userId",(int)SharedPFUtils.getParam(this,"userId",0)+"")
                .execute(new StringCallback() {

                    private int score;
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "onSuccess: "+s);
                        TokenCheck.toLogin(MyPubWallActivity.this,s);

                        MyWallBean myWallBean = new Gson().fromJson(s, MyWallBean.class);
                        Picasso.with(MyPubWallActivity.this).load(UrlCollect.baseIamgeUrl+myWallBean.getData().getLogo()).into(xignxiang);

                        List<MyWallBean.DataBean.UserPhotoBean> userPhoto = myWallBean.getData().getUserPhoto();
                        adapter.setData(userPhoto);
                        for (int i = 0; i <userPhoto.size(); i++) {
                            score += userPhoto.get(i).getScore();
                        }
                        energy.setText(""+score);
                        List<MyWallBean.DataBean.AxleBean> axle = myWallBean.getData().getAxle();
                        for (int i = 0; i < axle.size(); i++) {
                            MyWallBean.DataBean.AxleBean bean = axle.get(i);
                            int realx= (int) (Float.parseFloat(bean.getXaxle())*scale);
                            int realy= (int) (Float.parseFloat(bean.getYaxle())*scale);
                            int realhight= (int) (Float.parseFloat(bean.getHigh())*scale);
                            int realweith= (int) (Float.parseFloat(bean.getWide())*scale);
                            //传入自己的真实像素
                            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                    realweith, realhight);
                            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
                            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
                            //实现随机出现  限定坐标 父控件宽高-子空间宽高  不能保存移动后位置 ? 还是在别的地方
                            lp.leftMargin=realx;
                            lp.topMargin= realy;
                            ImageView view=new ImageView(MyPubWallActivity.this);
                            Picasso.with(MyPubWallActivity.this).load(UrlCollect.baseIamgeUrl+bean.getUrl()).into(view);
                            view.setLayoutParams(lp);
                            rl.addView(view);
                        }
                    }
                });
    }
}

package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPubWall;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterVIPSendWall;
import friendgoods.vidic.com.generalframework.bean.MyGiftsListBean;
import friendgoods.vidic.com.generalframework.mine.customview.MoveImageView;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import okhttp3.Call;
import okhttp3.Response;

public class VIPSendWallActivity extends BaseActivity implements View.OnClickListener {
    private List<MoveImageView> imageList=new ArrayList<>();

    private RecyclerView rv;
    private AdapterVIPSendWall adapter;
    private RelativeLayout view;
    private float scale;

    private String receiveId;
    private String wallId,logo;
    private StringBuffer yaxle;
    private StringBuffer xaxle;
    private StringBuffer gift;
    private String userId;
    private ImageView iv_char;
    private String randomName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipsendwall);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int wid = (int) (density*343);
        scale = wid/325.0f;

        Intent intent = getIntent();
        receiveId = intent.getStringExtra("userId");
        wallId = intent.getStringExtra("wallId");
        logo = intent.getStringExtra("logo");
        userId = (int)SharedPFUtils.getParam(this, "userId", 0)+"";
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
//        对手
        iv_char = findViewById(R.id.xingxiang_vipwall);
//        requestChar();
        Picasso.with(this).load(UrlCollect.baseIamgeUrl+File.separator+logo).into(iv_char);
        rv = findViewById(R.id.rv_vipwall);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        adapter = new AdapterVIPSendWall(VIPSendWallActivity.this);

        rv.setAdapter(adapter);
//        获取礼物集合
        requestGift();
//
        adapter.setOnItemClickListener(new OnItemClickListenerPubWall() {
            @Override
            public void onItemClick(String sx, String sy, String surl, String id, int remove) {
                int x=Integer.parseInt(sx);
                int y=Integer.parseInt(sy);

                //实际图片尺寸
                int realwidth= (int) (x* scale)-1;
                int realheight= (int) (y* scale)-1;
                //获取限定范围 以父控件为参照
                int left = view.getLeft();
                int top = view.getTop();
                int right = view.getRight();
                int bottom = view.getBottom();
                Log.e("=============", "onSuccess: "+realwidth);
                Log.e("=============", "onSuccess: "+realheight);
                Log.e("=============", "onSuccess: "+left);
                Log.e("=============", "onSuccess: "+top);
                Log.e("=============", "onSuccess: "+right);
                Log.e("=============", "onSuccess: "+bottom);

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
//                宽度大于控件!!!!!!!!!
                int rx=right-left-realwidth;
                if (rx==0){
                    lp.leftMargin=0;
                }else{
                    lp.leftMargin=new Random().nextInt(rx);
                }
                int ry=bottom-top-realheight;
                if (ry==0){
                    lp.topMargin= 0;
                }else{
                    lp.topMargin=new Random().nextInt(ry);
                }
                iv.setLayoutParams(lp);//设置布局参数
                view.addView(iv);//加载图片
                imageList.add(iv);
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
                randomName=StringUtil.getRandomName(16);
                saveBitmap(view, randomName);
                imageList.clear();
//                view.removeAllViews();
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
                .params("userId", userId)
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
                .params("fansId", userId)
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
    //    保存图片
    public void saveBitmap(View v, String name) {
        String fileName = name + ".png";
        Bitmap bm = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        v.draw(canvas);
        File f = new File(UrlCollect.IMAGEPATH, fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //    上传图片到服务器
//        uploadToService();
    }

    private void uploadToService() {
        OkGo.post(UrlCollect.baseIamgeUrl)//
                .tag(this)//
                .params("***", "")//文件
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "上传到图片服务器: "+s);
                        uploadPng();
                    }
                });
    }

    //    上传VIP墙图片
    private void uploadPng() {
        OkGo.post(UrlCollect.updatePresentsWall)//
                .tag(this)//
                .params("url", randomName)//文件名
                .params("presentsWallId", wallId)//墙的ID
                .params("slt", "")//缩略图 省略>?
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "上传VIP墙图片: "+s);
                    }
                });
    }
}

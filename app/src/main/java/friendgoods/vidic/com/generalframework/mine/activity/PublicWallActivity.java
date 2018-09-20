package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.OnItemClickListener;
import friendgoods.vidic.com.generalframework.mine.OnItemClickListenerPubWall;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyRecord;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterPubWall;
import friendgoods.vidic.com.generalframework.mine.bean.IconSetBean;
import friendgoods.vidic.com.generalframework.mine.bean.MyGiftsListBean;
import friendgoods.vidic.com.generalframework.mine.bean.MyRecordBean;
import friendgoods.vidic.com.generalframework.mine.customview.MoveImageView;
import friendgoods.vidic.com.generalframework.mine.customview.customiconset.CircleImageView;
import friendgoods.vidic.com.generalframework.mine.customview.customiconset.PileLayout;
import okhttp3.Call;
import okhttp3.Response;

public class PublicWallActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rv;
    private AdapterPubWall adapter;
    private RelativeLayout view;
    private String presentsWallId;
    private PileLayout set;
    private int top;
    private int left;
    private int right;
    private int bottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicwall);

        Intent intent = getIntent();
        presentsWallId = intent.getStringExtra("presentsWallId");

//        TextView name = findViewById(R.id.tv_name_pubwall);
//        name.setText(intent.getStringExtra("name"));
//        TextView energy = findViewById(R.id.tv_energy_pubwall);
//        energy.setText(intent.getStringExtra("energy"));
//        ImageView icon = findViewById(R.id.iv_icon_pubwall);
//        Picasso.with(PublicWallActivity.this).load(intent.getStringExtra("icon")).into(icon);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_makesure_pubwall).setOnClickListener(this);
        findViewById(R.id.iv_mall_pubwall).setOnClickListener(this);

        //设置头像集合  获取好友ID  网络访问
        set = findViewById(R.id.customiconset_pubwall);
        //获取定位
        view = findViewById(R.id.container_pubwall);
        top = view.getTop();
        left = view.getLeft();
        right = view.getRight();
        bottom = view.getBottom();
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
                        Log.e("********************", "onSuccess: "+s);
                        MyGiftsListBean myGiftsListBean = new Gson().fromJson(s, MyGiftsListBean.class);
                        List<MyGiftsListBean.DataBean> data = myGiftsListBean.getData();
                        adapter.setData(data);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    }
                });
        //头像集访问
//        OkGo.post(UrlCollect.iconsOfSendGift)//
//                .tag(this)//
//                .params("presentsWallId", presentsWallId)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        IconSetBean iconSetBean = new Gson().fromJson(s, IconSetBean.class);
//                        List<IconSetBean.DataBean> data = iconSetBean.getData();
//
//                        for (int i = 0; i < data.size(); i++) {
//                            CircleImageView imageView = (CircleImageView) LayoutInflater.from(PublicWallActivity.this).inflate(R.layout.item_praise, set, false);
//                            Picasso.with(PublicWallActivity.this).load(data.get(i).getPhoto()).into(imageView);
//                            set.addView(imageView);
//                        }
//                    }
//
//                    @Override
//                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                    }
//                });

        adapter.setOnItemClickListener(new OnItemClickListenerPubWall() {
            @Override
            public void onItemClick(String sx,String sy,String surl) {
                int x=Integer.parseInt(sx);
                int y=Integer.parseInt(sy);
//                Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                //获取活动区外的空间高度
                int dpi = dm.densityDpi;
                int gap = dpi / 160*150;
                Log.e("ddddddpppppppp", "onItemClick: "+dpi);

                int heightPixels = dm.heightPixels;
                int widthPixels = dm.widthPixels;

//                int width = defaultDisplay.getWidth();
//                int height = defaultDisplay.getHeight();
                //图片尺寸放大缩小比率
                double scale=widthPixels/325;
                //实际图片尺寸
                int realwidth= (int) (x*scale);
                int realheight= (int) (y*scale);

                //增加子控件  随机位置  可拖动  可以传输限定位置
                MoveImageView iv=new MoveImageView(PublicWallActivity.this,widthPixels,heightPixels-gap);
                Picasso.with(PublicWallActivity.this).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3311496021,2379882468&fm=27&gp=0.jpg").into(iv);
                view.addView(iv);
                //加载图片

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_makesure_pubwall:
                //获取容器内所有控件 获取位置
                //点击上传 并清除容器内容 容器置为空


                break;
            case R.id.iv_mall_pubwall:
                //跳转

                break;
        }

    }
}

package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyRecord;
import friendgoods.vidic.com.generalframework.bean.MyRecordBean;
import friendgoods.vidic.com.generalframework.mine.customview.ColorTextView;
import okhttp3.Call;
import okhttp3.Response;

public class MyRecordActivity extends Activity {

    private ColorTextView tv_name,tv_chenghu,tv_history;
    private TextView tv_detailrecord;
    private RecyclerView rv;
    private AdapterMyRecord adapter;
    private ImageView iv_icon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //
        initView();
        //设置头部
        tv_name.setText(MyApplication.NAME);
        Picasso.with(this).load(MyApplication.USERICON).into(iv_icon);
    }

    private void initView() {
        findViewById(R.id.iv_back_myrecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置自定义字体样式
        tv_name = findViewById(R.id.tv_username_myrecord);
        tv_chenghu = findViewById(R.id.tv_mingcheng_myrecord);
        tv_history = findViewById(R.id.tv_history_myrecord);
        tv_detailrecord = findViewById(R.id.tv_detailrecord_myrecord);
        iv_icon = findViewById(R.id.iv_icon_myrecord);
        //新增需求
//        TextView hand = findViewById(R.id.tv_hand_myrecord);
//        TextView foot = findViewById(R.id.tv_foot_myrecord);
//        TextView jifen = findViewById(R.id.tv_jifen_myrecord);
        //设置字体样式 渐变
        Typeface font = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
            tv_chenghu.setTypeface(font);
            font = getResources().getFont(R.font.kuaileti);
            tv_name.setTypeface(font);
            tv_history.setTypeface(font);
        }
        //历史记录
        rv = findViewById(R.id.rv_myrecord);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyRecord(MyRecordActivity.this);
        rv.setAdapter(adapter);
        //网络访问
        request();
        //下拉呈现
//        rv.addRefreshViewCreator(new DefaultRefreshCreator());
        //上拉呈现
//        rv.addLoadViewCreator(new DefaultLoadMoreCreator());
    }

    private void request() {
        OkGo.post(UrlCollect.recordOfGame)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "0")
                .params("pageSize", "30")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        MyRecordBean recordBean = new Gson().fromJson(s, MyRecordBean.class);
                        adapter.setData(recordBean.getData().getPageInfo().getList());
//                        tv_detailrecord = findViewById(R.id.tv_detailrecord_myrecord);
//                        tv_detailrecord.setText(recordBean.getData().getPageInfo().getTotal()+"");
                    }
                });
    }

}

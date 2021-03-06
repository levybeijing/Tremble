package friendgoods.vidic.com.generalframework.mine.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.bean.RecordDetailBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyRecord;
import friendgoods.vidic.com.generalframework.bean.MyRecordBean;
import friendgoods.vidic.com.generalframework.mine.customview.ColorTextView;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MyRecordActivity extends BaseActivity {

    private ColorTextView tv_name,tv_history;

    private TextView tv_detailrecord1,tv_detailrecord2,tv_chenghu;
    private RecyclerView rv;
    private AdapterMyRecord adapter;
    private String currentId;
    private TextView hand;
    private TextView foot;
    private TextView jifen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        String userId = getIntent().getStringExtra("userId");
        if (userId==null){
            currentId=(int)SharedPFUtils.getParam(this,"userId",0) +"";
        }else{
            currentId=userId;
        }
        //
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_myrecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置自定义字体样式
//        tv_name = findViewById(R.id.tv_username_myrecord);
        tv_chenghu = findViewById(R.id.tv_mingcheng_myrecord);
        tv_history = findViewById(R.id.tv_history_myrecord);
        tv_detailrecord1 = findViewById(R.id.tv_detailrecord1_myrecord);
        tv_detailrecord2 = findViewById(R.id.tv_detailrecord2_myrecord);
//        iv_icon = findViewById(R.id.iv_icon_myrecord);
        //新增需求
        hand = findViewById(R.id.tv_hand_myrecord);
        foot = findViewById(R.id.tv_foot_myrecord);
        jifen = findViewById(R.id.tv_jifen_myrecord);
        //设置字体样式 渐变
        Typeface font = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
            tv_chenghu.setTypeface(font);
            font = getResources().getFont(R.font.kuaileti);
//            tv_name.setTypeface(font);
            tv_history.setTypeface(font);
        }
        //历史记录
        rv = findViewById(R.id.rv_myrecord);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyRecord(MyRecordActivity.this);
        rv.setAdapter(adapter);
//
        requestInfo();
//
        requestRecord();
    }

    private void requestRecord() {
        OkGo.post(UrlCollect.recordOfGame)//
                .tag(this)//
                .params("userId", currentId)
                .params("page", "1")
                .params("pageSize", "30")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(MyRecordActivity.this,s);

                        Log.e("==========requestRecord", "onSuccess: "+s);
                        MyRecordBean recordBean = new Gson().fromJson(s, MyRecordBean.class);
                        adapter.setData(recordBean.getData().getPageInfo().getList());
                    }
                });
    }
    private void requestInfo() {
        OkGo.post(UrlCollect.getUserData)//
                .tag(this)//
                .params("userId", currentId)
                .params("type", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(MyRecordActivity.this,s);

                        Log.e("===========requestInfo=", "onSuccess: "+s);
                        RecordDetailBean record = new Gson().fromJson(s, RecordDetailBean.class);
                        tv_detailrecord1.setText("时长:"+record.getData().getTime());
                        tv_detailrecord2.setText("好友排名:"+(int)record.getData().getRownum());
                        hand.setText(record.getData().getSShakeNum()+"");
                        foot.setText(record.getData().getJShakeNum()+"");
                        jifen.setText((int)record.getData().getIntegral()+"");
                    }
                });
    }
}

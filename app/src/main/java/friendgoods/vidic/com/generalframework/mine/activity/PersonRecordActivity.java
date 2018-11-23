package friendgoods.vidic.com.generalframework.mine.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterWallRecord;
import friendgoods.vidic.com.generalframework.mine.bean.WallRecordBean;
import okhttp3.Call;
import okhttp3.Response;

public class PersonRecordActivity extends BaseActivity {

    private RecyclerView rv;
    private String wallId;
    private AdapterWallRecord adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personrecord);
        //
        wallId = getIntent().getStringExtra("wallId");
        initView();
    }

    private void initView() {

        findViewById(R.id.iv_back_personrecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv = findViewById(R.id.rv_personrecord);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterWallRecord(this);
        rv.setAdapter(adapter);
        request();
    }

    private void request() {
        OkGo.post(UrlCollect.getPresentWallallScore)//
                .tag(this)//
                .params("presentWallId", wallId)
                .params("page", "1")
                .params("pageSize", "20")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("================", "onSuccess: "+s);
                        WallRecordBean wallRecordBean = new Gson().fromJson(s, WallRecordBean.class);
                        List<WallRecordBean.DataBean.PageInfoBean.ListBean> list = wallRecordBean.getData().getPageInfo().getList();
                        if (list==null||list.size()==0){
                            setContentView(R.layout.emptylist);
                            return;
                        }
                        adapter.setData(list);
                    }
                });
    }
}

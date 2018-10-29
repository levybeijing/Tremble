package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerMine;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterFansBangBottom;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterFansBangTop;
import friendgoods.vidic.com.generalframework.bean.FansBangBean;
import okhttp3.Call;
import okhttp3.Response;

public class FansBangActivity extends AppCompatActivity {

    private RecyclerView rv_top;
    private RecyclerView rv_bottom;
    private AdapterFansBangTop adapter;
    private AdapterFansBangBottom adapter2;
    private List<FansBangBean.DataBean.PageInfoBean.ListBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fansbang);

        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("list");

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_fansbang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        rv_top = findViewById(R.id.rv_top_fansbang);
        rv_bottom = findViewById(R.id.rv_bottom_fansbang);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        rv_top.setLayoutManager(manager);
        LinearLayoutManager manager2=new LinearLayoutManager(this);
        rv_bottom.setLayoutManager(manager2);

        adapter = new AdapterFansBangTop(this);
        rv_top.setAdapter(adapter);

        adapter2 = new AdapterFansBangBottom(this);
        rv_bottom.setAdapter(adapter2);

        if (list==null||list.size()==0){
            //网络访问
            request();
        }else{
            adapter.setData(list);
            adapter2.setData(list);
        }
        adapter.setOnItemClickListener(new OnItemClickListenerMine() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(FansBangActivity.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        });
        adapter2.setOnItemClickListener(new OnItemClickListenerMine() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(FansBangActivity.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void request() {

        OkGo.post(UrlCollect.wallOfFriend)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "0")
                .params("pageSize", "10")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("******************", "onSuccess: "+s);
                        FansBangBean fansBangBean = new Gson().fromJson(s, FansBangBean.class);
                        list = fansBangBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                        adapter2.setData(list);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    }
                });
    }
}

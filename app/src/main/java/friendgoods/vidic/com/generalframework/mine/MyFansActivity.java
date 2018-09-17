package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.bean.MyFansBean;
import friendgoods.vidic.com.generalframework.mine.bean.UserInfoBean;
import okhttp3.Call;
import okhttp3.Response;

public class MyFansActivity extends Activity {

    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_myfans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //数据导入
        rv = findViewById(R.id.rv_fans);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);


        request();
    }

    private void request() {

        OkGo.post(UrlCollect.persenalDetail)//
                .tag(this)//
                .params("userId", "27")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("OrderFragment", "onSuccess: "+s);
                        MyFansBean myFansBean = new Gson().fromJson(s, MyFansBean.class);
                        List<MyFansBean.DataBean.PageInfoBean.ListBean> list = myFansBean.getData().getPageInfo().getList();

                        AdapterMyFans adapter=new AdapterMyFans(MyFansActivity.this,list);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

                    }
                });

    }
}

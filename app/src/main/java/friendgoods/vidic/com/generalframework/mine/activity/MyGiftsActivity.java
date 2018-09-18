package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyFriends;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyGifts;
import friendgoods.vidic.com.generalframework.mine.bean.MyFriendsBean;
import friendgoods.vidic.com.generalframework.mine.bean.MyGiftsBean;
import okhttp3.Call;
import okhttp3.Response;

public class MyGiftsActivity extends Activity {

    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygifts);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_mygifts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv = findViewById(R.id.rv_mygifts);

        GridLayoutManager manager=new GridLayoutManager(this,4);
        rv.setLayoutManager(manager);
//        AdapterMyGifts adapter=new AdapterMyGifts();
//        rv.setAdapter(adapter);

        repuest();
    }

    private void repuest() {

        OkGo.post(UrlCollect.myFriends)//
                .tag(this)//
                .params("isUse", "27")
                .params("page", "0")
                .params("pageSize", "12")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("MyGiftsActivity", "onSuccess: "+s);
                        MyGiftsBean bean = new Gson().fromJson(s, MyGiftsBean.class);
                        List<MyGiftsBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        AdapterMyGifts adapter=new AdapterMyGifts(MyGiftsActivity.this,list);
                        rv.setAdapter(adapter);

                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

                    }
                });
    }
}

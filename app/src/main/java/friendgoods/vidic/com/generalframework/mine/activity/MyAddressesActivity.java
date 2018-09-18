package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyAddresses;
import friendgoods.vidic.com.generalframework.mine.bean.AddressesBean;
import friendgoods.vidic.com.generalframework.mine.bean.DetailGoodsBean;
import okhttp3.Call;
import okhttp3.Response;

public class MyAddressesActivity extends Activity {
    private static final int RETURNCODE=1000;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddresses);

        initView();

        request();
    }

    private void request() {
        OkGo.post(UrlCollect.addresses)
                .tag(this)
                .params("userId","27")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        AddressesBean addressesBean = new Gson().fromJson(s, AddressesBean.class);
                        List<AddressesBean.DataBean> data = addressesBean.getData();
                        AdapterMyAddresses adapter=new AdapterMyAddresses(MyAddressesActivity.this,data);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

                    }
                });

    }

    private void initView() {
        ImageView iv_top = findViewById(R.id.iv_top_addresses);
        ImageView iv_add = findViewById(R.id.iv_addaddress);
        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///跳转
                startActivityForResult(new Intent(MyAddressesActivity.this,AddAddressActivity.class),RETURNCODE);
            }
        });

        //添加适配器 数据
        rv = findViewById(R.id.rv_myaddress);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

    }


    //onactivity

}

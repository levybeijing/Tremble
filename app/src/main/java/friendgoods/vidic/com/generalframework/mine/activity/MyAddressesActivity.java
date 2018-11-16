package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerAddress;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyAddresses;
import friendgoods.vidic.com.generalframework.bean.AddressesBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MyAddressesActivity extends Activity {
    private RecyclerView rv;
    private AdapterMyAddresses adapter;
    //是否是回传数据
    private boolean retun=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddresses);

        Intent intent = getIntent();
        retun=intent.getBooleanExtra("forResult",false);
        initView();
        adapter.setOnItemClickListener(new OnItemClickListenerAddress() {
            @Override
            public void onItemClick(AddressesBean.DataBean i) {
                if (retun){
                    retun=!retun;
                    Intent intent=new Intent();
                    intent.putExtra("bean",i);
                    setResult(10000,intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    private void request() {
        OkGo.post(UrlCollect.addresses)
                .tag(this)
                .params("userId",(int)SharedPFUtils.getParam(this,"userId",0))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("======================", "onSuccess: "+s);
                        AddressesBean addressesBean = new Gson().fromJson(s, AddressesBean.class);
                        List<AddressesBean.DataBean> data = addressesBean.getData();
                        adapter.setData(data);
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
                startActivity(new Intent(MyAddressesActivity.this,AddAddressActivity.class));
            }
        });

        //添加适配器 数据
        rv = findViewById(R.id.rv_myaddress);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterMyAddresses(MyAddressesActivity.this);
        rv.setAdapter(adapter);
    }
}

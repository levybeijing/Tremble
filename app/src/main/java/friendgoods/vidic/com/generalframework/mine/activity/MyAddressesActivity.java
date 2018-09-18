package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import friendgoods.vidic.com.generalframework.R;

public class MyAddressesActivity extends Activity {
    private static final int RETURNCODE=1000;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddresses);
        initView();
    }

    private void initView() {
        ImageView iv_top = findViewById(R.id.iv_top_addresses);
        ImageView iv_add = findViewById(R.id.iv_addaddress);
        //添加适配器 数据



        rv = findViewById(R.id.rv_myaddress);


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
    }


    //onactivity

}

package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import friendgoods.vidic.com.generalframework.R;

public class MyFansActivity extends Activity {
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
        RecyclerView rv = findViewById(R.id.rv_fans);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        AdapterMyFans adapter=new AdapterMyFans(this);
        rv.setAdapter(adapter);

    }
}

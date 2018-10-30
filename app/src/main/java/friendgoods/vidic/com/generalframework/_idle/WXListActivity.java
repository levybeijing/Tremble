package friendgoods.vidic.com.generalframework._idle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import friendgoods.vidic.com.generalframework.R;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class WXListActivity extends AppCompatActivity {

//    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxlist);

        initView();

//        api=WXAPIFactory.createWXAPI(this,WXAppID,true);
//        api.registerApp(WXAppID);
    }

    private void initView() {
        RecyclerView rv = findViewById(R.id.rv_wxlist);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

//        rv.setAdapter();
    }
}

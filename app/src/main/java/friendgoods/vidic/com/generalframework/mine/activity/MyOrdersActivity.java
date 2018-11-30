package friendgoods.vidic.com.generalframework.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMyOrders;
import friendgoods.vidic.com.generalframework.mine.frag.FragOrders1;
import friendgoods.vidic.com.generalframework.mine.frag.FragOrders2;
import friendgoods.vidic.com.generalframework.mine.frag.FragOrders3;
import friendgoods.vidic.com.generalframework.mine.frag.FragOrders4;
import friendgoods.vidic.com.generalframework.mine.frag.FragOrders5;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class MyOrdersActivity extends BaseActivity {

    private String preid;
    public static IWXAPI api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        api = WXAPIFactory.createWXAPI(this, WXAppID);
        api.registerApp(WXAppID);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_myorders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ViewPager vp = findViewById(R.id.vp_myorders);
        TabLayout tab = findViewById(R.id.tab_myorders);
        //
        List<Fragment> list=new ArrayList<>();
        list.add(new FragOrders1());
        list.add(new FragOrders2());
        list.add(new FragOrders3());
        list.add(new FragOrders4());
        list.add(new FragOrders5());

        //进行关联
        vp.setAdapter(new AdapterMyOrders(getSupportFragmentManager(),list));
        tab.setTabMode(TabLayout.MODE_FIXED);

        tab.setupWithViewPager(vp);

        TabLayout.Tab tab1 = tab.getTabAt(0);
        tab1.setText("全部");
        TabLayout.Tab tab2 = tab.getTabAt(1);
        tab2.setText("待付款");
        TabLayout.Tab tab3 = tab.getTabAt(2);
        tab3.setText("待发货");
        TabLayout.Tab tab4 = tab.getTabAt(3);
        tab4.setText("待收货");
        TabLayout.Tab tab5 = tab.getTabAt(4);
        tab5.setText("已完成");

    }
}

package friendgoods.vidic.com.generalframework.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterVipWall;
import friendgoods.vidic.com.generalframework.mine.frag.FragVipWall;

public class VIPGiftsWallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipwall);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_vipwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabLayout  tab = findViewById(R.id.tab_vipwall);
        ViewPager vp = findViewById(R.id.vp_vipwall);

        List<Fragment> list=new ArrayList<>();
        list.add(new FragVipWall());
        list.add(new FragVipWall());

        //进行关联
        vp.setAdapter(new AdapterVipWall(getSupportFragmentManager(),list));
        tab.setTabMode(TabLayout.MODE_FIXED);

        tab.setupWithViewPager(vp);

        TabLayout.Tab tab1 = tab.getTabAt(0);
        tab1.setText("收到礼物");
        TabLayout.Tab tab2 = tab.getTabAt(1);
        tab2.setText("送出礼物");

    }
}

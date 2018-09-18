package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import friendgoods.vidic.com.generalframework.R;

public class MyOrdersActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
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


    }
}

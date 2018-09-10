package friendgoods.vidic.com.generalframework.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;

public class MallActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall);
        initView();
    }

    private void initView() {
        ImageView iv = findViewById(R.id.iv_back_mall);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //通过网络访问设置金币数量
        TextView tv_number = findViewById(R.id.tv_smallTitle);

        TextView tv_toGifts = findViewById(R.id.tv_mygifts_mall);
        tv_toGifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转移到我的礼物的界面
            }
        });
        //
        List<Fragment> list=new ArrayList<>();
        list.add(new FragRecommend());
        list.add(new FragGifts());

        TabLayout tab = findViewById(R.id.tab_mall);
        final ViewPager vp = findViewById(R.id.vp_mall);
        //进行关联
        vp.setAdapter(new AdapterMall(getSupportFragmentManager(),list));
        tab.setTabMode(TabLayout.MODE_FIXED);

        tab.setupWithViewPager(vp);


        //设置tab1
        TabLayout.Tab tab1 = tab.getTabAt(0);
        tab1.setCustomView(R.layout.item_tab_mall);//给每一个tab设置view
        tab1.getCustomView().findViewById(R.id.iv_item_tab_mall).setSelected(true);//第一个tab被选中
        ImageView iv1 = tab1.getCustomView().findViewById(R.id.iv_item_tab_mall);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            iv1.setBackground(getResources().getDrawable(R.mipmap.recommend_noselect_3x));//设置tab上的文字
        }
        //设置tab2
        TabLayout.Tab tab2 = tab.getTabAt(1);
        tab2.setCustomView(R.layout.item_tab_mall);//给每一个tab设置view
        ImageView iv2 = tab2.getCustomView().findViewById(R.id.iv_item_tab_mall);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            iv2.setBackground(getResources().getDrawable(R.mipmap.gift_noselete_3x));//设置tab上的文字
        }
        //TODO:设置动画
//        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                tab.getCustomView().findViewById(R.id.iv_item_tab_mall).setSelected(true);
//                vp.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.getCustomView().findViewById(R.id.iv_item_tab_mall).setSelected(false);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }
}

package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.bean.FansBangBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterMall;
import friendgoods.vidic.com.generalframework.mine.bean.JifenBean;
import friendgoods.vidic.com.generalframework.mine.frag.FragGifts;
import friendgoods.vidic.com.generalframework.mine.frag.FragRecommend;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MallActivity extends BaseActivity {

    private TextView tv_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        initView();

    }

    private void requsetIntegral() {
        OkGo.post(UrlCollect.getIntegral)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(this,"userId",0)+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(MallActivity.this,s);
                        Log.e("=============", "onSuccess: "+s);
                        JifenBean jifenBean = new Gson().fromJson(s, JifenBean.class);
                        int integral = (int) jifenBean.getData().getIntegral();
                        SharedPFUtils.setParam(MallActivity.this,"integral",integral);
                        tv_number.setText(integral+"");
                    }
                });
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
        tv_number = findViewById(R.id.tv_numberofcoin);
        requsetIntegral();
        TextView tv_toGifts = findViewById(R.id.tv_mygifts_mall);
        tv_toGifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MallActivity.this,MyGiftsActivity.class));
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
            iv1.setBackground(getResources().getDrawable(R.mipmap.tab_reme_3x_));//设置tab上的文字
        }
        //设置tab2
        TabLayout.Tab tab2 = tab.getTabAt(1);
        tab2.setCustomView(R.layout.item_tab_mall);//给每一个tab设置view
        ImageView iv2 = tab2.getCustomView().findViewById(R.id.iv_item_tab_mall);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            iv2.setBackground(getResources().getDrawable(R.mipmap.tab_gift_3x));//设置tab上的文字
        }

    }
}

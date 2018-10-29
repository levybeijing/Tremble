package friendgoods.vidic.com.generalframework.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.bean.StoryModelBean;
import friendgoods.vidic.com.generalframework.activity.fragment.ModelFragment;
import friendgoods.vidic.com.generalframework.activity.fragment.RankFragment;
import friendgoods.vidic.com.generalframework.activity.fragment.MineFragment;
import friendgoods.vidic.com.generalframework._idle.DouFragment;
import friendgoods.vidic.com.generalframework.bean.UserInfoBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static boolean mBackKeyPressed = false;
    private RadioButton  model_rg;
    private static int preFt;
    private List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        request();
    }

    private void request() {
        OkGo.post(UrlCollect.getUsers)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        UserInfoBean infoBean = new Gson().fromJson(s, UserInfoBean.class);
                        if ("请求成功".equals(infoBean.getMessage())){
                            MyApplication.NAME=infoBean.getData().getName();
                            MyApplication.USERICON=infoBean.getData().getPhoto();
                            MyApplication.USERINTEGRAL=infoBean.getData().getIntegral();
                        }
                    }
                });
    }

    protected void initView() {

        list.add(new RankFragment());
        list.add(new ModelFragment());
        list.add(new MineFragment());

        model_rg = findViewById(R.id.dou_rg);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, list.get(1)).commit();
        model_rg.setChecked(true);
        preFt=1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank_rg:
                switchContent(0);
                break;
            case R.id.dou_rg:
                switchContent(1);
                break;
            case R.id.mine_rg:
                switchContent(2);
                break;
        }
    }

    public void switchContent(int i) {
        if (i==preFt){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (list.get(i).isAdded()){
            transaction.show(list.get(i)).hide(list.get(preFt)).commit();
        }else{
            transaction.add(R.id.frame_layout,list.get(i)).hide(list.get(preFt)).commit();
        }
        preFt=i;
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtils.shortToast("再按一次退出程序");
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);//延时两秒，如果超出则擦错第一次按键记录
        } else {
            this.finish();
            System.exit(0);
        }

    }
}
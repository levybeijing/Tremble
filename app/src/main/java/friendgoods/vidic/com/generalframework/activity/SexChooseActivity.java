package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class SexChooseActivity extends BaseActivity implements View.OnClickListener {
    private  final int MANMARS =11;
    private  final int MANEARTH =12;
    private  final int WOMANMARS =21;
    private  final int WOMANEARTH =22;
    private boolean isUpdateOk=true;

    private ImageView man_mars,woman_mars,man_earth,woman_earth,bg_man,bg_woman;
    private SharedPreferences userinfo;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sexchoose);
        userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        edit = userinfo.edit();

        if (!isUpdateOk){
            requset(userinfo.getInt("sex",0));
        }
        initView();
    }

    private void initView() {

        findViewById(R.id.iv_man_sex).setOnClickListener(this);
        findViewById(R.id.iv_woman_sex).setOnClickListener(this);
//男性
        man_mars = findViewById(R.id.iv_man_mars_sex);
        man_mars.setOnClickListener(this);
        man_earth = findViewById(R.id.iv_man_earth_sex);
        man_earth.setOnClickListener(this);
//女性
        woman_mars = findViewById(R.id.iv_woman_mars_sex);
        woman_mars.setOnClickListener(this);
        woman_earth = findViewById(R.id.iv_woman_earth_sex);
        woman_earth.setOnClickListener(this);
//背景
        bg_man = findViewById(R.id.bg_man_sex);
        bg_woman = findViewById(R.id.bg_woman_sex);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
//            上部
            case R.id.iv_man_sex:
                bg_man.setVisibility(View.VISIBLE);
                bg_woman.setVisibility(View.INVISIBLE);

                man_earth.setVisibility(View.VISIBLE);
                man_mars.setVisibility(View.VISIBLE);
                woman_mars.setVisibility(View.INVISIBLE);
                woman_earth.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_woman_sex:
                bg_woman.setVisibility(View.VISIBLE);
                bg_man.setVisibility(View.INVISIBLE);

                man_earth.setVisibility(View.INVISIBLE);
                man_mars.setVisibility(View.INVISIBLE);
                woman_mars.setVisibility(View.VISIBLE);
                woman_earth.setVisibility(View.VISIBLE);
                break;
//                下部
            case R.id.iv_man_mars_sex:
                edit.putInt("sex",MANMARS);
                edit.commit();
                gotoMain();
                requset(MANMARS);
                break;
            case R.id.iv_man_earth_sex:
                edit.putInt("sex",MANEARTH);
                edit.commit();
                gotoMain();
                requset(MANEARTH);
                break;
            case R.id.iv_woman_mars_sex:
                edit.putInt("sex",WOMANMARS);
                edit.commit();
                gotoMain();
                requset(WOMANMARS);
                break;
            case R.id.iv_woman_earth_sex:
                edit.putInt("sex",WOMANEARTH);
                edit.commit();
                gotoMain();
                requset(WOMANEARTH);
                break;
        }
    }

    private void gotoMain() {
        startActivity(new Intent(SexChooseActivity.this,MainActivity.class));
        finish();
    }

    private void requset(int i) {
        String sex=(i<20?0:1)+"";
        String logo;
        if (sex.equals("0")){
            logo="man"+i%10+".png";
        }else{
            logo="woman"+i%10+".png";
        }
        OkGo.post(UrlCollect.updateSex)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("sex", sex)//0  man  1  woman
                .params("logo", logo)//man1.png  woman1.png
                .params("name", MyApplication.NAME)
                .params("photo", MyApplication.USERICON)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject jo= null;
                        try {
                            jo = new JSONObject(s);
                            String message = jo.getString("message");
                            if (!"请求成功".equals(message)){
                                isUpdateOk=false;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

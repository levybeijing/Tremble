package friendgoods.vidic.com.generalframework.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.bean.StoryModelBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class StoryModelActivity extends AppCompatActivity implements View.OnClickListener {

    private static int count=0;
    private ImageView iv_net;
    private List<Integer> numbers;
    private List<StoryModelBean.DataBean.StoryIMGBean> images;
    private int flag=0;
    private TextView tv_number;
    private long gametime;
    private ScaleAnimation animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storymodel);
        initView();

        iv_net = findViewById(R.id.iv_net_story);
        request();
        //设置形象
        ImageView person = findViewById(R.id.iv_person_storymodel);
        ImageView icon = findViewById(R.id.iv_icon_storymodel);
        TextView name = findViewById(R.id.tv_name_storymodel);
        tv_number = findViewById(R.id.tv_number_storymodel);

        name.setText((String) SharedPFUtils.getParam(this, "name", ""));
        Picasso.with(StoryModelActivity.this).load((String) SharedPFUtils.getParam(this, "icon", "")).into(icon);

        Typeface font=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
            tv_number.setTypeface(font);
        }
        //        缩放动画
        animation = new ScaleAnimation(
                1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        );
        animation.setDuration(500);
        tv_number.setAnimation(animation);
        int sex = (int) SharedPFUtils.getParam(this, "sex", 0);
        int r=0;
        switch (sex){
            case 11:
                r=R.mipmap.man_one;
                break;
            case 12:
                r=R.mipmap.man_two;
                break;
            case 21:
                r=R.mipmap.woman_one;
                break;
            case 22:
                r=R.mipmap.woman_two;
                break;
        }
        if (r!=0)
            person.setImageDrawable(getResources().getDrawable(r));
    }

    private void request() {
        OkGo.post(UrlCollect.getStoryIMG)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("========", "onSuccess: "+s);
                        StoryModelBean bean = new Gson().fromJson(s, StoryModelBean.class);
                        numbers = bean.getData().getRandom();
                        images=bean.getData().getStoryIMG();
                    }
                });
    }

    private void initView() {
        findViewById(R.id.iv_exit_storymodel).setOnClickListener(this);
        findViewById(R.id.iv_click_storymodel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_exit_storymodel:
                finish();
                break;
            case R.id.iv_click_storymodel:
                if (numbers==null){
                    Toast.makeText(this, "网络故障,请重新载入", Toast.LENGTH_SHORT).show();
                    request();
                    return;
                }
                gametime=System.currentTimeMillis();
                if (count==numbers.get(flag)){
//                    requestImage(flag);
                    Picasso.with(StoryModelActivity.this).load("http://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@2o.jpg").into(iv_net);
                    flag++;
                }
                if (flag==numbers.size()){
                    gametime=System.currentTimeMillis()-gametime;
//                    格式化时间值
                    addrecord();
                    Toast.makeText(this, "故事结束", Toast.LENGTH_SHORT).show();
                }
                tv_number.setText(++count+"");
                tv_number.setAnimation(animation);
                break;
        }
    }

    private void addrecord() {
        OkGo.post(UrlCollect.addRecord)//
                .tag(this)//
                .params("userId", MyApplication.USERID)
                .params("time", gametime+"")
                .params("shakeNum", count+"")
                .params("type", "0")
                .params("roomId", "0")
                .params("status", "0")//0（手动）1（脚动）
                .params("mode", "2")//1（挑战）2（故事）3（pk）4（休闲）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }

    private void requestImage(final int flag) {
        OkGo.post(UrlCollect.getStoryModeSJ)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jo=new JSONObject(s);
                            JSONArray data = jo.getJSONArray("data");
                            JSONObject jo2 = (JSONObject) data.get(flag);
                            String img = jo2.getString("img");
                            Picasso.with(StoryModelActivity.this).load(UrlCollect.baseIamgeUrl+File.separator+img).into(iv_net);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gametime=System.currentTimeMillis()-gametime;
        addrecord();
    }
}

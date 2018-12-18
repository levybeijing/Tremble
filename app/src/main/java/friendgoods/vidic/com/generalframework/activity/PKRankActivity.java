package friendgoods.vidic.com.generalframework.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import java.util.List;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.bean.PKRecordBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.customview.CirImageView;
import okhttp3.Call;
import okhttp3.Response;

public class PKRankActivity extends BaseActivity {
    private int roomId;
    private ImageView char_one;
    private ImageView char_two;
    private ImageView char_three;
    private CirImageView icon_one;
    private CirImageView icon_two;
    private CirImageView icon_three;
    private TextView name_one;
    private TextView name_two;
    private TextView name_three;
    private int degree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkrank);
//
        roomId = getIntent().getIntExtra("roomId",0);
        degree = getIntent().getIntExtra("degree",1);
//        Log.e("================", "roomId: "+roomId);
//        Log.e("================", "degree: "+degree);

//加载背景图片
        ImageView iv_bg = findViewById(R.id.iv_bg_pkrank);
        Glide.with(this).asGif().load(R.drawable.fireworks).into(iv_bg);
//退出按键
        ImageView iv_exit = findViewById(R.id.iv_exit_pkrank);
        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PKRankActivity.this);
                //    设置Title的图标
                builder.setTitle("游戏结束");
                builder.setMessage("再来一局?");
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(PKRankActivity.this,PKModelActivity.class);
                        intent.putExtra("again",true);
                        intent.putExtra("exit",false);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("退出游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(PKRankActivity.this,PKModelActivity.class);
                        intent.putExtra("again",true);
                        intent.putExtra("exit",true);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });
//请求排名
        initView();

        requestRank();
    }

    private void initView() {
        char_one = findViewById(R.id.char_one_pkrank);
        char_two = findViewById(R.id.char_two_pkrank);
        char_three = findViewById(R.id.char_three_pkrank);
        icon_one = findViewById(R.id.icon_one_pkrank);
        icon_two = findViewById(R.id.icon_two_pkrank);
        icon_three = findViewById(R.id.icon_three_pkrank);
        name_one = findViewById(R.id.name_one_pkrank);
        name_two = findViewById(R.id.name_two_pkrank);
        name_three = findViewById(R.id.name_three_pkrank);
    }

    private void requestRank() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OkGo.post(UrlCollect.getUserPKRanking)//
                .tag(this)//
                .params("roomId", roomId)
                .params("degree", degree)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("================", "排名数据: "+s);
                        PKRecordBean pkRecordBean = new Gson().fromJson(s, PKRecordBean.class);
                        List<PKRecordBean.DataBean> data = pkRecordBean.getData();
                        if (data==null||data.size()==0){
                            return;
                        }
                        name_one.setText(data.get(0).getShakeNum()+"");
                        Picasso.with(PKRankActivity.this).load(data.get(0).getPhoto()).into(icon_one);
                        Picasso.with(PKRankActivity.this).load(UrlCollect.baseIamgeUrl+data.get(0).getLogo()).into(char_one);
                        if (data.size()<=1){
                            name_three.setVisibility(View.INVISIBLE);
                            name_two.setVisibility(View.INVISIBLE);
                            return;
                        }
                        name_two.setText(data.get(1).getShakeNum()+"");
                        Picasso.with(PKRankActivity.this).load(data.get(1).getPhoto()).into(icon_two);
                        Picasso.with(PKRankActivity.this).load(UrlCollect.baseIamgeUrl+data.get(1).getLogo()).into(char_two);
                        if (data.size()<=2){
                            name_three.setVisibility(View.INVISIBLE);
                            return;
                        }
                        name_three.setText(data.get(2).getShakeNum()+"");
                        Picasso.with(PKRankActivity.this).load(data.get(2).getPhoto()).into(icon_three);
                        Picasso.with(PKRankActivity.this).load(UrlCollect.baseIamgeUrl+data.get(2).getLogo()).into(char_three);
                    }
                });
    }
}

package friendgoods.vidic.com.generalframework.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import okhttp3.Call;
import okhttp3.Response;

public class PKRankActivity extends AppCompatActivity {

    private String roomId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkrank);
//
        roomId = getIntent().getStringExtra("roomId");
//加载背景图片
        ImageView iv_bg = findViewById(R.id.iv_bg_pkrank);
        Glide.with(this).asGif().load(R.drawable.fireworks).into(iv_bg);
//退出按键
        ImageView iv_exit = findViewById(R.id.iv_exit_pkrank);
        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//请求排名
        requestRank();
    }

    private void requestRank() {
        OkGo.post(UrlCollect.getUserPKRanking)//
                .tag(this)//
                .params("roomId", roomId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("================", "排名: "+s);
//                        MyRecordBean recordBean = new Gson().fromJson(s, MyRecordBean.class);
                    }
                });
    }
}

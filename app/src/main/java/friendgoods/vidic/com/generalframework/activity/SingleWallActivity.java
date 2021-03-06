package friendgoods.vidic.com.generalframework.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.bean.SingleWallBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.customview.CirImageView;
import okhttp3.Call;
import okhttp3.Response;

public class SingleWallActivity extends BaseActivity {

    private CirImageView icon;
    private TextView name;
    private ImageView container;
    private int wallId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlewall);

        wallId = getIntent().getIntExtra("wallId", 0);

        initView();

        request();
    }

    private void request() {
        OkGo.post(UrlCollect.getSPresentsWallOne)//
                .tag(this)//
                .params("presentswallId", wallId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(SingleWallActivity.this,s);

                        SingleWallBean singleWallBean = new Gson().fromJson(s, SingleWallBean.class);
                        name.setText(singleWallBean.getData().getName());
                        Picasso.with(SingleWallActivity.this).load(singleWallBean.getData().getPhoto()).into(icon);
                        String url = singleWallBean.getData().getUrl();
                        if (url!=null)
                            Picasso.with(SingleWallActivity.this).load(UrlCollect.baseIamgeUrl+url).into(container);
                    }
                });
    }

    private void initView() {
        icon = findViewById(R.id.iv_icon_singlewall);
        name = findViewById(R.id.tv_name_singlewall);
        container = findViewById(R.id.rl_container_singlewall);
    }

}

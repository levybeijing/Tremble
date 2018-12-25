package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.CustomDialogBottom;
import friendgoods.vidic.com.generalframework.bean.DetailGoodsBean;
import okhttp3.Call;
import okhttp3.Response;

public class DetailGoodsActivity extends BaseActivity {

    private String goodsId;
    private ImageView iv_top;
    private TextView name;
    private TextView price;
    private TextView fareprice;
    private CustomDialogBottom dialogBottom;
    private String pricedialog;
    private DetailGoodsBean.DataBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailgoods);

        Intent intent = getIntent();
        //
        goodsId = intent.getStringExtra("goodsId");
        initView();
        if (this.goodsId != null)
            request();
    }

    private void request() {
        OkGo.post(UrlCollect.goodsDetail)//
                .tag(this)//
                .params("goodsId", goodsId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //暂时没有数据
                        TokenCheck.toLogin(DetailGoodsActivity.this, s);

                        DetailGoodsBean bean = new Gson().fromJson(s, DetailGoodsBean.class);
                        data = bean.getData();
                        Picasso.with(DetailGoodsActivity.this).load(UrlCollect.baseIamgeUrl + data.getPhoto()).into(iv_top);
//                      图片加载
                        name.setText(data.getName());
                        pricedialog = "￥" + data.getMoney();
                        price.setText(pricedialog);
                    }
                });

    }

    private void initView() {

        findViewById(R.id.iv_back_detailgoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_top = findViewById(R.id.iv_top_detailgoods);

        name = findViewById(R.id.tv_name_detailgoods);
        price = findViewById(R.id.tv_price_detailgoods);
        fareprice = findViewById(R.id.tv_price_detailgoods);

        ImageView iv_buy = findViewById(R.id.iv_buy_detailgoods);
        iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBottom = new CustomDialogBottom(DetailGoodsActivity.this, data);
                dialogBottom.showAtLocation(DetailGoodsActivity.this.findViewById(R.id.root_detailgoods),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        dialogBottom.dismiss();
    }
}

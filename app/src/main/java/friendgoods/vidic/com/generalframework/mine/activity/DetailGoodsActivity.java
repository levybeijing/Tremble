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
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.CustomDialogBottom;
import friendgoods.vidic.com.generalframework.mine.bean.DetailGoodsBean;
import okhttp3.Call;
import okhttp3.Response;

public class DetailGoodsActivity extends AppCompatActivity {

    private String goodsId;
    private ImageView iv_top;
    private ImageView iv_bottom;
    private ImageView share;
    private TextView name;
    private TextView price;
    private TextView fareprice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailgoods);

        Intent intent = getIntent();
        goodsId = intent.getStringExtra("goodsId");

        initView();

        request();
    }

    private void request() {
        OkGo.post(UrlCollect.goodsDetail)//
                .tag(this)//
                .params("goodsId",goodsId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //暂时没有数据
                        DetailGoodsBean bean = new Gson().fromJson(s, DetailGoodsBean.class);
                        Picasso.with(DetailGoodsActivity.this).load(bean.getData().getPhoto1()).into(iv_top);
                        Picasso.with(DetailGoodsActivity.this).load(bean.getData().getPhoto2()).into(iv_bottom);
                        name.setText(bean.getData().getName());
                        price.setText(bean.getData().getMoney()+"");
                        //积分
                        int jifen = bean.getData().getIntegral();
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

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
        iv_bottom = findViewById(R.id.iv_back_detailgoods);

        name = findViewById(R.id.tv_name_detailgoods);
        price = findViewById(R.id.tv_price_detailgoods);
        fareprice = findViewById(R.id.tv_price_detailgoods);

        share = findViewById(R.id.iv_share_detailgoods);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击分享
            }
        });
        ImageView iv_buy = findViewById(R.id.iv_buy_detailgoods);
        iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomDialogBottom(DetailGoodsActivity.this).showAtLocation(DetailGoodsActivity.this.findViewById(R.id.root_detailgoods), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }
}

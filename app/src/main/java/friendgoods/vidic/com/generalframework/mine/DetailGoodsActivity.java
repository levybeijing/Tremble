package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import friendgoods.vidic.com.generalframework.R;

public class DetailGoodsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailgoods);
        initView();
    }

    private void initView() {
//        getIntent();//获取内容

        ImageView iv_back = findViewById(R.id.iv_back_detailgoods);
        ImageView iv_top = findViewById(R.id.iv_top_detailgoods);
        ImageView iv_bottom = findViewById(R.id.iv_back_detailgoods);

        ImageView share = findViewById(R.id.iv_share_detailgoods);
        TextView name = findViewById(R.id.tv_name_detailgoods);
        TextView price = findViewById(R.id.tv_price_detailgoods);
        TextView fareprice = findViewById(R.id.tv_price_detailgoods);

        ImageView iv_buy = findViewById(R.id.iv_buy_detailgoods);

        iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomDialogBottom(DetailGoodsActivity.this).showAtLocation(DetailGoodsActivity.this.findViewById(R.id.root_detailgoods), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }
}

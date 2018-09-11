package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import friendgoods.vidic.com.generalframework.R;

public class DetailGoodsActivity extends Activity {
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

        ImageView iv_buy = findViewById(R.id.iv_but_detailgoods);

        iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

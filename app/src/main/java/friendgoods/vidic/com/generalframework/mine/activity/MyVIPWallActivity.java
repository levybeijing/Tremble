package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import friendgoods.vidic.com.generalframework.R;

public class MyVIPWallActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygiftswall);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_mygiftwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout rl = findViewById(R.id.container_mygiftwall);
        //
        TextView date = findViewById(R.id.tv_date_mygiftwall);
        TextView energy = findViewById(R.id.tv_energy_mygiftwall);
        //
        RecyclerView rv = findViewById(R.id.rv_mygiftwall);

    }
}

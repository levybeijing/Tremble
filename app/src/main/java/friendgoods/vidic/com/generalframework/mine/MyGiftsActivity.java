package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import friendgoods.vidic.com.generalframework.R;

public class MyGiftsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygifts);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_mygifts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView rv = findViewById(R.id.rv_mygifts);

        GridLayoutManager manager=new GridLayoutManager(this,4);
        rv.setLayoutManager(manager);

//        rv.setAdapter();
    }
}

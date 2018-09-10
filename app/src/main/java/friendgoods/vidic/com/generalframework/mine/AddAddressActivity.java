package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import friendgoods.vidic.com.generalframework.R;

public class AddAddressActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        initView();

    }

    private void initView() {
        findViewById(R.id.iv_top_addaddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}

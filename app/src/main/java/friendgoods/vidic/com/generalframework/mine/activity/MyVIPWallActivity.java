package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import friendgoods.vidic.com.generalframework.R;

public class MyVIPWallActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygiftswall);

        initView();
    }

    private void initView() {
        
    }
}

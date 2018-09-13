package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import friendgoods.vidic.com.generalframework.R;

public class MyRecordActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
    }

    private void initView() {



        TextView tv_name = findViewById(R.id.tv_username);
        Typeface font = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
        }
        tv_name.setTypeface(font);
    }
}

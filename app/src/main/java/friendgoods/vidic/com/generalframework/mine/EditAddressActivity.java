package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import friendgoods.vidic.com.generalframework.R;

public class EditAddressActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddress);
        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_top_editaddress);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EditText name = findViewById(R.id.edit_name_editaddress);
        EditText phone = findViewById(R.id.edit_phone_editaddress);
        EditText district = findViewById(R.id.edit_district_editaddress);
        EditText detail = findViewById(R.id.edit_detailaddress_editaddress);

        ImageView delete = findViewById(R.id.iv_delete_editaddress);
        ImageView save = findViewById(R.id.iv_save_editaddress);

    }
}

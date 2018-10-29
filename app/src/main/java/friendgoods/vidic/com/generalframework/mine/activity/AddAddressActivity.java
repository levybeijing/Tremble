package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import okhttp3.Call;
import okhttp3.Response;

public class AddAddressActivity extends Activity implements View.OnClickListener {

    private EditText name;
    private EditText phone;
    private EditText dis;
    private EditText detail;
    private Switch swi;
    private int isDefault=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);

        initView();

    }

    private void initView() {
        findViewById(R.id.iv_top_addaddress).setOnClickListener(this);
        findViewById(R.id.iv_save_addaddress).setOnClickListener(this);

        name = findViewById(R.id.edit_name_addaddress);
        phone = findViewById(R.id.edit_phone_addaddress);
        dis = findViewById(R.id.edit_district_addaddress);
        detail = findViewById(R.id.edit_detailaddress_addaddress);
        swi = findViewById(R.id.switch_default_addaddress);

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isDefault=1;
                }else{
                    isDefault=0;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_top_addaddress:
                finish();
                break;
            case R.id.iv_save_addaddress:
                String consigee = name.getText().toString().trim();
                if (consigee==null|| consigee.length()==0){
                    Toast.makeText(this, "收件人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                String mobile = phone.getText().toString().trim();
                if (mobile ==null|| mobile.length()==0){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String site = dis.getText().toString().trim();
                String de = detail.getText().toString().trim();
                if (de==null||site ==null|| de.length()==0||site.length()==0){
                    Toast.makeText(this, "地址信息有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestAdd(consigee,mobile,site+de);
                break;
        }
    }

    private void requestAdd(String consigee,String mobile,String district){
        OkGo.post(UrlCollect.addAddress)
                .tag(this)
                .params("site",district)
                .params("consignee",consigee)
                .params("mobile",mobile)
                .params("status",isDefault+"")
                .params("userId", MyApplication.USERID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Toast.makeText(AddAddressActivity.this, s, Toast.LENGTH_SHORT).show();
                        name.setText("");
                        phone.setText("");
                        detail.setText("");
                        dis.setText("");
                        swi.setChecked(false);
                    }

                });
    }
}

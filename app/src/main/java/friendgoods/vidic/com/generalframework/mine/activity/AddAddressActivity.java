package friendgoods.vidic.com.generalframework.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.StoryModelActivity;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import okhttp3.Call;
import okhttp3.Response;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

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
                if (!StringUtil.isPhoneNumber(mobile)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
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
        Log.e("======================", "onSuccess: "+consigee+mobile+district);
        OkGo.post(UrlCollect.addAddress)
                .tag(this)
                .params("site",district)
                .params("consignee",consigee)
                .params("mobile",mobile)
                .params("status",isDefault+"")
                .params("userId", (int)SharedPFUtils.getParam(this,"userId",0))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(AddAddressActivity.this,s);

                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            Toast.makeText(AddAddressActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        name.setText("");
                        phone.setText("");
                        detail.setText("");
                        dis.setText("");
                        swi.setChecked(false);
                        finish();
                    }
                });
    }
}

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

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.AddressesBean;
import okhttp3.Call;
import okhttp3.Response;

public class EditAddressActivity extends Activity implements View.OnClickListener {

    private AddressesBean.DataBean bean;
    private EditText name;
    private EditText phone;
    private EditText district;
    private EditText detail;
    private Switch swi;
    private String addressId;
    private int status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddress);

        Intent intent = getIntent();
        bean = intent.getParcelableExtra("bean");
        status = bean.getStatus();
        initView();
        addressId = bean.getId()+"";
        name.setText(bean.getConsignee());
        phone.setText(bean.getMobile());
        detail.setText(bean.getSite());
        swi.setChecked(status ==1?true:false);
    }

    private void initView() {
        findViewById(R.id.iv_top_editaddress).setOnClickListener(this);
        findViewById(R.id.iv_delete_editaddress).setOnClickListener(this);
        findViewById(R.id.iv_save_editaddress).setOnClickListener(this);

        swi = findViewById(R.id.switch_default_editaddress);
        name = findViewById(R.id.edit_name_editaddress);
        phone = findViewById(R.id.edit_phone_editaddress);
        district = findViewById(R.id.edit_district_editaddress);
        detail = findViewById(R.id.edit_detailaddress_editaddress);

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    requestdefault();
                    status=1;
                }else {
                    status=0;
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_top_editaddress:
                finish();
                break;
            case R.id.iv_save_editaddress:
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
                String site = district.getText().toString().trim();
                if (site ==null|| site.length()==0){
                    Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestChange(consigee,mobile,site);
                break;
            case R.id.iv_delete_editaddress:
                requestDelete();
                break;
        }

    }

    private void requestdefault() {
        OkGo.post(UrlCollect.setForDefaultAddress)
                .tag(this)
                .params("userId", bean.getUserId())
                .params("addressId",bean.getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }

    private void requestChange(String consigee,String mobile,String district) {
        OkGo.post(UrlCollect.modifyAddress)
                .tag(this)
                .params("site",district)
                .params("consignee",consigee)
                .params("mobile",mobile)
                .params("addressId",bean.getId())
                .params("status",status+"")
                .params("userId", bean.getUserId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        name.setText("");
                        phone.setText("");
                        detail.setText("");
                        swi.setChecked(false);
                    }
                });
    }

    private void requestDelete() {
        OkGo.post(UrlCollect.deleteAddress)
                .tag(this)
                .params("addressId",addressId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        name.setText("");
                        phone.setText("");
                        detail.setText("");
                        swi.setChecked(false);
                    }

                });
    }


}

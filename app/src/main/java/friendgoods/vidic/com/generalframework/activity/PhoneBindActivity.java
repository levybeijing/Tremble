package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import okhttp3.Call;
import okhttp3.Response;

public class PhoneBindActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_code;
    private TextView btn_code;
    private TimeCount time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebind);
        time = new TimeCount(60000, 1000);
        initView();
    }



    private void initView() {
        findViewById(R.id.iv_back_phonebind).setOnClickListener(this);
        et_phone = findViewById(R.id.et_phone_phonebind);
        et_code = findViewById(R.id.et_code_phonebind);

        btn_code = findViewById(R.id.tv_code_phonebind);
        btn_code.setOnClickListener(this);
        findViewById(R.id.tv_bind_phonebind).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_phonebind:
                finish();
                break;
            case R.id.tv_code_phonebind:
//                验证码
                String trim = et_phone.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(trim)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                obtioncode(trim);
                time.start();
                break;
            case R.id.tv_bind_phonebind:
//                绑定
                String number = et_phone.getText().toString().trim();
                String code = et_code.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(number)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code==null){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                bind(number,code);
                break;
        }
    }

    private void bind(String number, String code) {
        OkGo.post(UrlCollect.updateMobile)//
                .tag(this)//
                .params("weChat", MyApplication.WX)
                .params("mobile", number)
                .params("smsCode", code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jo=new JSONObject(s);
                            if ("请求成功".equals(jo.getString("message"))){
                                Toast.makeText(PhoneBindActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PhoneBindActivity.this,IntroduceActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(PhoneBindActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void obtioncode(String phone) {
        OkGo.post(UrlCollect.getSms)//
                .tag(this)//
                .params("mobile", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//
                    }
                });
    }
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_code.setBackgroundColor(Color.parseColor("#B6B6D8"));
            btn_code.setClickable(false);
            btn_code.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            btn_code.setText("重新获取验证码");
            btn_code.setClickable(true);
            btn_code.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }
}

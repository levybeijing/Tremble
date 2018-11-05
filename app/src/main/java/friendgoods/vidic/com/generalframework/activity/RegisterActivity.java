package friendgoods.vidic.com.generalframework.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import friendgoods.vidic.com.generalframework._idle.LoginPWDActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import friendgoods.vidic.com.generalframework.wxapi.WXEntryActivity;
import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_code;
//    private EditText et_pwd;
    private TextView btn_code;
    private TimeCount time;

//    private SharedPreferences.Editor edit;
//    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();

        time = new TimeCount(60000, 1000);

        initView();
    }

    private void initView() {
        et_phone = findViewById(R.id.et_phone_register);
        et_code = findViewById(R.id.et_code_register);
//        et_pwd = findViewById(R.id.et_pwd_register);

        btn_code = findViewById(R.id.tv_obtioncode_register);
        btn_code.setOnClickListener(this);

        findViewById(R.id.tv_regi_register).setOnClickListener(this);
        findViewById(R.id.tv_gotologin_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            获取验证码
            case R.id.tv_obtioncode_register:
                String trim = et_phone.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(trim)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                obtioncode(trim);
//                60秒倒计时
                time.start();
                break;
            case R.id.tv_gotologin_register:
                startActivity(new Intent(RegisterActivity.this,LoginCodeActivity.class));
                finish();
                break;
//                注册
            case R.id.tv_regi_register:
                String number = et_phone.getText().toString().trim();
                String code = et_code.getText().toString().trim();
//                String pwd = et_pwd.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(number)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code==null){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                register(number,code);
//                MyApplication.PHONE=number;
//                MyApplication.CODE=code;
//                register(number,code);
//                Intent intent = new Intent(RegisterActivity.this, WXBindActivity.class);
//                startActivity(intent);
                break;
        }
    }

    private void obtioncode(String phone) {
        OkGo.post(UrlCollect.getSms)//
                .tag(this)//
                .params("mobile", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }
                });
    }


    private void register(String phone,String code) {
        OkGo.post(UrlCollect.register)//
                .tag(this)//
                .params("mobile",phone)
                .params("smsCode", code)
                .params("type", "3")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        成功则提示 考虑自动登录
                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if ("请求成功".equals(message)){
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                                SharedPFUtils.setParam(RegisterActivity.this,"bindwx",true);
                                SharedPFUtils.setParam(RegisterActivity.this,"bindphone",true);
                                startActivity(new Intent(RegisterActivity.this,WXBindActivity.class));
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    class TimeCount extends CountDownTimer {

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

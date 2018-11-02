package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.bean.LoginBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.musicplay.MusicService;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class LoginCodeActivity extends Activity implements View.OnClickListener {
    private EditText et_phone;
    private EditText et_code;
    private TextView btn_code;
    private TimeCount time;
//    private MusicService serv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincode);
        time = new TimeCount(60000, 1000);

        initView();
    }

    private void initView() {
        et_phone = findViewById(R.id.et_phone_logincode);
        et_code = findViewById(R.id.et_code_logincode);

        btn_code = findViewById(R.id.tv_code_logincode);
        btn_code.setOnClickListener(this);
        findViewById(R.id.tv_gotopwd_logincode).setOnClickListener(this);
        findViewById(R.id.tv_gotoregi_logincode).setOnClickListener(this);
        findViewById(R.id.tv_login_logincode).setOnClickListener(this);
        findViewById(R.id.iv_weixin_logincode).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_code_logincode:
                String trim = et_phone.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(trim)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                obtioncode(trim);
                time.start();
                break;
            case R.id.tv_gotopwd_logincode:
                startActivity(new Intent(LoginCodeActivity.this,LoginPWDActivity.class));
                break;
            case R.id.tv_gotoregi_logincode:
                startActivity(new Intent(LoginCodeActivity.this,RegisterActivity.class));
                break;
            case R.id.tv_login_logincode:
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
                login(number,code);
                break;
            case R.id.iv_weixin_logincode:
                startActivity(new Intent(LoginCodeActivity.this,WXBindActivity.class));
                break;
        }
    }

    private void login(String phone,String code) {
        OkGo.post(UrlCollect.smsLogen)//
                .tag(this)//
                .params("mobile", phone)
                .params("smsCode", code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        记录用户信息
                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if ("请求成功".equals(message)){
//                                Log.e("*************", "onSuccess: "+s);
                                LoginBean bean = new Gson().fromJson(s, LoginBean.class);
                                SharedPFUtils.setParam(LoginCodeActivity.this,"shake",bean.getData().getShake()==1?true:false);
                                SharedPFUtils.setParam(LoginCodeActivity.this,"voice",bean.getData().getVoice()==1?true:false);
                                SharedPFUtils.setParam(LoginCodeActivity.this,"password",bean.getData().getPassword());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"spNum",bean.getData().getSpNum());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"signDays",bean.getData().getSignDays());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"integral",bean.getData().getIntegral());//
                                SharedPFUtils.setParam(LoginCodeActivity.this,"userId",bean.getData().getId()+"");
                                SharedPFUtils.setParam(LoginCodeActivity.this,"status",bean.getData().getStatus());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"is_use",bean.getData().getIs_use());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"createTime",bean.getData().getCreateTime());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"mobile",bean.getData().getMobile());
                                SharedPFUtils.setParam(LoginCodeActivity.this,"bindphone",true);
                                if (!(boolean)SharedPFUtils.getParam(LoginCodeActivity.this,"bindwx",false)){
                                    startActivity(new Intent(LoginCodeActivity.this,WXBindActivity.class));
                                } else if (SharedPFUtils.getParam(LoginCodeActivity.this,"sex", 0)==0) {
                                    startActivity(new Intent(LoginCodeActivity.this,IntroduceActivity.class));
                                }else{
                                    startActivity(new Intent(LoginCodeActivity.this,MainActivity.class));
                                }
                                finish();
                                SharedPFUtils.setParam(LoginCodeActivity.this,"bindphone",true);
                            }else{
                                Toast.makeText(LoginCodeActivity.this, "请先注册", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        this.unbindService(conn);
        MusicService.getInstance().onDestroy();
    }
}
package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class LoginPWDActivity extends Activity implements View.OnClickListener {
    private EditText et_phone;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpwd);

        initView();
    }

    private void initView() {
        et_phone = findViewById(R.id.et_phone_loginpwd);
        et_pwd = findViewById(R.id.et_pwd_loginpwd);

        findViewById(R.id.tv_gotocode_loginpwd).setOnClickListener(this);
        findViewById(R.id.tv_gotoregi_loginpwd).setOnClickListener(this);
        findViewById(R.id.tv_login_loginpwd).setOnClickListener(this);
        findViewById(R.id.iv_weixin_loginpwd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            获取验证码
            case R.id.tv_gotocode_loginpwd:
                startActivity(new Intent(LoginPWDActivity.this,LoginCodeActivity.class));
                break;
            case R.id.tv_gotoregi_loginpwd:
                startActivity(new Intent(LoginPWDActivity.this,RegisterActivity.class));
                break;
//                注册
            case R.id.tv_login_loginpwd:
                String number = et_phone.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(number)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!StringUtil.isPwdOk(pwd)){
                    Toast.makeText(this, "密码不符合要求", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(number,pwd);
                break;
            case R.id.iv_weixin_loginpwd:
                startActivity(new Intent(LoginPWDActivity.this,WXBindActivity.class));
                break;
        }
    }

    private void login(String phone,String pwd) {
        OkGo.post(UrlCollect.logen)//
                .tag(this)//
                .params("mobile", phone)
                .params("password", pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                       成功则记录用户信息  并跳转
                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if ("请求成功".equals(message)){
                                LoginBean bean = new Gson().fromJson(s, LoginBean.class);
                                SharedPFUtils.setParam(LoginPWDActivity.this,"shake",bean.getData().getShake()==1?true:false);
                                SharedPFUtils.setParam(LoginPWDActivity.this,"voice",bean.getData().getVoice()==1?true:false);
                                SharedPFUtils.setParam(LoginPWDActivity.this,"password",bean.getData().getPassword());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"spNum",bean.getData().getSpNum());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"signDays",bean.getData().getSignDays());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"integral",bean.getData().getIntegral());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"userId",bean.getData().getId()+"");
                                MyApplication.USERID=bean.getData().getId()+"";
                                SharedPFUtils.setParam(LoginPWDActivity.this,"status",bean.getData().getStatus());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"is_use",bean.getData().getIs_use());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"createTime",bean.getData().getCreateTime());
                                SharedPFUtils.setParam(LoginPWDActivity.this,"mobile",bean.getData().getMobile());
                                if (!(boolean)SharedPFUtils.getParam(LoginPWDActivity.this,"bindwx",false)){
                                    startActivity(new Intent(LoginPWDActivity.this,WXBindActivity.class));
                                } else if (SharedPFUtils.getParam(LoginPWDActivity.this,"sex", 0)==0) {
                                    startActivity(new Intent(LoginPWDActivity.this,IntroduceActivity.class));
                                }else{
                                    startActivity(new Intent(LoginPWDActivity.this,MainActivity.class));
                                }
                                finish();
                                SharedPFUtils.setParam(LoginPWDActivity.this,"bindphone",true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
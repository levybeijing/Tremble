package friendgoods.vidic.com.generalframework.activity;

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
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.bean.LoginBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.util.StringUtil;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class LoginCodeActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_phone;
    private EditText et_code;
    private TextView btn_code;
    private TimeCount time;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincode);
        time = new TimeCount(60000, 1000);
        api = WXAPIFactory.createWXAPI(this,WXAppID,true);
        api.registerApp(WXAppID);
        initView();
    }

    private void initView() {
        et_phone = findViewById(R.id.et_phone_logincode);
        et_code = findViewById(R.id.et_code_logincode);

        btn_code = findViewById(R.id.tv_code_logincode);
        btn_code.setOnClickListener(this);
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
                if (api==null||!api.isWXAppInstalled()){
                    Toast.makeText(this, "请先安装微信", Toast.LENGTH_SHORT).show();
                    break;
                }
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "login";
                api.sendReq(req);
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
                        Log.e("===========login", ""+s);
                        LoginBean bean = new Gson().fromJson(s, LoginBean.class);
                        if (!bean.getMessage().equals("请求成功")){
                            Toast.makeText(LoginCodeActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SharedPFUtils.setParam(LoginCodeActivity.this,"phone",bean.getData().getMobile());
                        SharedPFUtils.setParam(LoginCodeActivity.this,"voice",bean.getData().getVoice()==1?true:false);
                        SharedPFUtils.setParam(LoginCodeActivity.this,"integral",bean.getData().getIntegral());//
                        SharedPFUtils.setParam(LoginCodeActivity.this,"userId",bean.getData().getId());
//                            设置全局请求头
                        SharedPFUtils.setParam(LoginCodeActivity.this,"token",bean.getData().getToken());
                        HttpHeaders headers = new HttpHeaders();
                        headers.put("token",bean.getData().getToken());
                        OkGo.getInstance().addCommonHeaders(headers);
//
                        if (bean.getData().getWeChatA()==null){
                            startActivity(new Intent(LoginCodeActivity.this,WXBindActivity.class));
                        } else {
                            SharedPFUtils.setParam(LoginCodeActivity.this,"wx",bean.getData().getWeChatA());
                            SharedPFUtils.setParam(LoginCodeActivity.this,"name",bean.getData().getName());
                            SharedPFUtils.setParam(LoginCodeActivity.this,"icon",bean.getData().getPhoto());
                            SharedPFUtils.setParam(LoginCodeActivity.this, "loginstatus", true);
                            SharedPFUtils.setParam(LoginCodeActivity.this, "logo", bean.getData().getLogo());

                            String logo = bean.getData().getLogo();
                            if (logo !=null) {
                                switch (logo) {
                                    case "man1.png":
                                        SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 11);
                                        break;
                                    case "man2.png":
                                        SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 12);
                                        break;
                                    case "woman1.png":
                                        SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 21);
                                        break;
                                    case "woman2.png":
                                        SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 22);
                                        break;
                                }
                                startActivity(new Intent(LoginCodeActivity.this,MainActivity.class));
                                finish();
                            }else{
                                startActivity(new Intent(LoginCodeActivity.this,IntroduceActivity.class));
                                finish();
                            }
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
//        stopMusic();
    }
}
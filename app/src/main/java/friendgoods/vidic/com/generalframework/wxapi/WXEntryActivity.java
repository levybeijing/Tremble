package friendgoods.vidic.com.generalframework.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.activity.IntroduceActivity;
import friendgoods.vidic.com.generalframework.activity.LoginCodeActivity;
import friendgoods.vidic.com.generalframework.activity.MainActivity;
import friendgoods.vidic.com.generalframework.activity.PhoneBindActivity;
import friendgoods.vidic.com.generalframework.activity.RegisterActivity;
import friendgoods.vidic.com.generalframework.activity.SpleashActivity;
import friendgoods.vidic.com.generalframework.activity.WXBindActivity;
import friendgoods.vidic.com.generalframework.activity.bean.LoginBean;
import friendgoods.vidic.com.generalframework.activity.bean.WXUserInfoBean;
import friendgoods.vidic.com.generalframework.activity.bean.WXAccessTokenBean;
import friendgoods.vidic.com.generalframework.activity.bean.WXRespBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    /**
     * 微信登录相关
     */
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, UrlCollect.WXAppID);
        api.registerApp(WXAppID);
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            if(!result){
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//        登录
        Gson gson = new Gson();
        WXRespBean wxRespBean = gson.fromJson(gson.toJson(baseResp), WXRespBean.class);
//        requestWX(wxRespBean.getCode());
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
//                result ="发送成功";
                if (wxRespBean.getState().equals("bind")){
                    requestLogin(wxRespBean.getCode());
                }else{
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                break;
            default:
//                showMsg(0,result);
                finish();
                break;
        }
    }


    private void requestLogin(String code){
        //登录
        OkGo.get("https://api.weixin.qq.com/sns/oauth2/access_token")
                .tag(this)//
                .params("appid", UrlCollect.WXAppID)
                .params("secret", UrlCollect.WXsecret)
                .params("code",code)
                .params("grant_type", "authorization_code")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        WXAccessTokenBean tokenBean = new Gson().fromJson(s, WXAccessTokenBean.class);
                        getUserInfo(tokenBean);
                    }
                });
    }

    /**
     * 获取个人信息
     * @param accessTokenEntity
     */
    private void getUserInfo(final WXAccessTokenBean accessTokenEntity) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID

        final String openid = accessTokenEntity.getOpenid();
        OkGo.get("https://api.weixin.qq.com/sns/userinfo")//
                .tag(this)//
                .params("access_token", accessTokenEntity.getAccess_token())
                .params("openid", openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        WXUserInfoBean bean = new Gson().fromJson(s, WXUserInfoBean.class);
                        MyApplication.NAME = bean.getNickname();
                        MyApplication.USERICON = bean.getHeadimgurl();
                        MyApplication.WX=openid;

                        requestBind(openid);
                    }
                });
    }

    private void requestBind(final String openid) {
        OkGo.post(UrlCollect.updateWeChat)//
                .tag(this)//
                .params("type", "1")
                .params("userId", MyApplication.USERID)
                .params("name", MyApplication.NAME)
                .params("photo", MyApplication.USERICON)
                .params("weChat", openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jo=new JSONObject(s);
                            if ("请求成功".equals(jo.getString("message"))){
                                Toast.makeText(WXEntryActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                SharedPFUtils.setParam(WXEntryActivity.this,"bindwx",true);
//                                进行网络请求 微信登录
                                requestWX(openid);
//                                startActivity(new Intent(WXEntryActivity.this,LoginCodeActivity.class));
                            }else {
                                Toast.makeText(WXEntryActivity.this, "绑定失败"+s, Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
//    private void login() {
//        OkGo.post(UrlCollect.smsLogen)//
//                .tag(this)//
//                .params("mobile", phone)
//                .params("smsCode", code)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
////                        记录用户信息
//                        try {
//                            JSONObject jo=new JSONObject(s);
//                            String message = jo.getString("message");
//                            if ("请求成功".equals(message)){
//                                Log.e("===============", "onSuccess: "+s);
//                                LoginBean bean = new Gson().fromJson(s, LoginBean.class);
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"shake",bean.getData().getShake()==1?true:false);
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"voice",bean.getData().getVoice()==1?true:false);
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"spNum",bean.getData().getSpNum());
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"signDays",bean.getData().getSignDays());
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"integral",bean.getData().getIntegral());//
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"status",bean.getData().getStatus());
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"is_use",bean.getData().getIs_use());
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"createTime",bean.getData().getCreateTime());
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"mobile",bean.getData().getMobile());
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"bindphone",true);
//                                SharedPFUtils.setParam(LoginCodeActivity.this,"userId",bean.getData().getId()+"");
//                                MyApplication.USERID=bean.getData().getId()+"";
//                                if (bean.getData().getWeChatA()==null){
//                                    startActivity(new Intent(LoginCodeActivity.this,WXBindActivity.class));
//                                } else {
//                                    SharedPFUtils.setParam(LoginCodeActivity.this,"bindwx",true);
//                                    MyApplication.NAME=bean.getData().getName();
//                                    SharedPFUtils.setParam(LoginCodeActivity.this,"name",bean.getData().getName());
//                                    MyApplication.USERICON=bean.getData().getPhoto();
//                                    SharedPFUtils.setParam(LoginCodeActivity.this,"icon",bean.getData().getPhoto());
//                                    String logo = bean.getData().getLogo();
//                                    if (logo !=null) {
//                                        switch (logo) {
//                                            case "man1.png":
//                                                SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 12);
//                                                break;
//                                            case "man2.png":
//                                                SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 11);
//                                                break;
//                                            case "woman1.png":
//                                                SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 22);
//                                                break;
//                                            case "woman2.png":
//                                                SharedPFUtils.setParam(LoginCodeActivity.this, "sex", 21);
//                                                break;
//                                        }
//                                    }
//                                }
//                                if ((int)SharedPFUtils.getParam(LoginCodeActivity.this,"sex",0)==0) {
//                                    startActivity(new Intent(LoginCodeActivity.this,IntroduceActivity.class));
//                                }else{
//                                    startActivity(new Intent(LoginCodeActivity.this,MainActivity.class));
//                                }
//                                finish();
//                            }else{
//                                Toast.makeText(LoginCodeActivity.this, "请先注册", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//    }
//
    private void requestWX(String s) {
        OkGo.post(UrlCollect.appLogin)//
                .tag(this)//
                .params("code", s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jo=new JSONObject(s);
                            if ("请求成功".equals(jo.getString("message"))){
                                SharedPFUtils.setParam(WXEntryActivity.this,"bindwx",true);

                                startActivity(new Intent(WXEntryActivity.this,IntroduceActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}

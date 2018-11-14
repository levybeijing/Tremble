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
import friendgoods.vidic.com.generalframework.activity.bean.WXLoginBean;
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
    private String status;
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
        Gson gson = new Gson();
        WXRespBean wxRespBean = gson.fromJson(gson.toJson(baseResp), WXRespBean.class);
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
//                result ="发送成功";
                requestLogin(wxRespBean.getCode());
                status=wxRespBean.getState();
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
                        switch (status){
                            case "bind":
                                requestBind(openid);
                                break;
                            case "login":
                                requestWX(openid);
                                SharedPFUtils.setParam(WXEntryActivity.this,"wx",openid);
                                break;
                        }
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
                        Log.e("===============", "register: "+s);
                        try {
                            JSONObject jo=new JSONObject(s);
                            if ("请求成功".equals(jo.getString("message"))){
                                Toast.makeText(WXEntryActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(WXEntryActivity.this,LoginCodeActivity.class));
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
    private void requestWX(String s) {
        OkGo.post(UrlCollect.getUserByWeChatA)//
                .tag(this)//
                .params("weChat", s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("===============", "getUserByWeChatA: "+s);
                        WXLoginBean wxLoginBean = new Gson().fromJson(s, WXLoginBean.class);
//                        保存信息
                        SharedPFUtils.setParam(WXEntryActivity.this,"voice",wxLoginBean.getData().getVoice()==1?true:false);
                        SharedPFUtils.setParam(WXEntryActivity.this,"signDays",wxLoginBean.getData().getSignDays());
                        SharedPFUtils.setParam(WXEntryActivity.this,"integral",wxLoginBean.getData().getIntegral());//
                        SharedPFUtils.setParam(WXEntryActivity.this,"mobile",wxLoginBean.getData().getMobile());
                        SharedPFUtils.setParam(WXEntryActivity.this,"userId",wxLoginBean.getData().getId()+"");
                        SharedPFUtils.setParam(WXEntryActivity.this,"name",wxLoginBean.getData().getName());
                        SharedPFUtils.setParam(WXEntryActivity.this,"icon",wxLoginBean.getData().getPhoto());
                        MyApplication.USERID=wxLoginBean.getData().getId();
                        MyApplication.NAME=wxLoginBean.getData().getName();
                        MyApplication.USERICON=wxLoginBean.getData().getPhoto();
                        String mobile = wxLoginBean.getData().getMobile();
                        if (mobile==null){
                            startActivity(new Intent(WXEntryActivity.this,PhoneBindActivity.class));
                            return;
                        }
                        if (wxLoginBean.getData().getLogo()==null){
                            startActivity(new Intent(WXEntryActivity.this,IntroduceActivity.class));
                        }else{
                            startActivity(new Intent(WXEntryActivity.this,MainActivity.class));
                        }
                    }
                });
    }
}

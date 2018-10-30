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
import friendgoods.vidic.com.generalframework.activity.PhoneBindActivity;
import friendgoods.vidic.com.generalframework.activity.SpleashActivity;
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
        Log.e("==============", "baseResp:--A"+new Gson().toJson(baseResp));
        Gson gson = new Gson();
        WXRespBean wxRespBean = gson.fromJson(gson.toJson(baseResp), WXRespBean.class);
        String result = "";
        requestWX(wxRespBean.getCode());
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result ="发送成功";
                OkGo.get("https://api.weixin.qq.com/sns/oauth2/access_token")
                        .tag(this)//
                        .params("appid", UrlCollect.WXAppID)
                        .params("secret", UrlCollect.WXsecret)
                        .params("code",wxRespBean.getCode())
                        .params("grant_type", "authorization_code")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                WXAccessTokenBean tokenBean = new Gson().fromJson(s, WXAccessTokenBean.class);
                                getUserInfo(tokenBean);
                            }
                        });
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                break;
            default:
                result = "发送返回";
//                showMsg(0,result);
                finish();
                break;
        }
        Toast.makeText(WXEntryActivity.this,result,Toast.LENGTH_LONG).show();
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
                        if ((boolean)SharedPFUtils.getParam(WXEntryActivity.this,"bindphone", false)){
                            requestBind(openid);
                            startActivity(new Intent(WXEntryActivity.this,IntroduceActivity.class));
                        }else{
                            startActivity(new Intent(WXEntryActivity.this,PhoneBindActivity.class));
                        }
                        finish();
                    }
                });
    }

    private void requestBind(String openid) {
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
                                SharedPFUtils.setParam(WXEntryActivity.this,"bindwx",true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

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
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}

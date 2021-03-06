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
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import friendgoods.vidic.com.generalframework.activity.IntroduceActivity;
import friendgoods.vidic.com.generalframework.login.LoginCodeActivity;
import friendgoods.vidic.com.generalframework.activity.MainActivity;
import friendgoods.vidic.com.generalframework.login.PhoneBindActivity;
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
        Log.e("===============", "进入微信反馈页面: ");
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            Log.e("===============", "WXEntryActivity: "+result);
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
        Log.e("===============", "onReq: "+baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("===============", "wxRespBean: "+baseResp);
        WXRespBean wxRespBean = new Gson().fromJson(new Gson().toJson(baseResp), WXRespBean.class);
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.e("===============", "wxRespBean.getState(): "+wxRespBean.getState());
                status=wxRespBean.getState();
//                判断对话类型
                if ("login".equals(status)||"bind".equals(status)){
                    requestLogin(wxRespBean.getCode());
                }
                finish();
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
                        Log.e("=====================", "onSuccess: "+tokenBean.getUnionid());
                    }
                });
    }
    /**
     * 获取个人信息
     * @param accessTokenEntity
     */
    private void getUserInfo(final WXAccessTokenBean accessTokenEntity) {

        final String openid = accessTokenEntity.getOpenid();
        OkGo.get("https://api.weixin.qq.com/sns/userinfo")//
                .tag(this)//
                .params("access_token", accessTokenEntity.getAccess_token())
                .params("openid", openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        WXUserInfoBean bean = new Gson().fromJson(s, WXUserInfoBean.class);
                        if (status!=null){
                            SharedPFUtils.setParam(WXEntryActivity.this,"name",bean.getNickname());
                            SharedPFUtils.setParam(WXEntryActivity.this,"icon",bean.getHeadimgurl());
                            SharedPFUtils.setParam(WXEntryActivity.this,"wx",openid);
                            switch (status){//
                                case "bind":
                                    requestBind(openid);
                                    break;
                                case "login":
                                    requestWX(openid);
                                    break;
                            }
                        }
                    }
                });
    }

    private void requestBind(final String openid) {
        int userId =(int) SharedPFUtils.getParam(this, "userId", 0);
        Log.e("============requestBind", userId+"");
        Log.e("============requestBind", openid);

        OkGo.post(UrlCollect.updateWeChat)//
                .tag(this)//
                .params("type", "3")
                .params("userId", userId+"")
                .params("name", (String)SharedPFUtils.getParam(this,"name",""))
                .params("photo", (String)SharedPFUtils.getParam(this,"icon",""))
                .params("weChat", openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===============", "register: "+s);
                        try {
                            JSONObject jo=new JSONObject(s);
                            if ("请求成功".equals(jo.getString("message"))){
                                SharedPFUtils.setParam(WXEntryActivity.this,"wx",openid);
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

    private void requestWX(final String openid) {
        OkGo.post(UrlCollect.getUserByWeChatA)//
                .tag(this)//
                .params("weChat", openid)
                .execute(new StringCallback() {

                    private Intent intent1;

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===============", "getUserByWeChatA: "+openid);
                        Log.e("===============", "getUserByWeChatA: "+s);
                        WXLoginBean wxLoginBean = new Gson().fromJson(s, WXLoginBean.class);
//                        保存信息
                        WXLoginBean.DataBean data = wxLoginBean.getData();

                        if (data!=null){
                            Log.e("===============", "data!=null: "+data);
                            SharedPFUtils.setParam(WXEntryActivity.this,"wx",data.getWeChatA());
                            SharedPFUtils.setParam(WXEntryActivity.this,"voice",data.getVoice()==1?true:false);
                            SharedPFUtils.setParam(WXEntryActivity.this,"integral",data.getIntegral());//
                            SharedPFUtils.setParam(WXEntryActivity.this,"phone",data.getMobile());
                            SharedPFUtils.setParam(WXEntryActivity.this,"userId",data.getId());
//                            名字 不可为空? 特殊字符处理不了
                            String name = data.getName();
                            if (name!=null) {
                                SharedPFUtils.setParam(WXEntryActivity.this,"name", name);
                            }
//                            头像
                            String photo = data.getPhoto();
                            if (photo!=null) {
                                SharedPFUtils.setParam(WXEntryActivity.this,"icon", photo);
                            }
//                            设置全局请求头
                            SharedPFUtils.setParam(WXEntryActivity.this,"token",data.getToken());
                            SharedPFUtils.setParam(WXEntryActivity.this, "loginstatus", true);
                            HttpHeaders headers = new HttpHeaders();
                            headers.put("token",data.getToken());
                            OkGo.getInstance().addCommonHeaders(headers);
//                            判断依据?
                            if (wxLoginBean.getData().getLogo()!=null){
                                switch (wxLoginBean.getData().getLogo()) {
                                    case "man1.png":
                                        SharedPFUtils.setParam(WXEntryActivity.this, "sex", 11);
                                        break;
                                    case "man2.png":
                                        SharedPFUtils.setParam(WXEntryActivity.this, "sex", 12);
                                        break;
                                    case "woman1.png":
                                        SharedPFUtils.setParam(WXEntryActivity.this, "sex", 21);
                                        break;
                                    case "woman2.png":
                                        SharedPFUtils.setParam(WXEntryActivity.this, "sex", 22);
                                        break;
                                }
                                intent1 = new Intent(WXEntryActivity.this, MainActivity.class);
                            }else{
                                intent1 =new Intent(WXEntryActivity.this,IntroduceActivity.class);
                            }
                        }else{
                            intent1 =new Intent(WXEntryActivity.this,PhoneBindActivity.class);
                        }
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        finish();
                    }
                });
    }
}

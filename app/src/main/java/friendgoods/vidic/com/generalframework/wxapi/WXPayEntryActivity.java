package friendgoods.vidic.com.generalframework.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import friendgoods.vidic.com.generalframework.R;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wxpay_entry);
        api = WXAPIFactory.createWXAPI(this, WXAppID);//这里填入自己的微信APPID
        api.handleIntent(getIntent(), this);
        Log.d("==========", "WXPayEntryActivity");
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("==========", "onPayFinish, errCode = " + baseResp.errCode);
        switch (baseResp.errCode){
            case 0:
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                break;
            case -1:
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                break;
            case -2:
                Toast.makeText(this, "用户取消", Toast.LENGTH_LONG).show();
                break;
        }
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}

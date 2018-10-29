package friendgoods.vidic.com.generalframework.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import friendgoods.vidic.com.generalframework.PermissionsUtils;
import friendgoods.vidic.com.generalframework.R;
import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;


public class WXLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private  IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxbind);

        api = WXAPIFactory.createWXAPI(this,WXAppID,true);
        api.registerApp(WXAppID);

        initView();
        String[] permession =new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        PermissionsUtils.getInstance().chekPermissions(this, permession, new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {

            }

            @Override
            public void forbitPermissons() {

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_back_wxbind).setOnClickListener(this);
        findViewById(R.id.iv_bind_wxbind).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_wxbind:
                finish();
                break;
            case R.id.iv_bind_wxbind:
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "forcode";
                api.sendReq(req);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}

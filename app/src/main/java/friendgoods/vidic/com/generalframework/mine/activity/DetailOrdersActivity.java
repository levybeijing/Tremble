package friendgoods.vidic.com.generalframework.mine.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.DetailOrderBean;
import okhttp3.Call;
import okhttp3.Response;

public class DetailOrdersActivity  extends BaseActivity {
    private String orderId;
    private TextView userName;
    private TextView phone;
    private ImageView icon;
    private TextView address;
    private TextView goodsName;
    private TextView number;
    private TextView price,serial;
    private TextView orderTime,payway;
    private TextView paytime,tv_money,tv_fare,tv_zonge;


//    private TextView tv_weixin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailorders);
        orderId = getIntent().getStringExtra("orderId");
        initView();
        request();
    }

    private void request() {
        OkGo.post(UrlCollect.orderDetail)//
                .tag(this)//
                .params("orderId", orderId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("################", "onSuccess: "+s);
                        TokenCheck.toLogin(DetailOrdersActivity.this,s);

                        DetailOrderBean detailOrderBean = new Gson().fromJson(s, DetailOrderBean.class);
                        DetailOrderBean.DataBean data = detailOrderBean.getData();
                        Picasso.with(DetailOrdersActivity.this).load(UrlCollect.baseIamgeUrl+data.getPhoto()).into(icon);
                        userName.setText(data.getConsignee());
                        phone.setText(data.getMobile()+"");
                        address.setText(data.getSite());
                        goodsName.setText(data.getGoodsName());
                        number.setText("X"+data.getNum());
                        price.setText("￥"+data.getMoney());
                        serial.setText("订单编号:   "+data.getOrder_uuid());
                        orderTime.setText("下单时间:   "+data.getCreateTime());

                        tv_money.setText(data.getMoneys());
//                        tv_fare.setText(data.getCreateTime());
                        tv_zonge.setText(data.getMoneys());

                    }

                });
    }

    private void initView() {
        findViewById(R.id.iv_back_detailorders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
        userName = findViewById(R.id.tv_username_orders);
        phone = findViewById(R.id.tv_phone_orders);
        address = findViewById(R.id.tv_address_orders);

        icon = findViewById(R.id.iv_icon_orders);
        goodsName = findViewById(R.id.tv_name_orders);
        number = findViewById(R.id.tv_number_orders);
        price = findViewById(R.id.tv_price_orders);
//
        serial = findViewById(R.id.tv_serial_orders);
        orderTime = findViewById(R.id.tv_time_orders);
        payway = findViewById(R.id.tv_payway_orders);
        paytime = findViewById(R.id.tv_paytime_orders);
//
        tv_money = findViewById(R.id.tv_money_detailorders);
        tv_fare = findViewById(R.id.tv_fare_detailorders);
        tv_zonge = findViewById(R.id.tv_zonge_detailorders);

        ImageView shouhou = findViewById(R.id.btn_shouhou_detailorders);
        shouhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //出现对话框(网络访问获得文本和图片)
                showDialog();
            }
        });
    }
    @SuppressLint("RestrictedApi")
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(R.layout.dialog_shouhou);

        final View layout = inflater.inflate(R.layout.dialog_shouhou, null);//获取自定义布局
        Button button = layout.findViewById(R.id.btn_copy_dialog);
        final TextView tv_weixin = layout.findViewById(R.id.tv_weixin_dailog);
//        ImageView iv_erweima= layout.findViewById(R.id.iv_erweima_dialog);

        //网络访问 设置
//        tv_weixin.setText();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tv_weixin.getText().toString());
                Toast.makeText(getApplication(), "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dlg = builder.create();

        dlg.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
        lp.width = display.getWidth()*11/15;
        lp.height = display.getHeight()*7/13;
        dlg.getWindow().setAttributes(lp);
//
    }
}

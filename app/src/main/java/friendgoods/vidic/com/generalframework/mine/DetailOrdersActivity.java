package friendgoods.vidic.com.generalframework.mine;

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

import friendgoods.vidic.com.generalframework.R;

public class DetailOrdersActivity  extends Activity{

    private TextView tv_weixin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailorders);

        initView();
    }

    private void initView() {
//
        TextView userName = findViewById(R.id.tv_username_orders);
        TextView phone = findViewById(R.id.tv_phone_orders);
//
        ImageView icon = findViewById(R.id.iv_icon_orders);
        TextView goodsName = findViewById(R.id.tv_name_orders);
        TextView number = findViewById(R.id.tv_number_orders);
        TextView price = findViewById(R.id.tv_price_orders);
//
        TextView serial = findViewById(R.id.tv_serial_orders);
        TextView orderTime = findViewById(R.id.tv_time_orders);
        TextView payway = findViewById(R.id.tv_payway_orders);
        TextView paytime = findViewById(R.id.tv_paytime_orders);
//
        Button shouhou = findViewById(R.id.btn_shouhou_detailorders);
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
        tv_weixin = layout.findViewById(R.id.tv_weixin_dailog);
        ImageView iv_erweima= layout.findViewById(R.id.iv_erweima_dialog);

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

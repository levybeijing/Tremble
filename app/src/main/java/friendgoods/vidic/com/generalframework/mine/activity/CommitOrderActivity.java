package friendgoods.vidic.com.generalframework.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.BaseActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.AddressesBean;
import friendgoods.vidic.com.generalframework.bean.DetailGoodsBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class CommitOrderActivity extends BaseActivity implements View.OnClickListener {

    private String number_;
    private double price_;
    private String goodsId_;
    private int integral_;
    private DetailGoodsBean.DataBean bean;
    private boolean haveAddress=false;

    private static final int REQUESTADDRESS=10000;
    private TextView username;
    private TextView phone;
    private TextView address;
    private LinearLayout ll_top;
    private LinearLayout ll_bottom;
    private AddressesBean.DataBean addre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commitorder);

        Intent intent = getIntent();
        bean = intent.getParcelableExtra("bean");
        price_ = bean.getMoney();
        number_ = intent.getStringExtra("number");
        goodsId_ = bean.getGoodsId()+"";
        integral_ = bean.getIntegral();

        initView();
    }

    private void initView() {
        //返回订单界面
        findViewById(R.id.iv_back_commitorder).setOnClickListener(this);
        findViewById(R.id.tv_top_commitorder).setOnClickListener(this);
        //增加地址
        ll_top = findViewById(R.id.ll_commitorder);
        ll_top.setOnClickListener(this);
        //回调显示地址信息
        ll_bottom = findViewById(R.id.ll_address_commitorders);
        ll_bottom.setOnClickListener(this);
        //快递
        findViewById(R.id.tv_kuaidi_commitorder).setOnClickListener(this);
        findViewById(R.id.iv_kuaidi_commitorder).setOnClickListener(this);
        //提交
        findViewById(R.id.iv_commit_commitorder).setOnClickListener(this);
        //界面控件赋值
        ImageView icon = findViewById(R.id.iv_icon_commitorder);
        Picasso.with(this).load(UrlCollect.baseIamgeUrl+bean.getPhoto()).into(icon);
        TextView name = findViewById(R.id.tv_name_commitorder);
        name.setText(bean.getName());
        TextView price = findViewById(R.id.tv_price_commitorder);
        price.setText("￥"+price_);
        TextView number = findViewById(R.id.tv_number_commitorder);
        number.setText("X"+number_);
        TextView zonge = findViewById(R.id.tv_moneys_commitorder);
        zonge.setText(Integer.parseInt(number_)*price_+"");

        username = findViewById(R.id.tv_username_commitorders);
        phone = findViewById(R.id.tv_phone_commitorders);
        address = findViewById(R.id.tv_address_commitorders);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_commitorder:
            case R.id.tv_top_commitorder:
                Intent intent=new Intent(this,MyOrdersActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_commitorder:
            case R.id.ll_address_commitorders:
                Intent intent1=new Intent(this,MyAddressesActivity.class);
                //选择地址 回调界面
                intent1.putExtra("forResult",true);
                startActivityForResult(intent1,REQUESTADDRESS);
                break;
            case R.id.tv_kuaidi_commitorder:
            case R.id.iv_kuaidi_commitorder:
                //应该是选择快递的界面
                break;
            case R.id.iv_commit_commitorder:
                if (haveAddress){
                    request();
                }else {
                    Toast.makeText(this, "地址为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void request() {
        OkGo.post(UrlCollect.addOrder)//
                .tag(this)
                .params("goodsId",goodsId_)
                .params("userId", (int)SharedPFUtils.getParam(this,"userId",0)+"")
                .params("num",number_)
                .params("status","0")//是未付款
                .params("consignee",addre.getConsignee())
                .params("mobile",addre.getMobile())
                .params("site",addre.getSite())
                .params("integral",Integer.parseInt(number_)*integral_+"")
                .params("moneys",Integer.parseInt(number_)*price_+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        haveAddress=false;
                        startActivity(new Intent(CommitOrderActivity.this,MyOrdersActivity.class));
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUESTADDRESS&&data !=null){
            addre= data.getParcelableExtra("bean");
            //显示界面并设置
            ll_top.setVisibility(View.GONE);
            ll_bottom.setVisibility(View.VISIBLE);
            username.setText(addre.getConsignee());
            phone.setText(addre.getMobile());
            address.setText(addre.getSite());
            //
            haveAddress=true;
        }
    }
}

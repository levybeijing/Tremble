package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.MyOrdersActivity;
import friendgoods.vidic.com.generalframework.mine.activity.PayBean;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerOrderId;
import friendgoods.vidic.com.generalframework.bean.OrdersBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class AdapterOrdersCommon extends RecyclerView.Adapter<AdapterOrdersCommon.MyViewHolder> {
    private Context context;
    private List<OrdersBean.DataBean.PageInfoBean.ListBean> list;
    private OnItemClickListenerOrderId clickListenerPosition;

    public AdapterOrdersCommon(Context context_) {
        context=context_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_orders, parent, false);
        return new MyViewHolder(view);
    }

    public void setClickListenerPosition(OnItemClickListenerOrderId clickListenerPosition_){
        clickListenerPosition=clickListenerPosition_;
    }

    public void setData (List<OrdersBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int status = list.get(position).getStatus();

        switch (status){
            case 0:
                holder.tv_status.setText("待付款");
                holder.tv_jifen.setVisibility(View.VISIBLE);
//                holder.tv_jifen.setText(list.get(position).getIntegral()+"");
                holder.btn_1.setImageResource(R.mipmap.cancelorder_myorders_3x);
                holder.btn_2.setImageResource(R.mipmap.pay_myorders_3x);
                break;
            case 1:
                holder.tv_status.setText("待发货");
                holder.btn_1.setVisibility(View.GONE);
                holder.btn_2.setImageResource(R.mipmap.remind_myorders_3x);
                break;
            case 2:
                holder.tv_status.setText("已发货");
                holder.btn_1.setImageResource(R.mipmap.lookfor_myorders_3x);
                holder.btn_2.setImageResource(R.mipmap.confirm_myorders_3x);
                break;
            case 3:
                holder.tv_status.setText("交易成功");
                holder.btn_1.setVisibility(View.GONE);
                holder.btn_2.setImageResource(R.mipmap.service_myorders_3x);
                break;
        }
        //共同属性
        Picasso.with(context).load(UrlCollect.baseIamgeUrl+list.get(position).getPhoto()).into(holder.iv_icon);
        holder.tv_name.setText(list.get(position).getGoodsName());
//        holder.tv_guige.setText(list.get(position).getGoodsId());

        if (status ==0){
            //加载可用积分
            holder.tv_jifen.setText(list.get(position).getIntegral()+"");
            holder.tv_price.setText("+"+list.get(position).getMoney());
        }else{
            holder.tv_price.setText(list.get(position).getMoney()+"");
        }
        holder.tv_number.setText("X"+list.get(position).getNum());
        holder.tv_zongji.setText("共1件商品  合计:¥"+list.get(position).getMoneys()+"(含运费¥0.00)");


        //跳转需要的参数
//        int orderId = list.get(position).getGoodsId();
        View itemView = holder.itemView;

        if (clickListenerPosition != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userId = list.get(position).getId()+"";
                    clickListenerPosition.onItemClick(userId);
                }
            });
        }
        holder.btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                        Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });
        holder.btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                        Log.e("==========parameters", list.get(position).getMoneys());
                        Log.e("==========parameters", list.get(position).getOrder_uuid());
                        Log.e("==========parameters", (String)SharedPFUtils.getParam(context,"wx",""));
                        Log.e("==========parameters", list.get(position).getGoodsName());

                        OkGo.post(UrlCollect.wxPay)//
                                .tag(this)//
                                .params("money",list.get(position).getMoneys())
                                .params("orderUUID",list.get(position).getOrder_uuid())
                                .params("openid","o5XA346r-VTtR6lBaViXVFxzzEE8")
//                                .params("openid",(String) SharedPFUtils.getParam(context,"wx",""))
                                .params("body",list.get(position).getGoodsName())
                                .params("tradetype","JSAPI")
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        //暂时没有数据
                                        Log.e("==========parameters", s);
                                        PayBean payBean = new Gson().fromJson(s, PayBean.class);

                                        PayReq request = new PayReq();
                                        request.appId = WXAppID;//
                                        request.nonceStr= payBean.getData().getNonceStr();
                                        request.partnerId = UrlCollect.mch_id;
                                        request.packageValue = "Sign=WXPay";
                                        request.prepayId= payBean.getData().getPrepay_id();
                                        request.timeStamp= payBean.getData().getTimeStamp();
                                        request.sign=payBean.getData().getPaySign();
                                        MyOrdersActivity.api.sendReq(request);
                                    }
                                });
//                        Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();

                        break;
                    case 3:
                        Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_icon,btn_1,btn_2;
        TextView tv_name,tv_price,tv_status,tv_number,tv_zongji,tv_jifen;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon= view.findViewById(R.id.iv_icon_myorders);

            tv_name= view.findViewById(R.id.tv_name_myorders);
            tv_jifen= view.findViewById(R.id.tv_jifen_myorders);

            tv_price = view.findViewById(R.id.tv_price_myorders);
            tv_status = view.findViewById(R.id.tv_status_myorders);
            tv_number=view.findViewById(R.id.tv_number_myorders);

            tv_zongji=view.findViewById(R.id.tv_zongjie_myorders);

            btn_1=view.findViewById(R.id.iv_left_goods_myorders);
            btn_2=view.findViewById(R.id.iv_right_goods_myorders);

        }
    }
}

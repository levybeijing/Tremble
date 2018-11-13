package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.GiftsMallBean;
import friendgoods.vidic.com.generalframework.mine.activity.DetailGoodsActivity;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class AdapterGiftMall extends RecyclerView.Adapter<AdapterGiftMall.MyViewHolder> {
    private Context context;
    private List<GiftsMallBean.DataBean.PageInfoBean.ListBean> list;
    private IWXAPI api;

    public AdapterGiftMall(Context context_) {
        context=context_;
    }

    public void setData(List<GiftsMallBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gifts_rv_mall, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到订单页面?直接支付?
                Intent intent = new Intent(context, DetailGoodsActivity.class);
                //加入数据
//                intent.putExtra("bean", list.get(position));
//                intent.putExtra("goodsId",list.get(position).getId()+"");

//                context.startActivity(intent);
                api = WXAPIFactory.createWXAPI(context, UrlCollect.WXAppID);
                api.registerApp(WXAppID);
                PayReq request = new PayReq();
                request.appId = WXAppID;
                request.partnerId = "1900000109";
                request.prepayId= "1101000000140415649af9fc314aa427";
                request.packageValue = "Sign=WXPay";
                request.nonceStr= "1101000000140429eb40476f8896f4c9";
                request.timeStamp= "1398746574";
                request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
                api.sendReq(request);
            }
        });
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_energy.setText(list.get(position).getScore()+"");
        holder.tv_jifen.setText(list.get(position).getIntegral()+"");
        String url = list.get(position).getUrl();
        Picasso.with(context).load(UrlCollect.baseIamgeUrl+url).into(holder.iv_icon);

        holder.iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.et_number.setText(Integer.parseInt(holder.et_number.getText().toString())+1+"");
            }
        });
        holder.iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.et_number.setText(Integer.parseInt(holder.et_number.getText().toString())-1+"");
            }
        });
    }
//    private void request(int number) {
//
//    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_buy;
        ImageView iv_icon,iv_plus,iv_reduce;
        TextView tv_name,tv_energy,tv_jifen;
        EditText et_number;

        public MyViewHolder(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name_gifts);
            tv_buy = view.findViewById(R.id.btn_buy_gifts);
            tv_energy = view.findViewById(R.id.tv_energy_gifts);
            tv_jifen = view.findViewById(R.id.tv_doutuinumber_gifts);
            et_number = view.findViewById(R.id.et_number_gifts);

            iv_icon = view.findViewById(R.id.iv_goods_gifts);
            iv_plus = view.findViewById(R.id.iv_plus_gift);
            iv_reduce = view.findViewById(R.id.iv_reduce_gift);
        }
    }
}

package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.bean.OrdersBean;

public class AdapterOrdersCommon extends RecyclerView.Adapter<AdapterOrdersCommon.MyViewHolder> {
    private Context context;
    private List<OrdersBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterOrdersCommon(Context context_, List<OrdersBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_orders, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (list.get(position).getStatus()){
            case 0:
                holder.tv_status.setText("待付款");
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
        Picasso.with(context).load(list.get(position).getPhoto()).into(holder.iv_icon);
        holder.tv_name.setText(list.get(position).getGoodsName());
        holder.tv_guige.setText(list.get(position).getGoodsId());

        holder.tv_price.setText(list.get(position).getMoney()+"");
        holder.tv_number.setText(list.get(position).getNum());
        holder.tv_zongji.setText(list.get(position).getMoney()*list.get(position).getNum()+"");
        //需要的参数
        int goodsId = list.get(position).getGoodsId();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_icon,btn_1,btn_2;
        TextView tv_name,tv_guige,tv_price,tv_status,tv_number,tv_zongji;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon= view.findViewById(R.id.iv_icon_myorders);
            tv_name= view.findViewById(R.id.tv_name_myorders);
            tv_guige = view.findViewById(R.id.tv_guige_myorders);
            tv_price = view.findViewById(R.id.tv_price_myorders);
            tv_status = view.findViewById(R.id.tv_status_myorders);
            tv_number=view.findViewById(R.id.tv_number_myorders);
            tv_zongji=view.findViewById(R.id.tv_zongjie_myorders);
            btn_1=view.findViewById(R.id.iv_left_goods_myorders);
            btn_2=view.findViewById(R.id.iv_right_goods_myorders);

        }
    }
}

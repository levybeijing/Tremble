package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerAddress;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.activity.EditAddressActivity;
import friendgoods.vidic.com.generalframework.bean.AddressesBean;

public class AdapterMyAddresses extends RecyclerView.Adapter {
    private Context context;
    private List<AddressesBean.DataBean> list;
    private OnItemClickListenerAddress onItemClickListener;
    public AdapterMyAddresses(Context context_) {
        context=context_;
    }

    public void setData(List<AddressesBean.DataBean> data){
        list=data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListenerAddress onItemClickListener_){
        onItemClickListener=onItemClickListener_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_myaddress, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //假如是默认地址
        if (position==0){
            ((MyViewHolder)holder).tv_default.setVisibility(View.VISIBLE);
        }
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getConsignee());
        ((MyViewHolder)holder).tv_phone.setText(list.get(position).getMobile());
        ((MyViewHolder)holder).tv_detail.setText(list.get(position).getSite());

        ((MyViewHolder)holder).iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditAddressActivity.class);
                intent.putExtra("bean",list.get(position));
                context.startActivity(intent);
            }
        });
        View itemView = ((MyViewHolder) holder).itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(list.get(position));
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
        ImageView iv_edit;
        TextView tv_name,tv_phone,tv_default,tv_detail;
        public MyViewHolder(View view)
        {
            super(view);
            tv_name= view.findViewById(R.id.tv_name_myaddress);
            tv_phone= view.findViewById(R.id.tv_phone_myaddress);
            tv_default= view.findViewById(R.id.tv_default_myaddress);
            tv_detail = view.findViewById(R.id.tv_detail_myaddress);
            iv_edit = view.findViewById(R.id.iv_edit_myaddress);
        }
    }
}

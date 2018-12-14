package friendgoods.vidic.com.generalframework.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.MainActivity;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.MyGiftsActivity;
import friendgoods.vidic.com.generalframework.bean.MyGiftsBean;

public class AdapterMyGifts extends RecyclerView.Adapter {
    private Activity context;
    private List<MyGiftsBean.DataBean> list;

    public AdapterMyGifts(Activity context_) {
        context=context_;

    }

    public void setData(List<MyGiftsBean.DataBean> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_mygifts, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
        ((MyViewHolder)holder).tv_energy.setText(list.get(position).getScore()+"");
        ((MyViewHolder)holder).tv_price.setText(list.get(position).getNum()+"");
        Picasso.with(context).load(UrlCollect.baseIamgeUrl+list.get(position).getUrl()).into(((MyViewHolder)holder).iv_icon);
//        ((MyViewHolder)holder).gift_good.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("type", 1);
//                intent.setClass(context, MainActivity.class);
//                context.startActivity(intent);
//                context.finish();
//                MainActivity.instance.finish();
//            }
//        });
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
        LinearLayout gift_good;
        ImageView iv_icon;
        TextView tv_name,tv_energy,tv_price;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon = view.findViewById(R.id.iv_icon_mygifts);
            tv_name= view.findViewById(R.id.tv_name_mygifts);
            tv_energy= view.findViewById(R.id.tv_energy_mygifts);
            tv_price = view.findViewById(R.id.tv_price_mygifts);
            gift_good = view.findViewById(R.id.gift_good);
        }
    }
}

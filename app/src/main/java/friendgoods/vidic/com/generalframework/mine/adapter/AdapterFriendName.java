package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerMine;
import friendgoods.vidic.com.generalframework.bean.FansBangBean;

public class AdapterFriendName extends RecyclerView.Adapter<AdapterFriendName.MyViewHolder> {
    private Context context;
    private OnItemClickListenerMine itemClickListener;
    private List<FansBangBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterFriendName(Context context_) {
        context=context_;
    }

    public void setOnItemClickListener(OnItemClickListenerMine itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_top3_friendname, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        switch (position){
            case 0:
                Picasso.with(context).load(list.get(position+1).getPhoto()).into(holder.iv_small);
                Picasso.with(context).load(R.mipmap.second_fans_3x).into(holder.iv_rank);
                holder.tv_name.setText(list.get(position+1).getName());
                holder.tv_energy.setText(list.get(position+1).getScore()+"");
                break;
            case 1:
                holder.iv_small.setVisibility(View.INVISIBLE);
                holder.iv_big.setVisibility(View.VISIBLE);
                Picasso.with(context).load(list.get(position-1).getPhoto()).into(holder.iv_big);
                Picasso.with(context).load(R.mipmap.first_fans_3x).into(holder.iv_rank);
                holder.tv_name.setText(list.get(position-1).getName());
                holder.tv_energy.setText(list.get(position-1).getScore()+"");
                break;
            case 2:
                Picasso.with(context).load(R.mipmap.third_fans_3x).into(holder.iv_rank);
                holder.tv_name.setText(list.get(position).getName());
                holder.tv_energy.setText(list.get(position).getScore()+"");
                Picasso.with(context).load(list.get(position+1).getPhoto()).into(holder.iv_small);
                break;
        }

        //监听事件
        View itemView =holder.itemView;
        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    itemClickListener.onItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    @Override
    public int getItemCount() {
        if (list==null) {
            return 0;
        }
        return 3;
    }

    public void setData(List<FansBangBean.DataBean.PageInfoBean.ListBean> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv_rank,iv_small,iv_big;
        TextView tv_name,tv_energy;
        public MyViewHolder(View view)
        {
            super(view);
            iv_small = view.findViewById(R.id.iv_icon_top3_friendname);
            iv_big= view.findViewById(R.id.iv_iconbig_top3_friendname);
            iv_rank= view.findViewById(R.id.iv_rank_friendname);
            tv_name= view.findViewById(R.id.tv_name_friendname);
            tv_energy = view.findViewById(R.id.tv_energy_friendname);
        }
    }

}

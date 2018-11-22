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

public class AdapterFansBangBottom extends RecyclerView.Adapter<AdapterFansBangBottom.MyViewHolder> {
    private Context context;
    private OnItemClickListenerMine itemClickListener;
    private List<FansBangBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterFansBangBottom(Context context_) {
        context=context_;
    }

    public void setOnItemClickListener(OnItemClickListenerMine itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_bottom_fansbang, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_rank.setText(list.get(position+3).getFansId()+"");
        holder.tv_name.setText(list.get(position+3).getName());
        holder.tv_energy.setText(list.get(position+3).getScore()+"");
        Picasso.with(context).load(list.get(position+3).getPhoto()).into(holder.iv_icon);

        //监听事件
        View itemView =holder.itemView;
        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    itemClickListener.onItemClick(position+3);
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
        if (list==null||list.size()<3) {
            return 0;
        }
        return list.size()-3;
    }

    public void setData(List<FansBangBean.DataBean.PageInfoBean.ListBean> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv_icon;
        TextView tv_rank,tv_name,tv_energy;
        public MyViewHolder(View view)
        {
            super(view);
            tv_rank = view.findViewById(R.id.tv_rank_fansbang);
            iv_icon= view.findViewById(R.id.iv_icon_fansbang);
            tv_name= view.findViewById(R.id.tv_name_fansbang);
            tv_energy = view.findViewById(R.id.tv_energy_fansbang);
        }
    }

}

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
import friendgoods.vidic.com.generalframework.bean.MyFansBean;

public class AdapterMyFans extends RecyclerView.Adapter<AdapterMyFans.MyViewHolder> {
    private Context context;
//    private OnItemClickListenerMine itemClickListener;
    private List<MyFansBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterMyFans(Context context_) {
        context=context_;
    }

    public void setData(List<MyFansBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }
//    public void setOnItemClickListener(OnItemClickListenerMine itemClickListene_){
//        itemClickListener=itemClickListene_;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item1_rv_myfans, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (position){
            case 0:
                Picasso.with(context).load(R.mipmap.first_fans_3x).into(holder.iv_rank);
                break;
            case 1:
                Picasso.with(context).load(R.mipmap.second_fans_3x).into(holder.iv_rank);
                break;
            case 2:
                Picasso.with(context).load(R.mipmap.third_fans_3x).into(holder.iv_rank);
                break;
            default:
                holder.tv_rank.setText(position+1);
                break;
        }
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_value.setText(list.get(position).getScore()+"");
        Picasso.with(context).load(list.get(position).getGiftId()).into(holder.iv_wall);
        Picasso.with(context).load(list.get(position).getPhoto()).into(holder.iv_icon);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //        View itemView = ((RelativeLayout) holder.itemView).getChildAt(0);
//
//        if (itemClickListener != null) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    itemClickListener.onItemClick(position);
//                }
//            });
//        }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv_rank,iv_icon,iv_wall;
        TextView tv_rank,tv_name,tv_value;
        public MyViewHolder(View view)
        {
            super(view);
            iv_rank = view.findViewById(R.id.iv_item1_myfans);
            tv_rank = view.findViewById(R.id.tv_item1_myfans);

            tv_name= view.findViewById(R.id.tv_name_myfans);
            iv_icon= view.findViewById(R.id.iv_icon_myfans);
            iv_wall = view.findViewById(R.id.iv_wall_myfans);
            tv_value = view.findViewById(R.id.tv_value_myfans);
        }
    }

}

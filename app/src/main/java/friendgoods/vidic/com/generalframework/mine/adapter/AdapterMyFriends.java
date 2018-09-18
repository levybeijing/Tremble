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
import friendgoods.vidic.com.generalframework.mine.OnItemClickListener;
import friendgoods.vidic.com.generalframework.mine.bean.MyFansBean;
import friendgoods.vidic.com.generalframework.mine.bean.MyFriendsBean;

public class AdapterMyFriends extends RecyclerView.Adapter<AdapterMyFriends.MyViewHolder> {
    private Context context;
    private OnItemClickListener itemClickListener;
    private List<MyFriendsBean.DataBean.PageInfoBean.ListBean> list;
    public AdapterMyFriends(Context context_, List<MyFriendsBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
    }

//    public void setOnItemClickListener(OnItemClickListener itemClickListene_){
//        itemClickListener=itemClickListene_;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item1_rv_myfriends, parent, false);
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
        holder.tv_name.setText(list.get(position).getNAME());
        holder.tv_time.setText(list.get(position).getTime());
        holder.tv_count.setText(list.get(position).getShakeNum()+"");
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
        return list.size();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv_rank,iv_icon;
        TextView tv_rank,tv_name,tv_count,tv_time;
        public MyViewHolder(View view)
        {
            super(view);
            iv_rank = view.findViewById(R.id.iv_item1_myfriends);
            tv_rank = view.findViewById(R.id.tv_item1_myfriends);

            iv_icon= view.findViewById(R.id.iv_icon_myfriends);

            tv_name= view.findViewById(R.id.tv_name_myfriends);
            tv_time = view.findViewById(R.id.tv_time_myfriends);
            tv_count = view.findViewById(R.id.tv_count_myfriends);
        }
    }

}

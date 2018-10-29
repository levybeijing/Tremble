package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.GiftsMallBean;
import friendgoods.vidic.com.generalframework.bean.WeekRankBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPosition;
import okhttp3.Call;
import okhttp3.Response;

public class AdapterWeekRank extends RecyclerView.Adapter<AdapterWeekRank.MyViewHolder> {
    private Context context;
    private List<WeekRankBean.DataBean.PageInfoBean.ListBean> list;
    private OnItemClickListenerPosition itemClickListener;

    public void setOnItemClickListener(OnItemClickListenerPosition itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    public AdapterWeekRank(Context context_) {
        context=context_;
    }

    public void setData(List<WeekRankBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item1_rv_myfriends, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.iv_rank.setVisibility(View.GONE);
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_time.setText(list.get(position).getTime());
        holder.tv_count.setText(list.get(position).getShakeNum()+"");
        String photo = list.get(position).getPhoto();
        if (photo!=null&&!photo.equals("")) {
            Picasso.with(context).load(photo).into(holder.iv_icon);
        }
//        int userId = list.get(position).getUserId();

        View itemView = holder.itemView;

        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position);
                }
            });
        }
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

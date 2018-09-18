package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.bean.MyRecordBean;

public class AdapterMyRecord  extends RecyclerView.Adapter{
    private List<MyRecordBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterMyRecord(Context context_, List<MyRecordBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
        Toast.makeText(context_, "历史纪录条目数量："+list_.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item0_rv_myfans, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position==0){
            ((MyViewHolder)holder).tv_count.setText("次数");
            ((MyViewHolder)holder).tv_time.setText("时长");
            ((MyViewHolder)holder).tv_rank.setText("总排名");
            ((MyViewHolder)holder).tv_type.setText("类型");
        }
        ((MyViewHolder)holder).tv_count.setText(list.get(position).getShakeNum());
        ((MyViewHolder)holder).tv_time.setText(list.get(position).getTime());
        ((MyViewHolder)holder).tv_rank.setText(list.get(position).getStatus());
        ((MyViewHolder)holder).tv_type.setText(list.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_count,tv_time,tv_rank,tv_type;
        public MyViewHolder(View view)
        {
            super(view);
            tv_count= view.findViewById(R.id.tv_name_myfans);
            tv_time = view.findViewById(R.id.tv_value_myfans);
            tv_rank= view.findViewById(R.id.tv_name_myfans);
            tv_type = view.findViewById(R.id.tv_value_myfans);
        }
    }
}

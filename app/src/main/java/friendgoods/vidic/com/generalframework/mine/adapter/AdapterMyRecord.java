package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.MyRecordBean;

public class AdapterMyRecord  extends RecyclerView.Adapter{
    private List<MyRecordBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterMyRecord(Context context_) {
        context=context_;
    }

    public void setData(List<MyRecordBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_myrecord, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position==0){
//            ((MyViewHolder)holder).tv_count.setText("次数");
//            ((MyViewHolder)holder).tv_time.setText("时长");
//            ((MyViewHolder)holder).tv_rank.setText("总排名");
//            ((MyViewHolder)holder).tv_type.setText("类型");
        }else {
            ((MyViewHolder)holder).tv_count.setText(list.get(position-1).getShakeNum()+"");
            ((MyViewHolder)holder).tv_time.setText(list.get(position-1).getTime());
            ((MyViewHolder)holder).tv_rank.setText(list.get(position-1).getStatus()+"");
            ((MyViewHolder)holder).tv_type.setText(list.get(position-1).getType()+"");
        }
        if (list!=null&&position==list.size()-1){
            ((MyViewHolder)holder).iv_line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 1;
        }
        return list.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_count,tv_time,tv_rank,tv_type;
        ImageView iv_line;
        public MyViewHolder(View view)
        {
            super(view);
            tv_count= view.findViewById(R.id.tv_count_rv_myrecord);
            tv_time = view.findViewById(R.id.tv_time_rv_myrcord);
            tv_rank= view.findViewById(R.id.tv_rank_rv_myrcord);
            tv_type = view.findViewById(R.id.tv_type_rv_myrcord);
            iv_line = view.findViewById(R.id.iv_line_myrecord);

        }
    }
}

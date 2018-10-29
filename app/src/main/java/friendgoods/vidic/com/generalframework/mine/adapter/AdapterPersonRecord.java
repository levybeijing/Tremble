package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.MyRecordBean;

public class AdapterPersonRecord extends RecyclerView.Adapter<AdapterPersonRecord.MyViewHolder> {
    private Context context;
    private List<MyRecordBean.DataBean.PageInfoBean.ListBean> list;


    public AdapterPersonRecord(Context context_, List<MyRecordBean.DataBean.PageInfoBean.ListBean> list_){
        context=context_;
        list=list_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_personrecord, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (position==0){
            return;
        }
        holder.tv_time.setText(list.get(position-1).getTime());
        holder.tv_energy.setText(list.get(position-1).getShakeNum()+"");
        holder.tv_number.setText(list.get(position-1).getShakeNum()+"");
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 1;
        }
        return list.size()+1 ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_time,tv_energy,tv_number;

        public MyViewHolder(View view)
        {
            super(view);
            tv_time = view.findViewById(R.id.tv_time_personrecord);
            tv_energy = view.findViewById(R.id.tv_energy_personrecord);
            tv_number = view.findViewById(R.id.tv_number_personrecord);
        }
    }
}

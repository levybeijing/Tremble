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
import friendgoods.vidic.com.generalframework.bean.FansBangBean;
import friendgoods.vidic.com.generalframework.mine.bean.WallRecordBean;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerMine;

public class AdapterWallRecord extends RecyclerView.Adapter<AdapterWallRecord.MyViewHolder> {
    private Context context;
    private List<WallRecordBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterWallRecord(Context context_) {
        context=context_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_wallrecord, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (position==0){
            return;
        }
        holder.tv_date.setText(list.get(position-1).getTime());
        holder.tv_energy.setText(list.get(position-1).getScore()+"");
        holder.tv_number.setText(list.get(position-1).getNum()+"");
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (list==null) {
            return 1;
        }
        return list.size()+1;
    }

    public void setData(List<WallRecordBean.DataBean.PageInfoBean.ListBean> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder
    {
        TextView tv_date,tv_energy,tv_number;
        public MyViewHolder(View view)
        {
            super(view);
            tv_date= view.findViewById(R.id.tv_date_wallrecord);
            tv_energy = view.findViewById(R.id.tv_energy_wallrecord);
            tv_number = view.findViewById(R.id.tv_number_wallrecord);
        }
    }

}

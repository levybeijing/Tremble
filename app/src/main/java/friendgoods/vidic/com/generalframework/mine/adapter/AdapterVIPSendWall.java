package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPubWall;
import friendgoods.vidic.com.generalframework.bean.MyGiftsListBean;

public class AdapterVIPSendWall extends RecyclerView.Adapter {
    private Context context;
    private List<MyGiftsListBean.DataBean> list;
    private OnItemClickListenerPubWall itemClickListener;
    private List<Integer> remain=new ArrayList<>();

    public AdapterVIPSendWall(Context context_){
        context=context_;

    }

    public void setOnItemClickListener(OnItemClickListenerPubWall itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    public void setData(List<MyGiftsListBean.DataBean> data){
        list=data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_pubwall, parent, false);
        return new MyViewHolder(view);
    }
    private List<Integer> numlist=new ArrayList<>();

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        numlist.add(list.get(position).getNum());

        final String photo = UrlCollect.baseIamgeUrl+list.get(position).getUrl();
        ((MyViewHolder)holder).tv_energy.setText(list.get(position).getScore()+"能量");
        ((MyViewHolder)holder).tv_number.setText(list.get(position).getNum()+"");
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
        Picasso.with(context).load(photo).into(((MyViewHolder)holder).iv_goods);
        remain.add(list.get(position).getNum());
        final int score = list.get(position).getScore();
        final View itemView = holder.itemView;
        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Integer remove = numlist.get(position);
                itemClickListener.onItemClick(list.get(position).getWide(),list.get(position).getHigh(), photo,list.get(position).getGiftId()+"",remove,score);
                if (remove==0){
                    return;
                }
                numlist.remove(position);
                numlist.add(position,--remove);
                ((MyViewHolder) holder).tv_number.setText(remove+"");
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
        ImageView iv_goods;
        TextView tv_name,tv_energy,tv_number;
        public MyViewHolder(View view)
        {
            super(view);
            iv_goods = view.findViewById(R.id.iv_rv_pubwall);
            tv_name= view.findViewById(R.id.tv_name_rv_pubwall);
            tv_energy= view.findViewById(R.id.tv_energy_rv_pubwall);
            tv_number = view.findViewById(R.id.tv_numberlast_rv_pubwall);
        }
    }
}

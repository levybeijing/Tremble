package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.activity.MyGiftsActivity;
import friendgoods.vidic.com.generalframework.mine.bean.MyGiftsBean;

public class AdapterMyGifts extends RecyclerView.Adapter {
    private Context context;
    private List<MyGiftsBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterMyGifts(MyGiftsActivity context_, List<MyGiftsBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_order, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
        ((MyViewHolder)holder).tv_energy.setText(list.get(position).getIntegral());
        ((MyViewHolder)holder).tv_doutui.setText(list.get(position).getScore());
        Picasso.with(context).load(list.get(position).getUrl()).into(((MyViewHolder)holder).iv_icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_icon;
        TextView tv_name,tv_energy,tv_doutui;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon = view.findViewById(R.id.iv_icon_mygifts);
            tv_name= view.findViewById(R.id.tv_name_mygifts);
            tv_energy= view.findViewById(R.id.tv_energy_mygifts);
            tv_doutui = view.findViewById(R.id.tv_price_mygifts);
        }
    }
}

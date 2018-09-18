package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.bean.VIPWallBean;

public class AdapterVipWallRV extends RecyclerView.Adapter {
    private Context context;
    private List<VIPWallBean.DataBean.PageInfoBean.ListBean> list;
    private static boolean isCheckable=false;
    private HashSet<String> set=new HashSet();

    public AdapterVipWallRV(Context context_, List<VIPWallBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_vipwall, parent, false);
        return new MyViewHolder(view);
    }
    //用于刷新数据
    public void freshData(){
        notifyDataSetChanged();
    }

    public void down(HashSet<String> set){
        for (String s:set) {
            //图片下载

        }
    }
    //setDeletelistner

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (isCheckable){
            ((MyViewHolder)holder).tv_name.setVisibility(View.INVISIBLE);
            ((MyViewHolder)holder).tv_energy.setVisibility(View.INVISIBLE);
            ((MyViewHolder)holder).iv_energy.setVisibility(View.INVISIBLE);
            ((MyViewHolder)holder).iv_icon.setVisibility(View.INVISIBLE);
            ((MyViewHolder)holder).box.setVisibility(View.VISIBLE);
            Picasso.with(context).load(list.get(position).getSlt()).into(((MyViewHolder)holder).iv_wall);
        }else{
            ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
            ((MyViewHolder)holder).tv_energy.setText(list.get(position).getScore());
            Picasso.with(context).load(list.get(position).getSlt()).into(((MyViewHolder)holder).iv_wall);
            Picasso.with(context).load(list.get(position).getLogo()).into(((MyViewHolder)holder).iv_icon);
        }
        //处理逻辑是否有问题  生命周期是否会消失
        final VIPWallBean.DataBean.PageInfoBean.ListBean listBean = list.get(position);
        ((MyViewHolder)holder).box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    list.remove(listBean);
                    set.add(listBean.getSlt());
                }else {
                    if (!list.contains(listBean)){
                        list.add(listBean);
                    }
                    set.remove(listBean.getSlt());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_wall,iv_icon,iv_energy;
        TextView tv_name,tv_energy;
        CheckBox box;
        public MyViewHolder(View view)
        {
            super(view);
            iv_wall = view.findViewById(R.id.iv_wall_vipwall);
            iv_icon = view.findViewById(R.id.iv_icon_item_vipwall);
            iv_energy = view.findViewById(R.id.iv_energy_item_vipwall);
            tv_name= view.findViewById(R.id.tv_name_item_vipwall);
            tv_energy= view.findViewById(R.id.tv_number_item_vipwall);
            box= view.findViewById(R.id.box_vipwall);

        }
    }
}

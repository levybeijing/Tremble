package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.VIPWallBean;

public class AdapterVipWallReceive extends RecyclerView.Adapter<AdapterVipWallReceive.MyViewHolder> {
    private Context context;
    private List<VIPWallBean.DataBean.PageInfoBean.ListBean> list;
    private static boolean isCheckable=false;
    private List<Boolean> listFlag=new ArrayList<>();

    public AdapterVipWallReceive(Context context_) {
        context=context_;
    }
    //在同一个碎片中  上下层切换标志
    public void setCheckable(boolean is){
        isCheckable=is;
    }
    //添加数据并加载
    public void setData( List<VIPWallBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        listFlag.clear();
        for (int i = 0; i < list.size(); i++) {
            listFlag.add(false);
        }
        notifyDataSetChanged();
    }
    //获取数据是否被选择标志
    public List<Boolean> getFlag() {
        return listFlag;
    }
    //设置数据  全选 全不选
    public void setALL(boolean yn){
        if(list==null||list.size()==0){
            return;
        }
        listFlag.clear();
        for (int i = 0; i < list.size(); i++) {
            listFlag.add(yn);
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_vipwall, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (isCheckable){
            holder.box_flag.setVisibility(View.VISIBLE);
            holder.ll.setVisibility(View.GONE);
            holder.tv_name.setVisibility(View.INVISIBLE);
            holder.tv_energy.setVisibility(View.INVISIBLE);
            holder.iv_icon.setVisibility(View.INVISIBLE);
            holder.iv_energy.setVisibility(View.INVISIBLE);
        }else{
            holder.tv_name.setText(list.get(position).getName());
            holder.tv_energy.setText(list.get(position).getScore()+"");
            Picasso.with(context).load(list.get(position).getUrl()).into(holder.iv_wall);
            Picasso.with(context).load(list.get(position).getPhoto()).into(holder.iv_icon);
        }
        //
        if (listFlag.get(position)){
            holder.box_flag.setChecked(listFlag.get(position));
        }else {
            holder.box_flag.setChecked(listFlag.get(position));
        }
        //
        holder.box_flag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listFlag.set(position,isChecked);
            }
        });

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
        ImageView iv_wall,iv_icon,iv_energy;
        TextView tv_name,tv_energy;
        CheckBox box_flag;
        LinearLayout ll;
        public MyViewHolder(View view)
        {
            super(view);
            iv_wall = view.findViewById(R.id.iv_wall_vipwall);
            iv_icon = view.findViewById(R.id.iv_icon_item_vipwall);
            iv_energy = view.findViewById(R.id.iv_energy_item_vipwall);
            tv_name= view.findViewById(R.id.tv_name_item_vipwall);
            tv_energy= view.findViewById(R.id.tv_number_item_vipwall);
            ll=view.findViewById(R.id.ll_bottom_vipwall);
            box_flag= view.findViewById(R.id.box_flag_vipwall);

        }
    }
}

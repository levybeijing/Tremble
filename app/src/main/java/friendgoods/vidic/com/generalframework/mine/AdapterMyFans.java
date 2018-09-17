package friendgoods.vidic.com.generalframework.mine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.bean.MyFansBean;

public class AdapterMyFans extends RecyclerView.Adapter<AdapterMyFans.MyViewHolder> {
    private Context context;
    private OnItemClickListener itemClickListener;
    private List<MyFansBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterMyFans(Context context_, List<MyFansBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
    }

//    public void setOnItemClickListener(OnItemClickListener itemClickListene_){
//        itemClickListener=itemClickListene_;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item1_rv_myfans, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position<3){

        }else {

        }


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
        ImageView iv_rank,iv_icon,iv_wall;
        TextView tv_rank,tv_name,tv_value;
        public MyViewHolder(View view)
        {
            super(view);
            iv_rank = view.findViewById(R.id.iv_item1_myfans);
            tv_rank = view.findViewById(R.id.tv_item1_myfans);

            tv_name= view.findViewById(R.id.tv_name_myfans);
            iv_icon= view.findViewById(R.id.iv_icon_myfans);
            iv_wall = view.findViewById(R.id.iv_wall_myfans);
            tv_value = view.findViewById(R.id.tv_value_myfans);
        }
    }

}

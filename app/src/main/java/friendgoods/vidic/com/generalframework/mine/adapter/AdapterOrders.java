package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import friendgoods.vidic.com.generalframework.R;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.MyViewHolder> {
    private Context context;
    private String[] array;
    private Drawable[] paths;
//    private OnItemClickListener itemClickListener;


    public AdapterOrders(Context context_){
        context=context_;
    }

//    public void setOnItemClickListener(OnItemClickListener itemClickListene_){
//        itemClickListener=itemClickListene_;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_orders, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

//        holder.iv_icon.setImageDrawable(paths[position]);
//        holder.tv_name.setText(array[position]);
//        holder.rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳转
//                Toast.makeText(context,"first",Toast.LENGTH_SHORT).show();
//            }
//        });

//        View itemView = ((RelativeLayout) holder.itemView).getChildAt(0);

//        if (itemClickListener != null) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    itemClickListener.onItemClick(position);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
//        ImageView iv_icon,iv_right,iv_bottom;
//        TextView tv_name;
        public MyViewHolder(View view)
        {
            super(view);
//            tv_name= view.findViewById(R.id.tv_name_mine);
//            iv_icon= view.findViewById(R.id.iv_icon_mine);
//            iv_right = view.findViewById(R.id.iv_jiantou_mine);
//            iv_bottom = view.findViewById(R.id.iv_line_mine);
//            rl = view.findViewById(R.id.rl_root_mine);
        }
    }
}

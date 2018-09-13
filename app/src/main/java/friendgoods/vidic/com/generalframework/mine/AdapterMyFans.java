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

import friendgoods.vidic.com.generalframework.R;

public class AdapterMyFans extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private String[] array;
    private Drawable[] paths;
    private OnItemClickListener itemClickListener;
    private static final int TYPE_0=0;
    private static final int TYPE_1=1;


    public AdapterMyFans(Context context_){
        context=context_;
    }

//    public void setOnItemClickListener(OnItemClickListener itemClickListene_){
//        itemClickListener=itemClickListene_;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_0){
            View view = LayoutInflater.from(context).inflate(R.layout.item0_rv_myfans, parent, false);
            return new  ViewHolder(view){};
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item1_rv_myfans, parent, false);
        MyViewHolder1 viewHolder = new MyViewHolder1(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position){
            case 0:
                break;
            case 1:
            case 2:
            case 3:

                break;
            default:

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_0;
        }else {
            return TYPE_1;
        }
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
        return 20;
    }

    class MyViewHolder1 extends ViewHolder
    {
        ImageView iv_rank,iv_icon,iv_wall;
        TextView tv_rank,tv_name,tv_value;
        public MyViewHolder1(View view)
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

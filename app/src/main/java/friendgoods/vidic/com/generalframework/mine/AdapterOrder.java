package friendgoods.vidic.com.generalframework.mine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;

public class AdapterOrder extends RecyclerView.Adapter {
    private Context context;
    private String[] array;
    private Drawable[] paths;
    private OnItemClickListener itemClickListener;


    public AdapterOrder(Context context_,String[] array_,Drawable[] paths_){
        context=context_;
        array=array_;
        paths=paths_;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        MyViewHolder viewHolder = new MyViewHolder(
                inflater.inflate(R.layout.item_my_main, parent, false));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClickListener.onItemLongClick(v);
                return true;
            }
        });

//        View view = LayoutInflater.from(context).inflate(R.layout.item_my_main, parent, false);
//        MyViewHolder vh = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position==paths.length-1){
            ((MyViewHolder)holder).iv_bottom.setVisibility(View.INVISIBLE);
        }
        ((MyViewHolder)holder).iv_icon.setImageDrawable(paths[position]);
        ((MyViewHolder)holder).tv_name.setText(array[position]);
        ((MyViewHolder)holder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转

            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_icon,iv_right,iv_bottom;
        TextView tv_name;
        RelativeLayout rl;
        public MyViewHolder(View view)
        {
            super(view);
            tv_name= view.findViewById(R.id.tv_name_mine);
            iv_icon= view.findViewById(R.id.iv_icon_mine);
            iv_right = view.findViewById(R.id.iv_jiantou_mine);
            iv_bottom = view.findViewById(R.id.iv_line_mine);
            rl = view.findViewById(R.id.rl_root_mine);

        }
    }
}

package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.IconSetBean;
import friendgoods.vidic.com.generalframework.bean.MyWallBean;

public class AdapterMyPubWall extends RecyclerView.Adapter<AdapterMyPubWall.MyViewHolder> {
    private Context context;
    private List<MyWallBean.DataBean.UserPhotoBean> list;

    public AdapterMyPubWall(Context context_) {
        context=context_;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_bottom_fansbang, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getPhoto()).into(holder.iv_icon);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (list==null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<MyWallBean.DataBean.UserPhotoBean> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv_icon;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon= view.findViewById(R.id.iv_rv_mypubwall);
        }
    }

}

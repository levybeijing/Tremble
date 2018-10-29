package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.VIPWallBean;

public class AdapterVipGiftSend extends RecyclerView.Adapter {
    private Context context;
    private List<VIPWallBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterVipGiftSend(Context context_) {
        context=context_;
    }
    public void setData(List<VIPWallBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_vipwallsend, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String photo = UrlCollect.baseIamgeUrl+list.get(position).getSlt();
        Picasso.with(context).load(photo).into(((MyViewHolder)holder).iv_icon);
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
        ImageView iv_icon;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon = view.findViewById(R.id.iv_wall_vipwallsend);
        }
    }
}

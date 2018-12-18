package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.VIPSendBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;

public class AdapterVipGiftSend extends RecyclerView.Adapter<AdapterVipGiftSend.MyViewHolder> {
    private Context context;
    private List<VIPSendBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterVipGiftSend(Context context_) {
        context=context_;
    }

    public void setData(List<VIPSendBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_vipwallsend, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String url = list.get(position).getUrl();
        Log.e("=======AdapterSend", url+"");
        if (url!=null){
            Picasso.with(context).load(UrlCollect.baseIamgeUrl+url).into(holder.iv_icon);
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
        ImageView iv_icon;
        public MyViewHolder(View view)
        {
            super(view);
            iv_icon = view.findViewById(R.id.iv_wall_vipwallsend);
        }
    }
}

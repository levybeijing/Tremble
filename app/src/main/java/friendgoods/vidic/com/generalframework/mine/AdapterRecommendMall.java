package friendgoods.vidic.com.generalframework.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;

public class AdapterRecommendMall extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String>  list;
    public AdapterRecommendMall(Context context_,List<String>  list_){
        context=context_;
        list=list_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面 并弹窗选择数量

            }
        });
//        ((MyViewHolder)holder).tv_name.setText();
//        ((MyViewHolder)holder).tv_sub.setText();
//        ((MyViewHolder)holder).tv_price.setText();
        //用图片框架取代
//        ((MyViewHolder)holder).iv_goods.setImageDrawable();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_goods,iv_buy;
        TextView tv_name,tv_sub,tv_price;
        public MyViewHolder(View view)
        {
            super(view);
            TextView name = view.findViewById(R.id.tv_goodsname_recommend);
            TextView sub = view.findViewById(R.id.tv_goodssub_recommend);
            TextView price = view.findViewById(R.id.tv_price_recommend);
            ImageView icon = view.findViewById(R.id.iv_goodsicon_recommend);
            ImageView buy = view.findViewById(R.id.iv_goodsbuy_recommend);
        }
    }
}

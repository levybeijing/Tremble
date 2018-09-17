package friendgoods.vidic.com.generalframework.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.bean.GoodsRecommendBean;

public class AdapterRecommendMall extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GoodsRecommendBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterRecommendMall(Context context_, List<GoodsRecommendBean.DataBean.PageInfoBean.ListBean> list_) {
        context=context_;
        list=list_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_rv_mall, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面 并弹窗选择数量
                Intent intent = new Intent(context, DetailGoodsActivity.class);
                //加入数据
                intent.putExtra("goodsId",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
        ((MyViewHolder)holder).tv_sub.setText(list.get(position).getIntro());
        ((MyViewHolder)holder).tv_price.setText(list.get(position).getMoney()+"");
        Picasso.with(context).load(list.get(position).getPhoto()).into(((MyViewHolder)holder).iv_goods);
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
            tv_name = view.findViewById(R.id.tv_goodsname_recommend);
            tv_sub = view.findViewById(R.id.tv_goodssub_recommend);
            tv_price = view.findViewById(R.id.tv_price_recommend);
            iv_goods = view.findViewById(R.id.iv_goodsicon_recommend);
            iv_buy = view.findViewById(R.id.iv_goodsbuy_recommend);
        }
    }
}

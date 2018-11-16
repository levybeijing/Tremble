package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.DetailGoodsActivity;
import friendgoods.vidic.com.generalframework.bean.GoodsRecommendBean;

public class AdapterRecommendMall extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GoodsRecommendBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterRecommendMall(Context context_) {
        context=context_;

    }

    public void setData(List<GoodsRecommendBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_rv_mall, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面 并弹窗选择数量
                Intent intent = new Intent(context, DetailGoodsActivity.class);
                //加入数据
                intent.putExtra("bean",list.get(position));
                intent.putExtra("goodsId",list.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
        ((MyViewHolder)holder).tv_sub.setText(list.get(position).getIntro());
        ((MyViewHolder)holder).tv_price.setText(list.get(position).getMoney()+"元");
        Picasso.with(context).load(UrlCollect.baseIamgeUrl+list.get(position).getPhoto()).into(((MyViewHolder)holder).iv_goods);
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
        ImageView iv_goods;
        TextView tv_name,tv_sub,tv_price,tv_buy;
        public MyViewHolder(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_goodsname_recommend);
            tv_sub = view.findViewById(R.id.tv_goodssub_recommend);
            tv_price = view.findViewById(R.id.tv_price_recommend);
            iv_goods = view.findViewById(R.id.iv_goodsicon_recommend);
            tv_buy = view.findViewById(R.id.iv_goodsbuy_recommend);
        }
    }
}

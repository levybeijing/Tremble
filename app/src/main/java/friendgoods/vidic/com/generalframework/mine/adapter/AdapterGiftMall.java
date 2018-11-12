package friendgoods.vidic.com.generalframework.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.GiftsMallBean;
import friendgoods.vidic.com.generalframework.mine.activity.DetailGoodsActivity;

public class AdapterGiftMall extends RecyclerView.Adapter<AdapterGiftMall.MyViewHolder> {
    private Context context;
    private List<GiftsMallBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterGiftMall(Context context_) {
        context=context_;
    }

    public void setData(List<GiftsMallBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gifts_rv_mall, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面 并弹窗选择数量
                Intent intent = new Intent(context, DetailGoodsActivity.class);
                //加入数据
                intent.putExtra("bean", (Parcelable) list.get(position));
                intent.putExtra("goodsId",list.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_energy.setText(list.get(position).getScore()+"");
        holder.tv_jifen.setText(list.get(position).getIntegral()+"");
        String url = list.get(position).getUrl();
        Picasso.with(context).load(UrlCollect.baseIamgeUrl+url).into(holder.iv_icon);

    }
    private void request(int number) {

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
        Button btn_buy;
        ImageView iv_icon;
        TextView tv_name,tv_energy,tv_jifen;
        EditText et_number;

        public MyViewHolder(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name_gifts);
            btn_buy = view.findViewById(R.id.btn_buy_gifts);
            iv_icon = view.findViewById(R.id.iv_goods_gifts);
            tv_energy = view.findViewById(R.id.tv_energy_gifts);
            tv_jifen = view.findViewById(R.id.tv_doutuinumber_gifts);
            et_number = view.findViewById(R.id.et_number_gifts);
        }
    }
}

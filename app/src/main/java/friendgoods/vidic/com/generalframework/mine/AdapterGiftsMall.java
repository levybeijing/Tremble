package friendgoods.vidic.com.generalframework.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;

public class AdapterGiftsMall extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;

    public AdapterGiftsMall(Context context_,List<String>  list_){
        context=context_;
        list=list_;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((MyViewHolder)holder).tv_doutui.setText();
//        ((MyViewHolder)holder).tv_energy.setText();
//        ((MyViewHolder)holder).tv_name.setText();
//        ((MyViewHolder)holder).et_number.setText();
//        ((MyViewHolder)holder).iv_goods.setImageDrawable();
        ((MyViewHolder)holder).btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断金币是否足够 可以的话 网络访问购买请求
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        Button btn_buy;
        ImageView iv_goods;
        TextView tv_name,tv_energy,tv_doutui;
        EditText et_number;
        public MyViewHolder(View view)
        {
            super(view);
            tv_name= view.findViewById(R.id.tv_name_gifts);
            tv_energy= view.findViewById(R.id.tv_energy_gifts);
            tv_doutui = view.findViewById(R.id.tv_doutuinumber_gifts);
            iv_goods = view.findViewById(R.id.iv_goods_gifts);
            btn_buy = view.findViewById(R.id.btn_buy_gifts);
            et_number=view.findViewById(R.id.et_number_gifts);
        }
    }
}

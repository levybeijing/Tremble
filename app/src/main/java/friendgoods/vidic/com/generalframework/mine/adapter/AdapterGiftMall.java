package friendgoods.vidic.com.generalframework.mine.adapter;

import android.app.Activity;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.bean.GiftsMallBean;
import friendgoods.vidic.com.generalframework.mine.activity.DetailGoodsActivity;
import friendgoods.vidic.com.generalframework.mine.activity.VIPSendWallActivity;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;

public class AdapterGiftMall extends RecyclerView.Adapter<AdapterGiftMall.MyViewHolder> {
    private Activity context;
    private List<GiftsMallBean.DataBean.PageInfoBean.ListBean> list;

    public AdapterGiftMall(Activity context_) {
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
        final int giftid = list.get(position).getId();
        holder.tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jf=list.get(position).getIntegral();
                final String number = holder.et_number.getText().toString();
                final int jifen =Integer.parseInt(number)*jf;
                int integral = (int)SharedPFUtils.getParam(context, "integral", 0);
                if (jifen>integral){
                    Toast.makeText(context, "您的积分不足", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (buyGift(giftid,number,jifen)){
                    holder.et_number.setText("1");
                }
            }
        });
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_energy.setText(list.get(position).getScore()+"");
        holder.tv_jifen.setText(list.get(position).getIntegral()+"");
        String url = list.get(position).getUrl();
        Picasso.with(context).load(UrlCollect.baseIamgeUrl+url).into(holder.iv_icon);

        holder.iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.et_number.setText(Integer.parseInt(holder.et_number.getText().toString())+1+"");
            }
        });
        holder.iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(holder.et_number.getText().toString())-1;
                if (i>0)
                    holder.et_number.setText(i+"");
            }
        });
    }

    private boolean buyGift(int id,String number,int jifen) {
        OkGo.post(UrlCollect.convertGift)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(context,"userId",0)+"")
                .params("giftId", id+"")
                .params("num", number)
                .params("integral", jifen+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=================", "onSuccess: "+s);
                        TokenCheck.toLogin(context,s);

                        try {
                            JSONObject jo=new JSONObject(s);
                            String message = jo.getString("message");
                            if (message.equals("请求成功")){
                                Toast.makeText(context, "购买成功", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return true;
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
        TextView tv_buy;
        ImageView iv_icon,iv_plus,iv_reduce;
        TextView tv_name,tv_energy,tv_jifen;
        EditText et_number;

        public MyViewHolder(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name_gifts);
            tv_buy = view.findViewById(R.id.btn_buy_gifts);
            tv_energy = view.findViewById(R.id.tv_energy_gifts);
            tv_jifen = view.findViewById(R.id.tv_doutuinumber_gifts);
            et_number = view.findViewById(R.id.et_number_gifts);

            iv_icon = view.findViewById(R.id.iv_goods_gifts);
            iv_plus = view.findViewById(R.id.iv_plus_gift);
            iv_reduce = view.findViewById(R.id.iv_reduce_gift);
        }
    }
}

package friendgoods.vidic.com.generalframework.mine.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerOrderId;
import friendgoods.vidic.com.generalframework.mine.activity.DetailOrdersActivity;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterOrdersCommon;
import friendgoods.vidic.com.generalframework.bean.OrdersBean;
import okhttp3.Call;
import okhttp3.Response;

public class FragOrders2 extends Fragment{

    private AdapterOrdersCommon adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView rv = view.findViewById(R.id.rv_orders);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        adapter = new AdapterOrdersCommon(getContext());
        rv.setAdapter(adapter);
        //网络访问obtainOrderList
        OkGo.post(UrlCollect.obtainOrderList)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "1")
                .params("pageSize", "10")
                .params("status", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=====================", "onSuccess: "+s);
                        OrdersBean ordersBean = new Gson().fromJson(s, OrdersBean.class);
                        List<OrdersBean.DataBean.PageInfoBean.ListBean> list = ordersBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
        adapter.setClickListenerPosition(new OnItemClickListenerOrderId() {
            @Override
            public void onItemClick(String s) {
                Intent intent=new Intent(getContext(), DetailOrdersActivity.class);
                intent.putExtra("orderId",s);
                startActivity(intent);
            }
        });
    }
}

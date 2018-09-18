package friendgoods.vidic.com.generalframework.mine.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterOrdersCommon;
import friendgoods.vidic.com.generalframework.mine.bean.OrdersBean;
import okhttp3.Call;
import okhttp3.Response;

public class FragOrders2 extends Fragment{

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

        //网络访问
        OkGo.post(UrlCollect.recordOfGame)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "0")
                .params("pageSize", "10")
                .params("status", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=====================", "onSuccess: "+s);
                        OrdersBean ordersBean = new Gson().fromJson(s, OrdersBean.class);
                        List<OrdersBean.DataBean.PageInfoBean.ListBean> list = ordersBean.getData().getPageInfo().getList();

                        AdapterOrdersCommon adapter=new AdapterOrdersCommon(getContext(),list);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    }
                });

    }
}

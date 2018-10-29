package friendgoods.vidic.com.generalframework.mine.frag;

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

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterRecommendMall;
import friendgoods.vidic.com.generalframework.bean.GoodsRecommendBean;
import okhttp3.Call;
import okhttp3.Response;

public class FragRecommend extends Fragment {

    private RecyclerView rv;
    private AdapterRecommendMall adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recommend_rv_mall,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_recommend_mall);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);

        adapter = new AdapterRecommendMall(getContext());
        rv.setAdapter(adapter);
        //优化设置
//        rv.setItemViewCacheSize(20);
        request();
    }

    private void request() {
        OkGo.post(UrlCollect.goodsListRecommmend)//
                .tag(this)//
                .params("page", "0")
                .params("pageSize", "12")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        GoodsRecommendBean recommendBean = new Gson().fromJson(s, GoodsRecommendBean.class);
                        adapter.setData(recommendBean.getData().getPageInfo().getList());
                    }
                });
    }
}

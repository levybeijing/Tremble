package friendgoods.vidic.com.generalframework.mine.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.VIPSendBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterVipGiftSend;
import friendgoods.vidic.com.generalframework.bean.VIPWallBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class FragVipGiftSend extends Fragment {


    private RecyclerView rv;
    private AdapterVipGiftSend adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vipwallsend,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rv_frag_vipwallsend);
        GridLayoutManager manager=new GridLayoutManager(getContext(),3);
        rv.setLayoutManager(manager);
        adapter = new AdapterVipGiftSend(getContext());
        rv.setAdapter(adapter);
        //网络访问
        request();

    }

    private void request() {
        //网络访问
        OkGo.post(UrlCollect.vipWallSend)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(getContext(),"userId",0))
                .params("page", "1")
                .params("pageSize", "20")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        VIPSendBean vipWallBean = new Gson().fromJson(s, VIPSendBean.class);
                        adapter.setData(vipWallBean.getData().getPageInfo().getList());
                    }
                });

    }
}

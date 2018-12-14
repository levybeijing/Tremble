package friendgoods.vidic.com.generalframework.mine.frag;

import android.app.Activity;
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
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.PublicWallActivity;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterGiftMall;
import friendgoods.vidic.com.generalframework.bean.GiftsMallBean;
import okhttp3.Call;
import okhttp3.Response;

public class FragGifts extends Fragment {

    private AdapterGiftMall adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gifts_rv_mall,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView rv = view.findViewById(R.id.rv_gifts_mall);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        adapter = new AdapterGiftMall(getActivity());
        rv.setAdapter(adapter);
//        Log.e("=================", "onSuccess: "+UrlCollect.giftsListGift);
        OkGo.post(UrlCollect.giftsListGift)//
                .tag(this)//
                .params("isUse", "1")
                .params("page", "1")
                .params("pageSize", "20")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=================", "onSuccess: "+s);
                        TokenCheck.toLogin(getActivity(),s);

                        GiftsMallBean giftsMallBean = new Gson().fromJson(s, GiftsMallBean.class);
                        List<GiftsMallBean.DataBean.PageInfoBean.ListBean> list = giftsMallBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}

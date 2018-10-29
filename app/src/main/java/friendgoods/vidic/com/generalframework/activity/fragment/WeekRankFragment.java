package friendgoods.vidic.com.generalframework.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.LazyFragment;
import friendgoods.vidic.com.generalframework.adapter.AdapterWeekRank;
import friendgoods.vidic.com.generalframework.bean.WeekRankBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class WeekRankFragment extends Fragment {

    private AdapterWeekRank adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekrank, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv_weekrank);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);
        adapter = new AdapterWeekRank(getActivity());
        rv.setAdapter(adapter);
        request();
    }
    private void request() {
        OkGo.post(UrlCollect.worldRankings)//
                .tag(this)//
                .params("page", "1")
                .params("pageSize", "10")
                .params("type", "1")    //1 周排行
                .params("status", "0")//0  手动  1脚动
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        WeekRankBean bean = new Gson().fromJson(s, WeekRankBean.class);
                        adapter.setData(bean.getData().getPageInfo().getList());
                    }
                });
    }
}

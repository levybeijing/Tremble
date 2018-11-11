package friendgoods.vidic.com.generalframework.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.AdapterRank;
import friendgoods.vidic.com.generalframework.bean.WeekRankBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.MyRecordActivity;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPosition;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class WeekRankFragment extends Fragment {

    private AdapterRank adapter;
    private String currentAction="android.tremble.WORLD";
    private int worldPage=1;
    private int friendPage=1;

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
        adapter = new AdapterRank(getActivity());
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListenerPosition() {
            @Override
            public void onItemClick(int i) {
                Intent intent=new Intent(getContext(),MyRecordActivity.class);
                intent.putExtra("userId",i+"");
                startActivity(intent);
            }
        });
        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (currentAction.equals(intent.getAction())){
                    return;
                }
                switch (intent.getAction()){
                    case "android.tremble.FRIEND":
                        request2(friendPage);
                        break;
                    case "android.tremble.WORLD":
                        request(worldPage);
                        break;
//                    case "android.tremble.PRE":
//                        if (currentAction.equals("android.tremble.WORLD")&&worldPage>1)
//                            request(--worldPage);
//                        else if (currentAction.equals("android.tremble.FRIEND")&&friendPage>1)
//                            request2(--friendPage);
//                        else
//                            Toast.makeText(context, "当前是第一页", Toast.LENGTH_SHORT).show();
//                        break;
//                    case "android.tremble.NEXT":
//                        if (currentAction.equals("android.tremble.WORLD")&&worldPage>1)
//                            request(++worldPage);
//                        else if (currentAction.equals("android.tremble.FRIEND")&&friendPage>1)
//                            request2(++friendPage);
//                        else
//                            Toast.makeText(context, "当前是第一页", Toast.LENGTH_SHORT).show();
//                        break;
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction("android.tremble.FRIEND");
        intentFilter.addAction("android.tremble.WORLD");
//        intentFilter.addAction("android.tremble.PRE");
//        intentFilter.addAction("android.tremble.NEXT");
        // 3. 动态注册：调用Context的registerReceiver（）方法
        getContext().registerReceiver(mBroadcastReceiver, intentFilter);
        request(1);
    }
    private void request(int page) {
        OkGo.post(UrlCollect.worldRankings)//
                .tag(this)//
                .params("page", page+"")
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
    private void request2(int page) {
        OkGo.post(UrlCollect.getFriendp)//
                .tag(this)//
                .params("page", page+"")
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

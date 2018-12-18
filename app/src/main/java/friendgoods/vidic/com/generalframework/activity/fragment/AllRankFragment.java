package friendgoods.vidic.com.generalframework.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.activity.base.LazyFragment;
import friendgoods.vidic.com.generalframework.adapter.AdapterRank;
import friendgoods.vidic.com.generalframework.bean.WeekRankBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.FriendNameActivity;
import friendgoods.vidic.com.generalframework.mine.activity.MyRecordActivity;
import friendgoods.vidic.com.generalframework.mine.customview.CirImageView;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPosition;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import friendgoods.vidic.com.generalframework.xrecyclerview.ProgressStyle;
import friendgoods.vidic.com.generalframework.xrecyclerview.XRecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class AllRankFragment extends Fragment {

    private AdapterRank adapter;
    private String currentAction="android.tremble.WORLD";
    private int worldPage=1;
    private int friendPage=1;
    private BroadcastReceiver mBroadcastReceiver;
    private CirImageView icon1,icon2,icon3;
    private TextView name1,time1,count1,name2,time2,count2,name3,time3,count3;
    private XRecyclerView rv;

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
        rv = view.findViewById(R.id.rv_weekrank);

        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        adapter = new AdapterRank(getActivity());

        rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rv.setArrowImageView(R.drawable.iconfont_downgrey);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switch (currentAction){
                    case "android.tremble.FRIEND2":
                        requestFriend(1);
                        break;
                    case "android.tremble.WORLD2":
                        requestworld(1);
                        break;
                }
            }

            @Override
            public void onLoadMore() {
                switch (currentAction){
                    case "android.tremble.FRIEND2":
                        requestFriend(++friendPage);
                        break;
                    case "android.tremble.WORLD2":
                        requestworld(++worldPage);
                        break;
                }
            }
        });

        rv.setAdapter(adapter);
        rv.refresh();
        adapter.setOnItemClickListener(new OnItemClickListenerPosition() {
            @Override
            public void onItemClick(int i) {
                Intent intent=new Intent(getContext(),FriendNameActivity.class);
                intent.putExtra("userId",i+"");
                startActivity(intent);
            }
        });

        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (currentAction.equals(intent.getAction())){
                    return;
                }
                switch (intent.getAction()){
                    case "android.tremble.FRIEND2":
                        requestFriend(friendPage);
                        currentAction="android.tremble.FRIEND2";
                        break;
                    case "android.tremble.WORLD2":
                        requestworld(worldPage);
                        currentAction="android.tremble.WORLD2";
                        break;
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction("android.tremble.FRIEND2");
        intentFilter.addAction("android.tremble.WORLD2");
        getContext().registerReceiver(mBroadcastReceiver, intentFilter);


        icon1 = view.findViewById(R.id.icon_first_top3);
        name1 = view.findViewById(R.id.name_first_top3);
        time1 = view.findViewById(R.id.time_first_top3);
        count1 = view.findViewById(R.id.count_first_top3);

        icon2 = view.findViewById(R.id.icon_second_top3);
        name2 = view.findViewById(R.id.name_second_top3);
        time2 = view.findViewById(R.id.time_second_top3);
        count2 = view.findViewById(R.id.count_second_top3);

        icon3 = view.findViewById(R.id.icon_third_top3);
        name3 = view.findViewById(R.id.name_third_top3);
        time3 = view.findViewById(R.id.time_third_top3);
        count3 = view.findViewById(R.id.count_third_top3);

        requestworld(1);
    }
    private void requestworld(int page) {
        OkGo.post(UrlCollect.worldRankings)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(getContext(),"userId",0))
                .params("page", page)
                .params("pageSize", "20")
                .params("type", "0")    //1 周排行 0总排行
                .params("status", "0")//0  手动  1脚动
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(getActivity(),s);
                        Log.i("*********", response.toString());
                        WeekRankBean bean = new Gson().fromJson(s, WeekRankBean.class);
                        List<WeekRankBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();


                        Picasso.with(getActivity()).load(list.get(0).getPhoto()).into(icon1);
                        name1.setText(list.get(0).getName());
                        time1.setText(list.get(0).getTime());
                        count1.setText(list.get(0).getShakeNum()+"");

                        Picasso.with(getActivity()).load(list.get(1).getPhoto()).into(icon2);
                        name2.setText(list.get(1).getName());
                        time2.setText(list.get(1).getTime());
                        count2.setText(list.get(1).getShakeNum()+"");

                        Picasso.with(getActivity()).load(list.get(2).getPhoto()).into(icon3);
                        name3.setText(list.get(2).getName());
                        time3.setText(list.get(2).getTime());
                        count3.setText(list.get(2).getShakeNum()+"");

                        final int userId1 = list.get(0).getUserId();
                        icon1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(getContext(),FriendNameActivity.class);

                                intent.putExtra("userId", userId1 +"");
                                startActivity(intent);
                            }
                        });
                        final int userId2 = list.get(1).getUserId();

                        icon2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(getContext(),FriendNameActivity.class);
                                intent.putExtra("userId",userId2+"");
                                startActivity(intent);
                            }
                        });
                        final int userId3 = list.get(2).getUserId();

                        icon3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(getContext(),FriendNameActivity.class);
                                intent.putExtra("userId",userId3+"");
                                startActivity(intent);
                            }
                        });
                        rv.refreshComplete();
                        rv.loadMoreComplete();
                        adapter.setData(list);
                        adapter.notifyDataSetChanged();

                    }
                });
    }

    private void requestFriend(int page) {
        OkGo.post(UrlCollect.getFriendp)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(getContext(),"userId",0))
                .params("page", page+"")
                .params("pageSize", "20")
                .params("type", "0")    //1 周排行 0总排行
                .params("status", "0")//0  手动  1脚动
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(getActivity(),s);

                        WeekRankBean bean = new Gson().fromJson(s, WeekRankBean.class);
                        rv.refreshComplete();
                        rv.loadMoreComplete();
                        adapter.setData(bean.getData().getPageInfo().getList());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}

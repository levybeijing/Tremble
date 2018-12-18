package friendgoods.vidic.com.generalframework.activity.fragment;import android.content.Intent;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.support.design.widget.TabLayout;import android.support.v4.app.Fragment;import android.support.v4.app.FragmentManager;import android.support.v4.view.ViewPager;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import com.google.gson.Gson;import com.lzy.okgo.OkGo;import com.lzy.okgo.callback.StringCallback;import com.squareup.picasso.Picasso;import java.util.ArrayList;import java.util.List;import friendgoods.vidic.com.generalframework.R;import friendgoods.vidic.com.generalframework.adapter.RankPagerAdapter;import friendgoods.vidic.com.generalframework.activity.base.BaseFragment;import friendgoods.vidic.com.generalframework.bean.MyrankBean;import friendgoods.vidic.com.generalframework.entity.UrlCollect;import friendgoods.vidic.com.generalframework.util.SharedPFUtils;import okhttp3.Call;import okhttp3.Response;/** * Created by Administrator on 2016/10/8 0008. */public class RankFragment extends BaseFragment {    private View view;    private WeekRankFragment weekRankFragment;    private AllRankFragment allRankFragment;    private List<Fragment> list_fragment;    private List<String> list_title;    private TabLayout tab;    private RankPagerAdapter adapter;    private ViewPager vp;    private TextView world;    private TextView friends;    private TextView rank ,name,time,count;    private ImageView icon;    @Nullable    @Override    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_rank, null);//        rank = view.findViewById(R.id.tv_myrank);        icon = view.findViewById(R.id.iv_icon_myrank);        name = view.findViewById(R.id.tv_name_myrank);        time = view.findViewById(R.id.tv_time_myrank);        count = view.findViewById(R.id.tv_count_myrank);        tab = view.findViewById(R.id.tab_FindFragment_title);        vp = view.findViewById(R.id.vp_FindFragment_pager);        world = view.findViewById(R.id.class_world);        friends = view.findViewById(R.id.class_friends);//        world.setSelected(true);        friends.setSelected(false);        weekRankFragment = new WeekRankFragment();        allRankFragment = new AllRankFragment();        list_fragment = new ArrayList<>();        list_fragment.add(weekRankFragment);        list_fragment.add(allRankFragment);        FragmentManager manager = getChildFragmentManager();        list_title = new ArrayList<>();        list_title.add("本周排行");        list_title.add("总排行");        tab.setTabMode(TabLayout.MODE_FIXED);        adapter = new RankPagerAdapter(manager, list_fragment, list_title);        //        vp.setAdapter(adapter);        vp.setCurrentItem(0);        //        tab.setupWithViewPager(vp);        world.setOnClickListener(this);        friends.setOnClickListener(this);        return view;    }    @Override    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        OkGo.post(UrlCollect.myWorldRankings)//                .tag(this)//                .params("userId", (int)SharedPFUtils.getParam(getContext(),"userId",0))                .params("type", 1)                .params("status", "0")                .execute(new StringCallback() {                    @Override                    public void onSuccess(String s, Call call, Response response) {//                        Log.e("==============", "onSuccess: "+s);                        MyrankBean myrankBean = new Gson().fromJson(s, MyrankBean.class);                        int rownum = (int) myrankBean.getData().getRownum();                        rank.setText(rownum +"");                        if (myrankBean.getData().getPhoto()!=null){                            Picasso.with(view.getContext()).load(myrankBean.getData().getPhoto()).into(icon);                        }                        name.setText(myrankBean.getData().getName());                        time.setText(myrankBean.getData().getTime());                        count.setText(myrankBean.getData().getShakeNum()+"");                    }                });    }    @Override    public void onClick(View view) {        switch (view.getId()) {            case R.id.class_world:                world.setSelected(true);                friends.setSelected(false);//                Intent intent=new Intent();                if (vp.getCurrentItem()==0){                    intent.setAction("android.tremble.WORLD");                }else{                    intent.setAction("android.tremble.WORLD2");                }                getContext().sendBroadcast(intent);                break;            case R.id.class_friends:                world.setSelected(false);                friends.setSelected(true);//                Intent intent2=new Intent();                if (vp.getCurrentItem()==0){                    intent2.setAction("android.tremble.FRIEND");                }else{                    intent2.setAction("android.tremble.FRIEND2");                }                getContext().sendBroadcast(intent2);                break;        }    }}
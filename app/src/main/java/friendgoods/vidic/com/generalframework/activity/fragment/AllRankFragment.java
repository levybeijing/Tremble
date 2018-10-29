package friendgoods.vidic.com.generalframework.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.LazyFragment;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class AllRankFragment extends Fragment {

//    private XListView mMainRecycler;
//    private String tokenData;
//    private int page = 1;
//    private int pageSize = 10;
//    private int status = 3;
//    List<HaveHandBean.DataBean.ListBean> haveDataAll = new ArrayList();
//    private HaveListAdapter haveListAdapter;
//    private ImageView mNullData;
//    private List<NewBean.DataBean.PageInfoBean.ListBean> newListAll = new ArrayList<>();
//    private MainListAdapter mainListAdapter;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekrank, container,false);

//        View view = View.inflate(getActivity(), R.layout.fragment_weekrank, null);

//        tokenData = SharedPFUtils.getData(getApplication(), "data");
//
//        mMainRecycler = (XListView) view.findViewById(R.id.main_recycler);
//        mNullData = (ImageView) view.findViewById(R.id.null_data);
//
//
//        mMainRecycler.setPullRefreshEnable(true);
//        mMainRecycler.setPullLoadEnable(true);
//        mMainRecycler.setXListViewListener(this);
//
//        newListAll.clear();

        return view;
    }
//
//    @Override
//    public void onStart() {
//        requestList();
//        super.onStart();
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }

//    @Override
//    protected void lazyLoad() {
//
//    }
//
//    private void requestList() {
//
//        OkGo.post(UrlCollect.ORDER)
//                .tag(this)
//                .params("horseManId", SharedPFUtils.getData(getApplication(), "data"))
//                .params("status", "1")
//                .params("page", page)
//                .params("pageSize", pageSize)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        mMainRecycler.stopRefresh();
//                        mMainRecycler.stopLoadMore();
//                        if (!JsonUtul.getString("state", s).equals("0")) {
//                            NewBean newBean = new Gson().fromJson(s, NewBean.class);
//                            List<NewBean.DataBean.PageInfoBean.ListBean> newList = newBean.getData().getPageInfo().getList();
//                            if (!newList.isEmpty()) {
//                                newListAll.addAll(newList);
//                                mainListAdapter = new MainListAdapter(getActivity(), newListAll, 1);
//                                mMainRecycler.setAdapter(mainListAdapter);
//                                mainListAdapter.notifyDataSetChanged();
//
//                                mainListAdapter.setItemClickListener(new MainListAdapter.OnItemClickListener() {
//                                    @Override
//                                    public void onClick(View v, int position, View view, RecyclerView adaapterView) {
//                                        switch (v.getId()){
//                                            case R.id.list_goods:
//                                                requestData(newListAll.get(position).getId(),position);
//                                                break;
//                                        }
//                                    }
//                                });
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        mMainRecycler.stopRefresh();
//                        mMainRecycler.stopLoadMore();
//                    }
//                });
//    }
//
//
//    @Override
//    public void onRefresh() {
//        page = 1;
//        newListAll.clear();
//        requestList();
//    }
//
//    @Override
//    public void onLoadMore() {
//        page++;
//        if (haveDataAll.size() < 10) {
//            mMainRecycler.stopRefresh();
//            mMainRecycler.stopLoadMore();
//        } else {
//            requestList();
//        }
//    }
//
//    private void requestData(int uuid, final int position) {
//
//        OkGo.post(UrlCollect.ORDERSTATUS)
//                .tag(this)
//                .headers("Accept", "*/*")
//                .params("orderId", uuid)
//                .params("status", "2")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        if (JsonUtul.getString("state",s).equals("0")){
//                            ToastUtils.shortToast("请求失败");
//                        }else {
//                            newListAll.remove(position);
//                            mainListAdapter.notifyDataSetChanged();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                    }
//                });
//    }
}

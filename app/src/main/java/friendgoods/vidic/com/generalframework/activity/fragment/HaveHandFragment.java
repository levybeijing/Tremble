package friendgoods.vidic.com.generalframework.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import friendgoods.vidic.com.generalframework.R;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class HaveHandFragment extends LazyFragment {

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

//    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_have, null);

//        tokenData = SharedPreferencesUtils.getData(getApplication(), "data");
//
//        mMainRecycler = (XListView) view.findViewById(R.id.main_recycler);
//        mNullData = (ImageView) view.findViewById(R.id.null_data);
//
//
//        mMainRecycler.setPullRefreshEnable(true);
//        mMainRecycler.setPullLoadEnable(true);
//        mMainRecycler.setXListViewListener(this);


        return view;
    }

    @Override
    protected void lazyLoad() {

    }
}

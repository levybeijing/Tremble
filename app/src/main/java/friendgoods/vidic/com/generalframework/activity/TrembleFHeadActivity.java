package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.fragment.LazyFragment;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class TrembleFHeadActivity extends LazyFragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_tremble_head, null);

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
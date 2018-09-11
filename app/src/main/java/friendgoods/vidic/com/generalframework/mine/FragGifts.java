package friendgoods.vidic.com.generalframework.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.R;

public class FragGifts extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gifts_rv_mall,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv_gifts_mall);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        //优化设置
//        rv.setItemViewCacheSize(20);
//        rv.setDrawingCacheEnabled(true);
//        rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        //在此传输数据 未考虑懒加载
        AdapterGiftsMall adapter= new AdapterGiftsMall(getContext(),new ArrayList<String>());
        //如果必须用 notifyDataSetChanged()，那么最好设置 mAdapter.setHasStableIds(true)
        //adapter.setHasStableIds(true);

        rv.setAdapter(adapter);
    }
}

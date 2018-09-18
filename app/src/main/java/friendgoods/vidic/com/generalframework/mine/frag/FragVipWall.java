package friendgoods.vidic.com.generalframework.mine.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterOrdersCommon;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterVipWallRV;
import friendgoods.vidic.com.generalframework.mine.bean.OrdersBean;
import friendgoods.vidic.com.generalframework.mine.bean.VIPWallBean;
import okhttp3.Call;
import okhttp3.Response;

public class FragVipWall extends Fragment {


    private RecyclerView rv;
    private AdapterVipWallRV adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_vipwall,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tv_edit_vipwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.ll_unchoose_vipwall).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.ll_choose_vipwall).setVisibility(View.VISIBLE);

                //开始监听
            }
        });


        view.findViewById(R.id.chooseall_frag_vipwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //本地路径？
        Button down = view.findViewById(R.id.btn_down_frag_vipwall);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        //删除操作本地刷新  刷新adapter
        Button delete = view.findViewById(R.id.btn_delete_frag_vipwall);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //统计被选数据
            adapter.freshData();
            }
        });
        rv = view.findViewById(R.id.rv_frag_vipwall);
        GridLayoutManager manager=new GridLayoutManager(getContext(),3);
        rv.setLayoutManager(manager);
        //网络访问
        request();

    }

    private void request() {
        //网络访问
        OkGo.post(UrlCollect.recordOfGame)//
                .tag(this)//
                .params("userId", "27")
                .params("page", "0")
                .params("pageSize", "10")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("******************", "onSuccess: "+s);
                        VIPWallBean vipWallBean = new Gson().fromJson(s, VIPWallBean.class);
                        adapter = new AdapterVipWallRV(getContext(),vipWallBean.getData().getPageInfo().getList());
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    }
                });

    }
}

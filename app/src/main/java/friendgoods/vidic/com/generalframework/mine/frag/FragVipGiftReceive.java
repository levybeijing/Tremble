package friendgoods.vidic.com.generalframework.mine.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.TokenCheck;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterVipWallReceive;
import friendgoods.vidic.com.generalframework.bean.VIPWallBean;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class FragVipGiftReceive extends Fragment implements View.OnClickListener {


    private RecyclerView rv_top;
    private AdapterVipWallReceive adapter;
    private CheckBox box_all;
    private List<VIPWallBean.DataBean.PageInfoBean.ListBean> list;
    private LinearLayout ll_choose,ll_unchoose;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vipwallreceive,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_choose = view.findViewById(R.id.ll_choose_vipwall);
        ll_unchoose = view.findViewById(R.id.ll_unchoose_vipwalltop);
        //编辑
        view.findViewById(R.id.tv_edit_vipwalltop).setOnClickListener(this);
        //全选
        box_all = view.findViewById(R.id.rb_chooseall_frag_vipwallbelowl);
        //下载
        view.findViewById(R.id.btn_down_frag_vipwallbelow).setOnClickListener(this);
        //删除
        view.findViewById(R.id.btn_delete_frag_vipwallbelow).setOnClickListener(this);

        rv_top = view.findViewById(R.id.rv_frag_vipwalltop);
        GridLayoutManager manager=new GridLayoutManager(getContext(),3);
        rv_top.setLayoutManager(manager);
        adapter = new AdapterVipWallReceive(getContext());
        adapter.setHasStableIds(true);
        rv_top.setAdapter(adapter);

        //网络访问
        box_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //设置是否全选 如何设置所有
                adapter.setALL(isChecked);
            }
        });
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    //
                    Toast.makeText(getContext(), "setOnKeyListener", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }

    private void request() {
        //网络访问
        OkGo.post(UrlCollect.vipWallReceived)//
                .tag(this)//
                .params("userId", (int)SharedPFUtils.getParam(getContext(),"userId",0))
                .params("page", "1")
                .params("pageSize", "20")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        TokenCheck.toLogin(getActivity(),s);

                        VIPWallBean vipWallBean = new Gson().fromJson(s, VIPWallBean.class);
                        list = vipWallBean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_edit_vipwalltop:
                ll_choose.setVisibility(View.VISIBLE);
                ll_unchoose.setVisibility(View.INVISIBLE);
                //
                adapter.setCheckable(true);
                adapter.setData(list);
                break;
            case R.id.btn_down_frag_vipwallbelow:
                List<Boolean> Flag = adapter.getFlag();
                for (int i = 0; i < list.size(); i++) {
                    if (Flag.get(i)){
                        //网络下载

                    }
                }
                box_all.setChecked(false);
                adapter.setCheckable(false);
                adapter.setData(list);
                break;
            case R.id.btn_delete_frag_vipwallbelow:
                List<Boolean> listFlag = adapter.getFlag();
                for (int i = 0; i < list.size(); i++) {
                    if (listFlag.get(i)){
                        list.remove(i);
                    }
                }
                adapter.setData(list);
                box_all.setChecked(false);
                adapter.setCheckable(false);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (list!=null)
            list.clear();
    }
}

package friendgoods.vidic.com.generalframework.activity.fragment;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import friendgoods.vidic.com.generalframework.R;/** * Created by Administrator on 2016/10/8 0008. */public class Order extends Fragment {    private View view;    @Nullable    @Override    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order, null);        initView();        return view;    }    private void initView() {        view.findViewById(R.id.fragment_mine_lv);        view.findViewById(R.id.fragment_mine_icon);        view.findViewById(R.id.fragment_mine_username);    }    @Override    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);    }}
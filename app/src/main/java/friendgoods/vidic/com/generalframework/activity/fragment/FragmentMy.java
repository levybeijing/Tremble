package friendgoods.vidic.com.generalframework.activity.fragment;import android.os.Bundle;import android.support.annotation.Nullable;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import friendgoods.vidic.com.generalframework.R;/** * Created by Administrator on 2016/10/8 0008. */public class FragmentMy extends BaseFragment {    private View view;    @Override    public void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);    }    @Nullable    @Override    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine, null);        initView();        return view;    }    private void initView() {    }}
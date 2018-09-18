package friendgoods.vidic.com.generalframework.activity.fragment;import android.content.Intent;import android.content.res.TypedArray;import android.graphics.drawable.Drawable;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import com.google.gson.Gson;import com.lzy.okgo.OkGo;import com.lzy.okgo.callback.StringCallback;import com.squareup.picasso.Picasso;import friendgoods.vidic.com.generalframework.R;import friendgoods.vidic.com.generalframework.entity.UrlCollect;import friendgoods.vidic.com.generalframework.mine.adapter.AdapterOrder;import friendgoods.vidic.com.generalframework.mine.activity.MallActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyAddressesActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyFansActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyFriendsActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyGiftsActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyVIPWallActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyOrdersActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyRecordActivity;import friendgoods.vidic.com.generalframework.mine.OnItemClickListener;import friendgoods.vidic.com.generalframework.mine.activity.SettingsActivity;import friendgoods.vidic.com.generalframework.mine.activity.VIPGiftsWallActivity;import friendgoods.vidic.com.generalframework.mine.bean.UserInfoBean;import okhttp3.Call;import okhttp3.Response;/** * Created by Administrator on 2016/10/8 0008. */public class OrderFragment extends Fragment {    private TextView name;    private ImageView icon;    @Nullable    @Override    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order, null);    }    @Override    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        final RecyclerView rv = view.findViewById(R.id.fragment_mine_rv);        icon = view.findViewById(R.id.fragment_mine_icon);        name = view.findViewById(R.id.fragment_mine_username);        //个人用户名头像  (本碎片网络访问?        request();        view.findViewById(R.id.cv_top).setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                getContext().startActivity(new Intent(getContext(), MyRecordActivity.class));            }        });        //进行网络访问        String[] array = getContext().getResources().getStringArray(R.array.list_mine);        TypedArray typedArray = getResources().obtainTypedArray(R.array.icon_mine);        Drawable[] icons = new Drawable[array.length];        for (int i = 0; i < array.length; i++) {            Drawable drawable = typedArray.getDrawable(i);            icons[i] = drawable;        }        //要及时释放        typedArray.recycle();        AdapterOrder adapter=new AdapterOrder(getContext(),array,icons);        LinearLayoutManager manager=new LinearLayoutManager(getContext());        rv.setLayoutManager(manager);        rv.setAdapter(adapter);        adapter.setOnItemClickListener(new OnItemClickListener() {            @Override            public void onItemClick(int i) {                switch (i){                    case 0:                        getContext().startActivity(new Intent(getContext(), MallActivity.class));                        break;                    case 1:                        getContext().startActivity(new Intent(getContext(), MyFansActivity.class));                        break;                    case 2:                        getContext().startActivity(new Intent(getContext(), MyFriendsActivity.class));                        break;                    case 3:                        getContext().startActivity(new Intent(getContext(), MyGiftsActivity.class));                        break;                    case 4:                        //礼物墙                        getContext().startActivity(new Intent(getContext(), MyVIPWallActivity.class));                        break;                    case 5:                        //VIP礼物墙                        getContext().startActivity(new Intent(getContext(), VIPGiftsWallActivity.class));                        break;                    case 6:                        getContext().startActivity(new Intent(getContext(), MyOrdersActivity.class));                        break;                    case 7:                        getContext().startActivity(new Intent(getContext(), MyAddressesActivity.class));                        break;                    case 8:                        getContext().startActivity(new Intent(getContext(), SettingsActivity.class));                        break;                }            }        });    }    private void request() {        OkGo.post(UrlCollect.persenalDetail)//                .tag(this)//                .params("userId", "27")                .execute(new StringCallback() {                    @Override                    public void onSuccess(String s, Call call, Response response) {                        Log.e("OrderFragment", "onSuccess: "+s);                        UserInfoBean infoBean = new Gson().fromJson(s, UserInfoBean.class);                        name.setText(infoBean.getData().getName());                        Picasso.with(getContext()).load(infoBean.getData().getPhoto()).into(icon);                    }                    @Override                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {                    }        });    }}
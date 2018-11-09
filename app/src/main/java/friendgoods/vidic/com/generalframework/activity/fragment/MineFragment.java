package friendgoods.vidic.com.generalframework.activity.fragment;import android.content.Intent;import android.content.res.TypedArray;import android.graphics.Typeface;import android.graphics.drawable.Drawable;import android.os.Build;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import com.google.gson.Gson;import com.lzy.okgo.OkGo;import com.lzy.okgo.callback.StringCallback;import com.squareup.picasso.Picasso;import friendgoods.vidic.com.generalframework.MyApplication;import friendgoods.vidic.com.generalframework.R;import friendgoods.vidic.com.generalframework.activity.StoryModelActivity;import friendgoods.vidic.com.generalframework.entity.UrlCollect;import friendgoods.vidic.com.generalframework.mine.customview.CirImageView;import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerMine;import friendgoods.vidic.com.generalframework.mine.adapter.AdapterOrder;import friendgoods.vidic.com.generalframework.mine.activity.MallActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyAddressesActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyFansActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyFriendsActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyGiftsActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyPubWallActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyOrdersActivity;import friendgoods.vidic.com.generalframework.mine.activity.MyRecordActivity;import friendgoods.vidic.com.generalframework.mine.activity.SettingsActivity;import friendgoods.vidic.com.generalframework.mine.activity.VIPGiftsWallActivity;import friendgoods.vidic.com.generalframework.bean.UserInfoBean;import friendgoods.vidic.com.generalframework.util.SharedPFUtils;import okhttp3.Call;import okhttp3.Response;/** * Created by Administrator on 2016/10/8 0008. */public class MineFragment extends Fragment {    private TextView name;    private CirImageView icon;    @Nullable    @Override    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order, null);    }    @Override    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        final RecyclerView rv = view.findViewById(R.id.fragment_mine_rv);        icon = view.findViewById(R.id.fragment_mine_icon);        name = view.findViewById(R.id.fragment_mine_username);        name.setText((String) SharedPFUtils.getParam(getContext(), "name", ""));        Picasso.with(getContext()).load((String) SharedPFUtils.getParam(getContext(), "icon", "")).into(icon);        Typeface font = null;        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {            font = getResources().getFont(R.font.kuaileti);            name.setTypeface(font);        }        view.findViewById(R.id.cv_top).setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                getContext().startActivity(new Intent(getContext(), MyRecordActivity.class));            }        });        //        String[] array = getContext().getResources().getStringArray(R.array.list_mine);        TypedArray typedArray = getResources().obtainTypedArray(R.array.icon_mine);        Drawable[] icons = new Drawable[array.length];        for (int i = 0; i < array.length; i++) {            Drawable drawable = typedArray.getDrawable(i);            icons[i] = drawable;        }        //释放        typedArray.recycle();        AdapterOrder adapter=new AdapterOrder(getContext(),array,icons);        LinearLayoutManager manager=new LinearLayoutManager(getContext());        rv.setLayoutManager(manager);        rv.setAdapter(adapter);        adapter.setOnItemClickListener(new OnItemClickListenerMine() {            @Override            public void onItemClick(int i) {                switch (i){                    case 0:                        getContext().startActivity(new Intent(getContext(), MallActivity.class));                        break;                    case 1:                        getContext().startActivity(new Intent(getContext(), MyFansActivity.class));                        break;                    case 2:                        getContext().startActivity(new Intent(getContext(), MyFriendsActivity.class));                        break;                    case 3:                        getContext().startActivity(new Intent(getContext(), MyGiftsActivity.class));                        break;                    case 4:                        //礼物墙                        getContext().startActivity(new Intent(getContext(), MyPubWallActivity.class));                        break;                    case 5:                        //VIP礼物墙                        getContext().startActivity(new Intent(getContext(), VIPGiftsWallActivity.class));                        break;                    case 6:                        getContext().startActivity(new Intent(getContext(), MyOrdersActivity.class));                        break;                    case 7:                        getContext().startActivity(new Intent(getContext(), MyAddressesActivity.class));                        break;                    case 8:                        getContext().startActivity(new Intent(getContext(), SettingsActivity.class));                        break;                }            }        });    }}
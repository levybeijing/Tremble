package friendgoods.vidic.com.generalframework._idle;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.base.LazyFragment;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class DouFootFragment extends LazyFragment {
    private int number=235;
    private int time;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_doufoot, null);

//        tokenData = SharedPFUtils.getData(getApplication(), "data");
//
//        mMainRecycler = (XListView) view.findViewById(R.id.main_recycler);
//        mNullData = (ImageView) view.findViewById(R.id.null_data);
//
//
//        mMainRecycler.setPullRefreshEnable(true);
//        mMainRecycler.setPullLoadEnable(true);
//        mMainRecycler.setXListViewListener(this);
//        ImageView iv = view.findViewById(R.id.iv_hand_douhand);
        final TextView tv_number = view.findViewById(R.id.tv_number_doufoot);
        tv_number.setText(number+"");
        TextView tv_time = view.findViewById(R.id.tv_time_doufoot);
//        字体设置
        Typeface font=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.edo);
            tv_number.setTypeface(font);
        }
//        缩放动画
        final ScaleAnimation animation = new ScaleAnimation(
                1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        );
        animation.setDuration(100);
//        蓝牙监听  不停地更新数据 注册广播接收器
//                tv_number.startAnimation(animation);
//                tv_number.setText(++number+"");
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

}
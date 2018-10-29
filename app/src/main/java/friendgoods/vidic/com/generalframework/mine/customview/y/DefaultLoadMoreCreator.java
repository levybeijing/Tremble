package friendgoods.vidic.com.generalframework.mine.customview.y;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import friendgoods.vidic.com.generalframework.R;

public class DefaultLoadMoreCreator extends LoadViewCreator{

    private View mRefreshIv;
    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        //设置位置
        View refreshView = LayoutInflater.from(context).inflate(R.layout.testitem, parent, false);
        mRefreshIv = refreshView.findViewById(R.id.iv_test);
        int bottom = parent.getBottom();

        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int loadViewHeight, int currentLoadStatus) {

        float rotate = ((float) currentDragHeight) / loadViewHeight;
        // 不断上拉的过程中不断的旋转图片
        mRefreshIv.setRotation(rotate * 360);
    }

    @Override
    public void onLoading() {
        // 刷新的时候不断旋转
        RotateAnimation animation = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(1000);
        mRefreshIv.startAnimation(animation);
    }

    @Override
    public void onStopLoad() {
        // 停止加载的时候清除动画
        mRefreshIv.setRotation(0);
        mRefreshIv.clearAnimation();
    }
}
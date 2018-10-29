package friendgoods.vidic.com.generalframework.mine.customview.y;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LoadRefreshRecyclerView extends RefreshRecyclerView {
    // 上拉加载更多的辅助类
    private LoadViewCreator mLoadCreator;
    // 上拉加载更多头部的高度
    private int mLoadViewHeight = 0;
    // 上拉加载更多的头部View
    private View mLoadView;
    // 手指按下的Y位置
    private int mFingerDownY;
    // 手指拖拽的阻力指数
    private float mDragIndex = 0.35f;
    // 当前是否正在拖动
    private boolean mCurrentDrag = false;
    // 当前的状态
    private int mCurrentLoadStatus;
    // 默认状态
    public int LOAD_STATUS_NORMAL = 0x0011;
    // 上拉加载更多状态
    public static int LOAD_STATUS_PULL_DOWN_REFRESH = 0x0022;
    // 松开加载更多状态
    public static int LOAD_STATUS_READYLOADING = 0x0033;
    // 正在加载更多状态
    public int LOAD_STATUS_LOADING = 0x0044;

    public LoadRefreshRecyclerView(Context context) {
        super(context);
    }

    public LoadRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // 先处理上拉加载更多，同时考虑加载列表的不同风格样式，确保这个项目还是下一个项目都能用
    // 所以我们不能直接添加View，需要利用辅助类
    public void addLoadViewCreator(LoadViewCreator loadCreator) {
        this.mLoadCreator = loadCreator;
        addRefreshView();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addRefreshView();
    }

    /**
     * 从加载器中提取view
     */
    private void addRefreshView() {
        Adapter adapter = getAdapter();
        if (adapter != null && mLoadCreator != null) {
            // 添加底部加载更多View
            View loadView = mLoadCreator.getLoadView(getContext(), this);
            if (loadView != null) {
//                TODO：同过增加底部布局的方式引入  造成BUG 个性化设置loadview  或者重新定义 addfooterView 方法
//                addFooterView(loadView);
                MarginLayoutParams params = (MarginLayoutParams) mLoadView.getLayoutParams();
                params.bottomMargin = getBottom()+loadView.getHeight();
                loadView.setLayoutParams(params);
                this.mLoadView = loadView;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                // 之所以写在dispatchTouchEvent那是因为如果我们处理了条目点击事件，
                // 那么就不会进入onTouchEvent里面，所以只能在这里获取
                mFingerDownY = (int) ev.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                if (mCurrentDrag) {
//                    手指抬起
                    restoreLoadView();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     *重置当前刷新状态
     */
    private void restoreLoadView() {
//       获取布局bottom位置
        int currentBottomMargin = ((MarginLayoutParams) mLoadView.getLayoutParams()).bottomMargin;
//        int currentBottomMargin = this.getBottom();
//        ?意义?
        int finalBottomMargin = 0;
        if (mCurrentLoadStatus == LOAD_STATUS_READYLOADING) {

            mCurrentLoadStatus = LOAD_STATUS_LOADING;
            if (mLoadCreator != null) {
//                一般是动画开始
                mLoadCreator.onLoading();
            }
            if (mListener != null) {
//                监听器  传出数据
                mListener.onLoad();
            }
        }

        int distance = currentBottomMargin - finalBottomMargin;

        // 回弹到指定位置
        ValueAnimator animator = ObjectAnimator.ofFloat(currentBottomMargin, finalBottomMargin).setDuration(distance);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentTopMargin = (float) animation.getAnimatedValue();
                setLoadViewMarginBottom((int) currentTopMargin);
            }
        });
        animator.start();
        mCurrentDrag = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // 如果是在最底部才处理，否则不需要处理
                if (canScrollDown() || mCurrentLoadStatus == LOAD_STATUS_LOADING) {
                    // 如果没有到达最顶端，也就是说还可以向上滚动就什么都不处理
                    return super.onTouchEvent(e);
                }
                //
                if (mLoadCreator != null) {
                    mLoadViewHeight = mLoadView.getMeasuredHeight();
                }
                // 如果实在拖拽  滚动到底部位置
                if (mCurrentDrag) {
                    scrollToPosition(getAdapter().getItemCount() - 1);
                }

                // 获取手指触摸拖拽的距离   getRaw获取点击事件与屏幕距离
                int distanceY = (int) ((e.getRawY() - mFingerDownY)* mDragIndex);
                // 如果是已经到达头部，并且不断的向下拉，那么不断的改变refreshView的marginTop的值
                if (distanceY < 0) {
//                    确定位置
                    setLoadViewMarginBottom(-distanceY);
//                    更新状态
                    updateLoadStatus(-distanceY);
                    mCurrentDrag = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                //重置动画
                break;
        }

        return super.onTouchEvent(e);
    }

    /**
     * 根据距离改变加载的状态
     */
    private void updateLoadStatus(int distanceY) {
        if (distanceY <= 0) {
//            反向移动 无变化
            mCurrentLoadStatus = LOAD_STATUS_NORMAL;
//           超过这一值  将加载下一条目
        } else if (distanceY < mLoadViewHeight) {
            mCurrentLoadStatus = LOAD_STATUS_PULL_DOWN_REFRESH;
        } else {
            mCurrentLoadStatus = LOAD_STATUS_READYLOADING;
        }

        if (mLoadCreator != null) {
            //传入参数
            // 1-相对初始 实时移动的间距值 负值为上滑
            // 2-控件高度
            // 3- 当前加载状态  显示不同视图
            mLoadCreator.onPull(distanceY, mLoadViewHeight, mCurrentLoadStatus);
        }
    }

    /**
     * 设置加载View的marginBottom
     */
    public void setLoadViewMarginBottom(int marginBottom) {
        MarginLayoutParams params = (MarginLayoutParams) mLoadView.getLayoutParams();
        if (marginBottom < getBottom()-mLoadViewHeight) {
            marginBottom = getBottom()-mLoadViewHeight;
        }
        params.bottomMargin = marginBottom;
        mLoadView.setLayoutParams(params);
    }

    /**
     * 停止加载更多
     */
    public void onStopLoad() {
        mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        restoreLoadView();
        if (mLoadCreator != null) {
            mLoadCreator.onStopLoad();
        }
    }
    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     * 判断是不是滚动到了最顶部，这个是从SwipeRefreshLayout里面copy过来的源代码
     */
    public boolean canScrollDown() {
        return ViewCompat.canScrollVertically(this, 1);
    }


    // 处理加载更多回调监听
    private OnLoadMoreListener mListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoad();
    }
}

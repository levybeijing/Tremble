package friendgoods.vidic.com.generalframework.mine.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MoveImageView extends android.support.v7.widget.AppCompatImageView {
    private int parentLeft=0;
    private int parentTop=0;
    private int parentRight=0;
    private int parentBottom=0;

    public MoveImageView(Context context) {
        super(context);
    }

    public MoveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MoveImageView(Context context,int a,int b,int c,int d) {
        super(context);
        parentLeft=a;
        parentTop=b;
        parentRight=c;
        parentBottom=d;
    }
    public int lastX = 0;
    public int lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) this.getLayoutParams();
        int dx,dy,top,left,right,bottom;
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //手指原始坐标
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                    //偏移量
                    dx = (int) event.getRawX() - lastX;
                    dy = (int) event.getRawY() - lastY;

                    left = getLeft() + dx;
                    top = getTop() + dy;
                    right = getRight() + dx;
                    bottom = getBottom() + dy;

                    if (left < 0) {
                        left = 0;
                        right = getWidth();
                    }
                    if (right > parentRight - parentLeft) {
                        right = parentRight - parentLeft;
                        left = right - getWidth();
                    }
                    if (top < 0) {
                        top = 0;
                        bottom = getHeight();
                    }
                    if (bottom > parentBottom - parentTop) {
                        bottom = parentBottom - parentTop;
                        top = bottom - getHeight();
                    }
                    lp.setMargins(left, top, right, bottom);
                    //确定位置
                    this.setLayoutParams(lp);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        invalidate();
        return true;
    }
}

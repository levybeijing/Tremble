package friendgoods.vidic.com.generalframework.mine.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
        RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) this.getLayoutParams();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //手指原始坐标
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //偏移量
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;
                //控件位置
                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;

                if(left < 0){
                    left = 0;
                    right = left + getWidth();
                }
                if(right > parentRight-parentLeft){
                    right = parentRight-parentLeft;
                    left = right - getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + getHeight();
                }
                if(bottom > parentBottom-parentTop){
                    bottom = parentBottom-parentTop;
                    top = bottom - getHeight();
                }
                layout(left, top, right, bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                //点击事件的坐标
//                Log.e("#########Move", "onTouchEvent: "+getBottom());
//                Log.e("#########Move", "onTouchEvent: "+getRight());
                //记录当前位置  拖曳过程中 会变小!?
                lp.setMargins(getLeft(),getTop(),getLeft()+getWidth(),getTop()+getHeight());
                this.setLayoutParams(lp);
                break;
            case MotionEvent.ACTION_UP:


                break;
            default:
                break;
        }
        return true;
    }
}
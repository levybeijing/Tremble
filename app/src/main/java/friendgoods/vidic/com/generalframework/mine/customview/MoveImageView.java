package friendgoods.vidic.com.generalframework.mine.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MoveImageView extends android.support.v7.widget.AppCompatImageView {
    public MoveImageView(Context context) {
        super(context);
    }

    public MoveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MoveImageView(Context context,int a,int b) {
        super(context);
        screenWidth=a;
        screenHeight=b;
    }
    private int lastX = 0;
    private int lastY = 0;

    private static int screenWidth; //屏幕宽度
    private static int screenHeight; //屏幕高度


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
                if(left < 0){
                    left = 0;
                    right = left + getWidth();
                }
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + getHeight();
                }
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }
                layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }
}

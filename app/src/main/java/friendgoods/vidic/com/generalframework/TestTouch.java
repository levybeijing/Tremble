package friendgoods.vidic.com.generalframework;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TestTouch extends android.support.v7.widget.AppCompatImageView {
    public TestTouch(Context context) {
        super(context);
    }

    public TestTouch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTouch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private ITest itest;

    public void setOnTest(ITest itest_){
        itest=itest_;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                itest.test();
                break;
        }
        invalidate();
        return true;
    }
}

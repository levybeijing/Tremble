package friendgoods.vidic.com.generalframework.util;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class SendFragerPager extends TabLayout {
    public SendFragerPager(Context context) {
        super(context);
    }

    public SendFragerPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SendFragerPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}

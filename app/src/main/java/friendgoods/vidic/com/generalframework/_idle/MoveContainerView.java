package friendgoods.vidic.com.generalframework._idle;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MoveContainerView extends RelativeLayout {
    public MoveContainerView(Context context) {
        super(context);
    }

    public MoveContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}

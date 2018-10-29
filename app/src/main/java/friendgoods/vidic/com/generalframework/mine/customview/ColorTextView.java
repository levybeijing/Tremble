package friendgoods.vidic.com.generalframework.mine.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

public class ColorTextView extends android.support.v7.widget.AppCompatTextView {
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mViewWidth = 0;
    private Rect mTextBound = new Rect();
    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //缺陷:影响属性gravity的使用
    @Override
    protected void onDraw(Canvas canvas) {
        mViewWidth = getMeasuredWidth();
        mPaint = getPaint();
        String mTipText = getText().toString();

        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);

        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                new int[] {  0xFFecd13b, 0xFF46a1f5 },
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        //以控件为基准  居中显示
        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 +   mTextBound.height()/2, mPaint);
//        canvas.drawText(mTipText, 0, 0, mPaint);
//        int gravity = getGravity();

    }
}

package friendgoods.vidic.com.generalframework.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Transformation;
import friendgoods.vidic.com.generalframework.MyApplication;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class UiUtils {

    public static Context getContext() {
        return MyApplication.getApplication();
    }
    /** dip转换px */
    public static int dip2px(int dip) {
        return dip;
    }

    /** pxz转换dip */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /** 获取资源 */
    public static Resources getResources() {
        System.out.println(".......haha" + getContext() == null ? true : false);
        return getContext().getResources();
    }

    /** 获取文字 */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /** 获取文字数组 */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /** 获取dimen */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /** 获取drawable */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /** 获取颜色 */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /** 获取颜色选择器 */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    public static Transformation getView(final ImageView iv) {
        Transformation transformation=new Transformation() {

            @Override
            public Bitmap transform (Bitmap source){

                int targetWidth = iv.getWidth();

                if (source.getWidth() == 0) {
                    return source;
                }

                //如果图片小于设置的宽度，则返回原图
                if (source.getWidth() < targetWidth) {
                    return source;
                } else {
                    //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    if (targetHeight != 0 && targetWidth != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                        if (result != source) {
                            // Same bitmap is returned if sizes are the same
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }

            }

            @Override
            public String key () {
                return "transformation" + " desiredWidth";
            }
        };
        return transformation;
    }

    public static String getMoney(String s) {

        if("".equals(s)) return "";
        if("".equals(s)){
            return "";
        }
        double money = Double.valueOf(s).doubleValue();

        String newMoney = money / 10000 + "";

        int index = newMoney.indexOf(".");

        String decimal = newMoney.substring(index, newMoney.length());      //获取点后面的数

        if (Double.parseDouble(decimal) > 0) {                              //判断点后面的数是否大于0
            return newMoney;

        } else {
            return newMoney.substring(0, newMoney.indexOf("."));
        }
    }
}

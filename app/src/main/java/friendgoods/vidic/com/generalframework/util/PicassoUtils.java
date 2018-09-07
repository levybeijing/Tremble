package friendgoods.vidic.com.generalframework.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;

import friendgoods.vidic.com.generalframework.R;

import static friendgoods.vidic.com.generalframework.MyApplication.getApplication;

public class PicassoUtils {

    public static void setIntImage(int draw, ImageView imageView){
        Picasso.with(getApplication())
                .load(draw)
                //占位图，图片加载出来之前显示的默认图片
                .placeholder(R.mipmap.my_normal)
                //错误图，图片加载出错时显示的图片
                .error(R.mipmap.my_normal)
                .config(Bitmap.Config.RGB_565)
                .transform(UiUtils.getView(imageView))
                .into(imageView);
    }

    public static void setUrlImage(String draw, ImageView imageView){
        Picasso.with(getApplication())
                .load(draw)
                //占位图，图片加载出来之前显示的默认图片
                .placeholder(R.mipmap.my_normal)
                //错误图，图片加载出错时显示的图片
                .error(R.mipmap.my_normal)
                .config(Bitmap.Config.RGB_565)
                .transform(UiUtils.getView(imageView))
                .into(imageView);
    }

}

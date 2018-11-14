package friendgoods.vidic.com.generalframework._idle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.bean.DetailGoodsBean;
import friendgoods.vidic.com.generalframework.mine.activity.CommitOrderActivity;
import friendgoods.vidic.com.generalframework.mine.activity.MallActivity;

public class CustomDialogGift extends PopupWindow{

    private View dialogView;
    private ImageView iv_net,iv_mall;
    private TextView tv_recerive;
    public CustomDialogGift(final Activity context, String s) {
        super(context);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogView = inflater.inflate(R.layout.dialog_challgift, null);

//        this.setContentView(dialogView);
//        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.DialogShowStyle); //设置SelectPicPopupWindow弹出窗体动画效果
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);

        tv_recerive.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = dialogView.findViewById(R.id.iv_next_detailgoods).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) { //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}

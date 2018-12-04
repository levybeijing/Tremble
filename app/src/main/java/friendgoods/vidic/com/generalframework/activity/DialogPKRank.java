package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.bean.PKRecordBean;
import friendgoods.vidic.com.generalframework.bean.DetailGoodsBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.mine.activity.CommitOrderActivity;
import friendgoods.vidic.com.generalframework.mine.customview.CirImageView;
import okhttp3.Call;
import okhttp3.Response;

public class DialogPKRank extends PopupWindow{

    private ImageView char_one;
    private ImageView char_two;
    private ImageView char_three;
    private CirImageView icon_one;
    private CirImageView icon_two;
    private CirImageView icon_three;
    private TextView name_one;
    private TextView name_two;
    private TextView name_three;
    private View dialogView;

    public DialogPKRank(final Activity context, int roomId ,int degree) {
        super(context);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogView = inflater.inflate(R.layout.activity_pkrank, null);
        //加载背景图片
        ImageView iv_bg = dialogView.findViewById(R.id.iv_bg_pkrank);
        Glide.with(context).asGif().load(R.drawable.fireworks).into(iv_bg);
//退出按键
        ImageView iv_exit =dialogView. findViewById(R.id.iv_exit_pkrank);
        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //    设置Title的图标
                builder.setTitle("游戏结束");
                builder.setMessage("再来一局?");
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        intent.setAction("action.PKAGAIN.OK");
                        context.sendBroadcast(intent);
                        dismiss();
                    }
                });
                builder.setNegativeButton("退出游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        intent.setAction("action.PKAGAIN.NO");
                        context.sendBroadcast(intent);
                        dismiss();
                    }
                });
                builder.show();
            }
        });
//请求排名
        char_one =dialogView. findViewById(R.id.char_one_pkrank);
        char_two = dialogView. findViewById(R.id.char_two_pkrank);
        char_three = dialogView. findViewById(R.id.char_three_pkrank);
        icon_one = dialogView. findViewById(R.id.icon_one_pkrank);
        icon_two = dialogView. findViewById(R.id.icon_two_pkrank);
        icon_three = dialogView. findViewById(R.id.icon_three_pkrank);
        name_one = dialogView. findViewById(R.id.name_one_pkrank);
        name_two = dialogView. findViewById(R.id.name_two_pkrank);
        name_three = dialogView. findViewById(R.id.name_three_pkrank);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OkGo.post(UrlCollect.getUserPKRanking)//
                .tag(this)//
                .params("roomId", roomId)
                .params("degree", degree)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("================", "排名数据: "+s);
                        PKRecordBean pkRecordBean = new Gson().fromJson(s, PKRecordBean.class);
                        List<PKRecordBean.DataBean> data = pkRecordBean.getData();
                        if (data==null||data.size()==0){
                            return;
                        }
                        name_one.setText(data.get(0).getShakeNum()+"");
                        Picasso.with(context).load(data.get(0).getPhoto()).into(icon_one);
                        Picasso.with(context).load(UrlCollect.baseIamgeUrl+data.get(0).getLogo()).into(char_one);
                        if (data.size()<=1){
                            return;
                        }
                        name_two.setText(data.get(1).getShakeNum()+"");
                        Picasso.with(context).load(data.get(1).getPhoto()).into(icon_two);
                        Picasso.with(context).load(UrlCollect.baseIamgeUrl+data.get(1).getLogo()).into(char_two);
                        if (data.size()<=2){
                            return;
                        }
                        name_three.setText(data.get(2).getShakeNum()+"");
                        Picasso.with(context).load(data.get(2).getPhoto()).into(icon_three);
                        Picasso.with(context).load(UrlCollect.baseIamgeUrl+data.get(2).getLogo()).into(char_three);
                    }
                });
        this.setContentView(dialogView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.DialogShowStyle); //设置SelectPicPopupWindow弹出窗体动画效果
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);

        dialogView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
//                int height = dialogView.findViewById(R.id.iv_next_detailgoods).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) { //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//                        dismiss();
//                    }
//                }
                return true;
            }
        });
    }

//    }
}

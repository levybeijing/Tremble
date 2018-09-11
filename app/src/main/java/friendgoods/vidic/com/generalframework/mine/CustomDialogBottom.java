package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.content.Context;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import friendgoods.vidic.com.generalframework.R;

public class CustomDialogBottom extends PopupWindow{

    private View dialogView;
    private EditText et_number;
    private ImageView goodsicon,add,reduce,close,next;
    private TextView name,price;

    public CustomDialogBottom(final Activity context, final BottomDialogOnclickListener bottomDialogOnclickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //
        dialogView = inflater.inflate(R.layout.customdialog, null);
        //商品图片
        goodsicon = dialogView.findViewById(R.id.iv_goods_dialog);
        //名称价格
        name = dialogView.findViewById(R.id.tv_name_detailgoods);
        price = dialogView.findViewById(R.id.tv_price_dialog);
        //数量操作及关闭
        et_number = dialogView.findViewById(R.id.et_number_dialog);
        add = dialogView.findViewById(R.id.iv_add_dialog);
        reduce = dialogView.findViewById(R.id.iv_reduce_dialog);
        close = dialogView.findViewById(R.id.iv_close_dialog);
        //下一步
        next = dialogView.findViewById(R.id.iv_next_detailgoods);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aim=et_number.getText().toString().trim();
                String reg="^[0-9]*$";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(aim);
                int i=-1;
                if (matcher.matches()){
                    i= Integer.parseInt(aim);
                    i--;
                }
                if (i>=0){
                    et_number.setText(i+"");
                }else{
                    Toast.makeText(context, "输入不合法", Toast.LENGTH_SHORT).show();
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aim=et_number.getText().toString().trim();
                String reg="^[0-9]*$";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(aim);
                int i=-1;
                if (matcher.matches()){
                    i= Integer.parseInt(aim);
                    i++;
                }
                if (i>=0){
                    et_number.setText(i+"");
                }else{
                    Toast.makeText(context, "输入不合法", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = et_number.getText().toString().trim();
                String reg="^[0-9]*$";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(number);
                if (matcher.matches()){
                    Toast.makeText(context, "NEXT", Toast.LENGTH_SHORT).show();
//                    bottomDialogOnclickListener.onPositiveClick("", CustomDialogBottom.this);
                }
            }
        });


        this.setContentView(dialogView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.DialogShowStyle); //设置SelectPicPopupWindow弹出窗体动画效果
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);

//        dialogView.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                int height = dialogView.findViewById(R.id.pop_layout).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) { //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
////                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });
    }
}

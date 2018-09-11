package friendgoods.vidic.com.generalframework.mine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import friendgoods.vidic.com.generalframework.R;

public class CustomDialogBottom extends PopupWindow{

    private View dialogView;
    private EditText et_number;
    private Button cancelBtn, confirmBtn;
    private ImageView goodsicon,add,reduce,close,next;
    private TextView name,price;

    public CustomDialogBottom(Activity context, final BottomDialogOnclickListener bottomDialogOnclickListener) {
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
//                et_number.setText();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                et_number.setText();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialogOnclickListener.onPositiveClick("", CustomDialogBottom.this);
            }
        });
    }
}

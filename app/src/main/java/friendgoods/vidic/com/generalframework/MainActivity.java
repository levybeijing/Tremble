package friendgoods.vidic.com.generalframework;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Timer;
import java.util.TimerTask;
import friendgoods.vidic.com.generalframework.activity.fragment.ClassificationHome;
import friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy;
import friendgoods.vidic.com.generalframework.activity.fragment.Order;
import friendgoods.vidic.com.generalframework.activity.fragment.ShoppingCat;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ClassificationHome classificationHome;
    private ShoppingCat shoppingCat;
    private Order order;
    private RadioGroup radioGroup;
    private RadioButton home, classify, service, mine;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        switchContent(new ShoppingCat());

        classificationHome = new ClassificationHome();
        order = new Order();
        shoppingCat = new ShoppingCat();

        radioGroup = findViewById(R.id.radio_group);
        home = findViewById(R.id.home_radio);
        classify = findViewById(R.id.classify_radio);
        classify.setChecked(true);
        service = findViewById(R.id.service_radio);
    }

    @Override
    public void onClick(View v) {
        AnimationSet set = new AnimationSet(true);
        switch (v.getId()) {
            case R.id.home_radio:
                switchContent(classificationHome);
                break;
            case R.id.classify_radio:
                switchContent(shoppingCat);
                break;
            case R.id.service_radio:
                switchContent(order);
                break;
        }
    }


    private Fragment mContent;
    private boolean status = true;
    private boolean secondStatus = true;

    public void switchContent(Fragment to) {
        if (status) {
            status = false;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mContent = new ShoppingCat();
            transaction.add(R.id.frame_layout, to).commit();
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (secondStatus) {
                transaction.hide(new ShoppingCat()).add(R.id.frame_layout, to).commit();
                secondStatus = false;
            } else {
                if (mContent != to) {
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (!to.isAdded()) { // 先判断是否被add过
                        transaction.hide(mContent).add(R.id.frame_layout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
                    } else {
                        transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
                    }
                    mContent = to;
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtils.shortToast("再按一次退出程序");
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);//延时两秒，如果超出则擦错第一次按键记录
        } else

        {
            this.finish();
            System.exit(0);
        }

    }
}

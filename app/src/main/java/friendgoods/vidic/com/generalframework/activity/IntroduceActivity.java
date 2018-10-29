package friendgoods.vidic.com.generalframework.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.IntroduceVPAdapter;

public class IntroduceActivity extends AppCompatActivity {
    private int number=0;
    private ViewPager vp;
    private IntroduceVPAdapter adapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==555&&list!=null){
                vp.setCurrentItem(number);
            }
        }
    };
    private List<Integer> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        list = new ArrayList();
        list.add(R.mipmap.introduce_one_3x);
        list.add(R.mipmap.introduce_two_3x);
        list.add(R.mipmap.introduce_three_3x);
        list.add(R.mipmap.introduce_four_3x);
        list.add(R.mipmap.introduce_five_3x);

        vp = findViewById(R.id.vp_introduce);
        adapter = new IntroduceVPAdapter(this, list);
        vp.setAdapter(adapter);
//        实现逻辑:考虑到轮播中用户滑动
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(number<5){
                        Thread.sleep(2000);
                        Message message=Message.obtain();
                        message.what=555;
                        number=vp.getCurrentItem()+1;
                        handler.sendMessage(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

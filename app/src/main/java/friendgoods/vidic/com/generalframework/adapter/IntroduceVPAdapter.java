package friendgoods.vidic.com.generalframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.MainActivity;
import friendgoods.vidic.com.generalframework.activity.SexChooseActivity;

public class IntroduceVPAdapter extends PagerAdapter {

    private Context mContext;
    private List<Integer> mData;

    public IntroduceVPAdapter(Context context , List<Integer> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = View.inflate(mContext, R.layout.item_vp_introduce,null);
        ImageView iv = view.findViewById(R.id.iv_item_vp_introduce);
        iv.setImageDrawable(mContext.getResources().getDrawable(mData.get(position)));
        if (position==4){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    判断是否有你性别 形象  然后跳转页面
                    SharedPreferences userinfo = mContext.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    int sex = userinfo.getInt("sex", 0);
                    if (sex==0){
                        mContext.startActivity(new Intent(mContext,SexChooseActivity.class));
                        ((AppCompatActivity)mContext).finish();
                    }else{
                        mContext.startActivity(new Intent(mContext,MainActivity.class));
                        ((AppCompatActivity)mContext).finish();
                    }
                }
            });
        }
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}

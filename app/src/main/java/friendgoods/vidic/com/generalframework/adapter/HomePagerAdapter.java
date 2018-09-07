package friendgoods.vidic.com.generalframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_Title;                              //tab上的名字
    public HomePagerAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_title) {
        super(fm);
        this.list_Title=list_title;
        this.list_fragment=list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
       return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position % list_Title.size());
    }
}

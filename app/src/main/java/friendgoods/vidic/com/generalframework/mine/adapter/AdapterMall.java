package friendgoods.vidic.com.generalframework.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AdapterMall extends FragmentPagerAdapter{

    private List<Fragment> mfragmentList;

    public AdapterMall(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mfragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}

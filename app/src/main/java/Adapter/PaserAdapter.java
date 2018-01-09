package Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vivek.android2.gullu.R;

import Fragments.Fragment1;
import Fragments.Fragment2;
import Fragments.Fragment3;

/**
 * Created by android2 on 30/4/16.
 */
public class PaserAdapter extends FragmentPagerAdapter {
    Activity activity;

    public PaserAdapter(FragmentManager fragmentManager, Activity activity) {
        super(fragmentManager);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return activity.getString(R.string.beverage);
            case 1:
                return activity.getString(R.string.cool_food);
            case 2:
                return activity.getString(R.string.hot_food);
        }
        return null;
    }
}

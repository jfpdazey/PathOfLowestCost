package com.jfpdazey.pathoflowestcost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PathOptionsPagerAdapter extends FragmentPagerAdapter {

    public PathOptionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return new PathOfLowestCostFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Example Grids";
    }
}

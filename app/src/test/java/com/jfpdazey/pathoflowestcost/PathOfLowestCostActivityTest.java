package com.jfpdazey.pathoflowestcost;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOfLowestCostActivityTest {

    private PathOfLowestCostActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
    }

    @Test
    public void viewPagerIsConfiguredWithAPathOptionsPagerAdapter() {
        PagerAdapter pagerAdapter = ((ViewPager) activity.findViewById(R.id.container)).getAdapter();
        assertThat(pagerAdapter, instanceOf(PathOptionsPagerAdapter.class));
    }

    @Test
    public void tabLayoutIsConfiguredAndStartsAtTheZerothTab() {
        TabLayout tabLayout = ((TabLayout) activity.findViewById(R.id.tabs));
        assertThat(tabLayout.getSelectedTabPosition(), equalTo(0));
    }
}
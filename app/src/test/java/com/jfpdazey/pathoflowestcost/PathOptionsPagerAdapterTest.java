package com.jfpdazey.pathoflowestcost;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOptionsPagerAdapterTest {

    private PathOptionsPagerAdapter pagerAdapter;

    @Before
    public void setUp() {
        FragmentActivity fragmentActivity = Robolectric.setupActivity(FragmentActivity.class);
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        pagerAdapter = new PathOptionsPagerAdapter(fragmentManager);
    }

    @Test
    public void adapterHasOneItem() {
        assertThat(pagerAdapter.getCount(), equalTo(1));
    }

    @Test
    public void firstItemOfTheAdapterIsTheLowestCostFragment() {
        assertThat(pagerAdapter.getItem(0), instanceOf(PathOfLowestCostFragment.class));
    }

    @Test
    public void pageTitleOfTheFirstItemIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(0).toString(), equalTo("Example Grids"));
    }

}
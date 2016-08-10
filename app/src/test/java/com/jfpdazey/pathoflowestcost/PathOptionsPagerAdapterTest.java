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
import static org.hamcrest.CoreMatchers.nullValue;
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
    public void adapterHasTwoItems() {
        assertThat(pagerAdapter.getCount(), equalTo(2));
    }

    @Test
    public void firstItemOfTheAdapterIsTheExamplesFragment() {
        assertThat(pagerAdapter.getItem(0), instanceOf(PathOfLowestCostExamplesFragment.class));
    }

    @Test
    public void secondItemOfTheAdapterIsTheCustomFragment() {
        assertThat(pagerAdapter.getItem(1), instanceOf(PathOfLowestCostCustomFragment.class));
    }

    @Test
    public void otherItemsOfTheAdapterAreNull() {
        assertThat(pagerAdapter.getItem(2), nullValue());
    }

    @Test
    public void pageTitleOfTheFirstItemIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(0).toString(), equalTo("Example Grids"));
    }

    @Test
    public void pageTitleOfTheSecondItemIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(1).toString(), equalTo("Custom Grid"));
    }

    @Test
    public void pageTitleOfOtherItemsIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(2), nullValue());
    }
}
package com.jfpdazey.pathoflowestcost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOfLowestCostActivityTest {

    private PathOfLowestCostActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
    }

    @Test
    public void testOfNoConsequence() {
        // Keeping this here long enough to decide if we need Activity tests for next feature to be added.
        assertTrue(true);
    }
}
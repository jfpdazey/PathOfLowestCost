package com.jfpdazey.pathoflowestcost;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOfLowestCostActivityTest {

    @Test
    public void firstMainActivityTest() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        activity.findViewById(R.id.go_button).performClick();
    }

}
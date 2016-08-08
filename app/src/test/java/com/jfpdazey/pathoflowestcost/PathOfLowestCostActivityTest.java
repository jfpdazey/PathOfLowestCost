package com.jfpdazey.pathoflowestcost;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOfLowestCostActivityTest {

    @Test
    public void clickingExampleOneButtonLoadsExampleGridOne() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        String expectedContents = PathOfLowestCostActivity.EXAMPLE_GRID_1.asDelimitedString("\t");
        activity.findViewById(R.id.grid_1_button).performClick();

        String gridContents = ((TextView) activity.findViewById(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingExampleTwoButtonLoadsExampleGridTwo() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        String expectedContents = PathOfLowestCostActivity.EXAMPLE_GRID_2.asDelimitedString("\t");
        activity.findViewById(R.id.grid_2_button).performClick();

        String gridContents = ((TextView) activity.findViewById(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingExampleThreeButtonLoadsExampleGridThree() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        String expectedContents = PathOfLowestCostActivity.EXAMPLE_GRID_3.asDelimitedString("\t");
        activity.findViewById(R.id.grid_3_button).performClick();

        String gridContents = ((TextView) activity.findViewById(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }
}
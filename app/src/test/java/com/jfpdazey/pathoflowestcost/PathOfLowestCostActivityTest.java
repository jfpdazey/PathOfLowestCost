package com.jfpdazey.pathoflowestcost;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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

    @Test
    public void goButtonIsDisabledByDefault() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        Button goButton = (Button) activity.findViewById(R.id.go_button);
        assertThat(goButton.isEnabled(), is(false));
    }

    @Test
    public void clickingAnyExampleGridButtonEnablesGoButton() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        Button goButton = (Button) activity.findViewById(R.id.go_button);

        activity.findViewById(R.id.grid_1_button).performClick();
        assertThat(goButton.isEnabled(), is(true));
        goButton.setEnabled(false);

        activity.findViewById(R.id.grid_2_button).performClick();
        assertThat(goButton.isEnabled(), is(true));
        goButton.setEnabled(false);

        activity.findViewById(R.id.grid_3_button).performClick();
        assertThat(goButton.isEnabled(), is(true));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysYesIfPathSuccessful() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        activity.findViewById(R.id.grid_1_button).performClick();
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_contents);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysNoIfPathNotSuccessful() {
        PathOfLowestCostActivity activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
        activity.findViewById(R.id.grid_3_button).performClick();
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_contents);
        assertThat(resultsView.getText().toString(), equalTo("No"));
    }
}
package com.jfpdazey.pathoflowestcost;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
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

    private PathOfLowestCostActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(PathOfLowestCostActivity.class);
    }

    @Test
    public void clickingExampleOneButtonLoadsExampleGridOne() {
        String expectedContents = PathOfLowestCostActivity.EXAMPLE_GRID_1.asDelimitedString("\t");
        activity.findViewById(R.id.grid_1_button).performClick();

        String gridContents = ((TextView) activity.findViewById(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingExampleTwoButtonLoadsExampleGridTwo() {
        String expectedContents = PathOfLowestCostActivity.EXAMPLE_GRID_2.asDelimitedString("\t");
        activity.findViewById(R.id.grid_2_button).performClick();

        String gridContents = ((TextView) activity.findViewById(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingExampleThreeButtonLoadsExampleGridThree() {
        String expectedContents = PathOfLowestCostActivity.EXAMPLE_GRID_3.asDelimitedString("\t");
        activity.findViewById(R.id.grid_3_button).performClick();

        String gridContents = ((TextView) activity.findViewById(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void goButtonIsDisabledByDefault() {
        Button goButton = (Button) activity.findViewById(R.id.go_button);
        assertThat(goButton.isEnabled(), is(false));
    }

    @Test
    public void clickingAnyExampleGridButtonEnablesGoButton() {
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
        activity.findViewById(R.id.grid_1_button).performClick();
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysNoIfPathNotSuccessful() {
        activity.findViewById(R.id.grid_3_button).performClick();
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        assertThat(resultsView.getText().toString(), equalTo("No"));
    }

//    @Test
//    public void clickingGoAfterClickingAGridButtonDisplaysTotalScoreOfPathOnSecondLineOfResults() {
//        activity.findViewById(R.id.grid_1_button).performClick();
//        activity.findViewById(R.id.go_button).performClick();
//
//        TextView resultsView = (TextView) activity.findViewById(R.id.results_contents);
//        assertThat(resultsView.getText().toString(), equalTo("Yes"));
//    }
}
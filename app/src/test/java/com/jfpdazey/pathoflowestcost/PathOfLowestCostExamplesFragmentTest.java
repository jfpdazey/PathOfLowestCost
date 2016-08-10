package com.jfpdazey.pathoflowestcost;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOfLowestCostExamplesFragmentTest {

    private PathOfLowestCostExamplesFragment fragment;

    @Before
    public void setUp() {
        fragment = new PathOfLowestCostExamplesFragment();
        startFragment(fragment);
        assertNotNull(fragment);
    }

    @Test
    public void clickingExampleOneButtonLoadsExampleGridOne() {
        String expectedContents = PathOfLowestCostExamplesFragment.EXAMPLE_GRID_1.asDelimitedString("\t");
        getViewFromFragment(R.id.grid_1_button).performClick();

        String gridContents = ((TextView) getViewFromFragment(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingExampleTwoButtonLoadsExampleGridTwo() {
        String expectedContents = PathOfLowestCostExamplesFragment.EXAMPLE_GRID_2.asDelimitedString("\t");
        getViewFromFragment(R.id.grid_2_button).performClick();

        String gridContents = ((TextView) getViewFromFragment(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingExampleThreeButtonLoadsExampleGridThree() {
        String expectedContents = PathOfLowestCostExamplesFragment.EXAMPLE_GRID_3.asDelimitedString("\t");
        getViewFromFragment(R.id.grid_3_button).performClick();

        String gridContents = ((TextView) getViewFromFragment(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void goButtonIsDisabledByDefault() {
        Button goButton = (Button) getViewFromFragment(R.id.go_button);
        assertThat(goButton.isEnabled(), is(false));
    }

    @Test
    public void clickingAnyExampleGridButtonEnablesGoButton() {
        Button goButton = (Button) getViewFromFragment(R.id.go_button);

        getViewFromFragment(R.id.grid_1_button).performClick();
        assertThat(goButton.isEnabled(), is(true));
        goButton.setEnabled(false);

        getViewFromFragment(R.id.grid_2_button).performClick();
        assertThat(goButton.isEnabled(), is(true));
        goButton.setEnabled(false);

        getViewFromFragment(R.id.grid_3_button).performClick();
        assertThat(goButton.isEnabled(), is(true));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysYesIfPathSuccessful() {
        getViewFromFragment(R.id.grid_1_button).performClick();
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_success);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysNoIfPathNotSuccessful() {
        getViewFromFragment(R.id.grid_3_button).performClick();
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_success);
        assertThat(resultsView.getText().toString(), equalTo("No"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysTotalCostOfPathOnSecondLineOfResults() {
        getViewFromFragment(R.id.grid_2_button).performClick();
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_total_cost);
        assertThat(resultsView.getText().toString(), equalTo("11"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysPathTakenOnThirdLineOfResults() {
        getViewFromFragment(R.id.grid_1_button).performClick();
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("1\t2\t3\t4\t4\t5"));
    }

    @Test
    public void selectingADifferentGridClearsResults() {
        getViewFromFragment(R.id.grid_1_button).performClick();
        getViewFromFragment(R.id.go_button).performClick();

        getViewFromFragment(R.id.grid_2_button).performClick();

        TextView successView = (TextView) getViewFromFragment(R.id.results_success);
        assertThat(successView.getText().toString(), equalTo(""));
        TextView costView = (TextView) getViewFromFragment(R.id.results_total_cost);
        assertThat(costView.getText().toString(), equalTo("No results"));
        TextView pathView = (TextView) getViewFromFragment(R.id.results_path_taken);
        assertThat(pathView.getText().toString(), equalTo(""));
    }

    @Test
    public void selectingTheSameGridDoesNotClearResults() {
        getViewFromFragment(R.id.grid_1_button).performClick();
        getViewFromFragment(R.id.go_button).performClick();

        getViewFromFragment(R.id.grid_1_button).performClick();

        TextView successView = (TextView) getViewFromFragment(R.id.results_success);
        assertThat(successView.getText().toString(), not(equalTo("")));
        TextView costView = (TextView) getViewFromFragment(R.id.results_total_cost);
        assertThat(costView.getText().toString(), not(equalTo("No results")));
        TextView pathView = (TextView) getViewFromFragment(R.id.results_path_taken);
        assertThat(pathView.getText().toString(), not(equalTo("")));
    }

    private View getViewFromFragment(int id) {
        return fragment.getView().findViewById(id);
    }
}
package com.jfpdazey.pathoflowestcost;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.jfpdazey.pathoflowestcost")
public class PathOfLowestCostCustomFragmentTest {

    private PathOfLowestCostCustomFragment fragment;

    @Before
    public void setUp() {
        fragment = new PathOfLowestCostCustomFragment();
        startFragment(fragment);
        assertNotNull(fragment);
    }

    @Test
    public void goButtonIsDisabledByDefault() {
        Button goButton = (Button) getViewFromFragment(R.id.go_button);
        assertThat(goButton.isEnabled(), is(false));
    }

    @Test
    public void enteringAnyTextIntoTheCustomGridContentsEnablesTheGoButton() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);
        Button goButton = (Button) getViewFromFragment(R.id.go_button);

        customGridContents.setText("a");

        assertThat(goButton.isEnabled(), is(true));
    }

    @Test
    public void removingAllTextFromTheCustomGridContentsDisablesTheGoButton() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);
        Button goButton = (Button) getViewFromFragment(R.id.go_button);
        customGridContents.setText("a");

        customGridContents.setText("");

        assertThat(goButton.isEnabled(), is(false));
    }

    @Test
    public void clickingGoWithLessThanFiveColumnsOfDataDisplaysErrorMessage() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("1 2 3 4");
        getViewFromFragment(R.id.go_button).performClick();

        ShadowAlertDialog alertDialog = Shadows.shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        assertThat(alertDialog.getTitle().toString(), equalTo("Invalid Grid"));
        assertThat(alertDialog.getMessage().toString(), equalTo(fragment.getResources().getString(R.string.invalid_grid_message)));
    }

    @Test
    public void clickingGoWithMoreThanOneHundredColumnsOfDataDisplaysErrorMessage() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 1; i <= 101; i++) {
            inputBuilder.append(i).append(" ");
        }

        customGridContents.setText(inputBuilder.toString());
        getViewFromFragment(R.id.go_button).performClick();

        ShadowAlertDialog alertDialog = Shadows.shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        assertThat(alertDialog.getTitle().toString(), equalTo("Invalid Grid"));
        assertThat(alertDialog.getMessage().toString(), equalTo(fragment.getResources().getString(R.string.invalid_grid_message)));
    }

    @Test
    public void clickingGoWithNonNumericDataDisplaysErrorMessage() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("1 2 3 4 b");
        getViewFromFragment(R.id.go_button).performClick();

        ShadowAlertDialog alertDialog = Shadows.shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        assertThat(alertDialog.getTitle().toString(), equalTo("Invalid Grid"));
        assertThat(alertDialog.getMessage().toString(), equalTo(fragment.getResources().getString(R.string.invalid_grid_message)));
    }

    @Test
    public void clickingGoWithValidDataLoadsTheGrid() {
        String expectedContents = GridUtils.EXAMPLE_GRID_3.asDelimitedString("\t");
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText(expectedContents);
        getViewFromFragment(R.id.go_button).performClick();

        String gridContents = ((TextView) getViewFromFragment(R.id.grid_contents)).getText().toString();
        assertThat(gridContents, equalTo(expectedContents));
    }

    @Test
    public void clickingGoWithValidDataDisplaysYesIfPathSuccessful() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("1 2 3 4 5");
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_success);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysNoIfPathNotSuccessful() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("50 2 3 4 5");
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_success);
        assertThat(resultsView.getText().toString(), equalTo("No"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysTotalCostOfPathOnSecondLineOfResults() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("1 2 3 4 5");
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_total_cost);
        assertThat(resultsView.getText().toString(), equalTo("15"));
    }

    @Test
    public void clickingGoAfterClickingAGridButtonDisplaysPathTakenOnThirdLineOfResults() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("1 2 3 4 5 6\n2 1 2 2 2 2\n3 3 1 3 3 3\n4 4 4 1 1 4\n5 5 5 5 5 1\n6 6 6 6 6 6");
        getViewFromFragment(R.id.go_button).performClick();

        TextView resultsView = (TextView) getViewFromFragment(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("1\t2\t3\t4\t4\t5"));
    }

    private View getViewFromFragment(int id) {
        return fragment.getView().findViewById(id);
    }
}
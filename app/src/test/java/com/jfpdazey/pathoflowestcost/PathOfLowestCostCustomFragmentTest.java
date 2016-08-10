package com.jfpdazey.pathoflowestcost;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

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
    public void clickingGoAfterClickingAGridButtonDisplaysUnimplementedToast() {
        EditText customGridContents = (EditText) getViewFromFragment(R.id.custom_grid_contents);

        customGridContents.setText("1");
        getViewFromFragment(R.id.go_button).performClick();

        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Evaluation of custom grids not implemented yet..."));
    }

    private View getViewFromFragment(int id) {
        return fragment.getView().findViewById(id);
    }
}
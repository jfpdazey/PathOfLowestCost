package com.jfpdazey.pathoflowestcost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PathOfLowestCostActivity extends AppCompatActivity {

    public static final Grid EXAMPLE_GRID_1 = new Grid(new int[][]{
            { 3, 4, 1, 2, 8, 6 },
            { 6, 1, 8, 2, 7, 4 },
            { 5, 9, 3, 9, 9, 5 },
            { 8, 4, 1, 3, 2, 6 },
            { 3, 7, 2, 8, 6, 4 }
    });

    public static final Grid EXAMPLE_GRID_2 = new Grid(new int[][]{
            { 3, 4, 1, 2, 8, 6 },
            { 6, 1, 8, 2, 7, 4 },
            { 5, 9, 3, 9, 9, 5 },
            { 8, 4, 1, 3, 2, 6 },
            { 3, 7, 2, 1, 2, 3 }
    });

    public static final Grid EXAMPLE_GRID_3 = new Grid(new int[][]{
            { 19, 10, 19, 10, 19 },
            { 21, 23, 20, 19, 12 },
            { 20, 12, 20, 11, 10 }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowest_cost_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonForGridClicked(View view) {
        String gridContents = getGridContentsForButton(view);
        ((TextView) findViewById(R.id.grid_contents)).setText(gridContents);
    }

    private String getGridContentsForButton(View view) {
        String contents = "";

        switch (view.getId()) {
            case R.id.grid_1_button:
                contents = EXAMPLE_GRID_1.asDelimitedString("\t");
                break;
            case R.id.grid_2_button:
                contents = EXAMPLE_GRID_2.asDelimitedString("\t");
                break;
            case R.id.grid_3_button:
                contents = EXAMPLE_GRID_3.asDelimitedString("\t");
                break;
        }

        return contents;
    }
}

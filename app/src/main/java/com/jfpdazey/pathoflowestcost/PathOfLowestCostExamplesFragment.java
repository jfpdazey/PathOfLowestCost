package com.jfpdazey.pathoflowestcost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PathOfLowestCostExamplesFragment extends Fragment {

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

    private Grid loadedGrid;

    public PathOfLowestCostExamplesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_lowest_cost_examples_main, container, false);

        Button gridOneButton = (Button) fragmentView.findViewById(R.id.grid_1_button);
        gridOneButton.setOnClickListener(new GridOnClickListener());
        Button gridTwoButton = (Button) fragmentView.findViewById(R.id.grid_2_button);
        gridTwoButton.setOnClickListener(new GridOnClickListener());
        Button gridThreeButton = (Button) fragmentView.findViewById(R.id.grid_3_button);
        gridThreeButton.setOnClickListener(new GridOnClickListener());
        Button goButton = (Button) fragmentView.findViewById(R.id.go_button);
        goButton.setOnClickListener(new GoOnClickListener());

        return fragmentView;
    }

    private Grid getGridForButton(View view) {
        switch (view.getId()) {
            case R.id.grid_1_button:
                return EXAMPLE_GRID_1;
            case R.id.grid_2_button:
                return EXAMPLE_GRID_2;
            case R.id.grid_3_button:
                return EXAMPLE_GRID_3;
            default:
                return null;
        }
    }

    private void clearResults() {
        ((TextView) getView().findViewById(R.id.results_success)).setText("");
        ((TextView) getView().findViewById(R.id.results_total_cost)).setText(getResources().getText(R.string.no_results));
        ((TextView) getView().findViewById(R.id.results_path_taken)).setText("");
    }

    private String formatPath(PathState path) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = path.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }

    class GridOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Grid selectedGrid = getGridForButton(view);
            if (!selectedGrid.equals(loadedGrid)) {
                clearResults();
            }

            loadedGrid = selectedGrid;
            ((TextView) getView().findViewById(R.id.grid_contents)).setText(loadedGrid.asDelimitedString("\t"));
            getView().findViewById(R.id.go_button).setEnabled(true);
        }
    }

    class GoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            GridVisitor visitor = new GridVisitor(loadedGrid);
            PathState bestPath = visitor.visitPathsForAllRows().get(0);

            if (bestPath.isSuccessful()) {
                ((TextView) getView().findViewById(R.id.results_success)).setText("Yes");
            } else {
                ((TextView) getView().findViewById(R.id.results_success)).setText("No");
            }
            ((TextView) getView().findViewById(R.id.results_total_cost)).setText(Integer.toString(bestPath.getTotalCost()));
            ((TextView) getView().findViewById(R.id.results_path_taken)).setText(formatPath(bestPath));
        }
    }
}

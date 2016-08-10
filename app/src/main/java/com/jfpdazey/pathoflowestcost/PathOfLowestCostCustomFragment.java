package com.jfpdazey.pathoflowestcost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PathOfLowestCostCustomFragment extends Fragment {

    private Grid loadedGrid;

    public PathOfLowestCostCustomFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_lowest_cost_custom_main, container, false);

        Button goButton = (Button) fragmentView.findViewById(R.id.go_button);
        goButton.setOnClickListener(new GoOnClickListener());

        EditText customGridContents = (EditText) fragmentView.findViewById(R.id.custom_grid_contents);
        customGridContents.addTextChangedListener(new GridContentsWatcher());

        return fragmentView;
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

    class GridContentsWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Button goButton = (Button) getView().findViewById(R.id.go_button);
            goButton.setEnabled(!s.toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) { }
    }

    class GoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast notYetToast = Toast.makeText(getContext(), "Evaluation of custom grids not implemented yet...", Toast.LENGTH_LONG);
            notYetToast.show();
//            GridVisitor visitor = new GridVisitor(loadedGrid);
//            PathState bestPath = visitor.visitPathsForAllRows().get(0);
//
//            if (bestPath.isSuccessful()) {
//                ((TextView) getView().findViewById(R.id.results_success)).setText("Yes");
//            } else {
//                ((TextView) getView().findViewById(R.id.results_success)).setText("No");
//            }
//            ((TextView) getView().findViewById(R.id.results_total_cost)).setText(Integer.toString(bestPath.getTotalCost()));
//            ((TextView) getView().findViewById(R.id.results_path_taken)).setText(formatPath(bestPath));
        }
    }
}

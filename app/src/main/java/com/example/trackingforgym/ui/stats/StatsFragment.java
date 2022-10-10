package com.example.trackingforgym.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.DataBase;
import com.example.trackingforgym.data.Session;
import com.example.trackingforgym.data.User;
import com.example.trackingforgym.databinding.FragmentStatsBinding;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class StatsFragment extends Fragment {

    private StatsViewModel slideshowViewModel;
    private FragmentStatsBinding binding;
    private ColumnChartView chart;
    ColumnChartView chart1;
    ColumnChartData data1;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);

        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        chart = (ColumnChartView) root.findViewById(R.id.chart);
        chart1= (ColumnChartView) root.findViewById(R.id.chart1);

        //chart.setOnValueTouchListener(new ValueTouchListener());

        generateDefaultData();;
        User usuario =Session.getUser();
        usuario.entrenamientos= DataBase.getEntrenamientos(usuario.getId());
        usuario.stats=DataBase.getLastStat(usuario.getId());

        /*final TextView textView = binding.textStats;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    void generateDefaultData() {
        int numSubcolumns = 1;
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
        int numColumns = Session.getUser().rutinas.size();
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue(Session.getUser().rutinas.get(i).numero_usos, ChartUtils.pickColor()));
            }
            mAxisXValues.add(new AxisValue(i).setLabel(Session.getUser().rutinas.get(i).getNombre()));
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);
        Axis axisX = new Axis();;
        if (hasAxes) {

            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Rutina");
                axisY.setName("Usos");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        axisX.setValues(mAxisXValues);
        chart.setColumnChartData(data);
        /////

        List<AxisValue> mAxisXValues1 = new ArrayList<AxisValue>();
        int numColumns1 = Session.getUser().stats.size();
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns1 = new ArrayList<Column>();
        List<SubcolumnValue> values1;
        for (int i = 0; i < numColumns1; ++i) {

            values1 = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values1.add(new SubcolumnValue(Session.getUser().stats.get(i).usos, ChartUtils.pickColor()));
            }
            mAxisXValues1.add(new AxisValue(i).setLabel(Session.getUser().stats.get(i).nombre));
            Column column1 = new Column(values1);
            column1.setHasLabels(hasLabels);
            column1.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns1.add(column1);
        }

        data1 = new ColumnChartData(columns1);
        Axis axisX1 = new Axis();;
        if (hasAxes) {

            Axis axisY1 = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX1.setName("Ejercicio");
                axisY1.setName("Usos");
            }
            data1.setAxisXBottom(axisX1);
            data1.setAxisYLeft(axisY1);
        } else {
            data1.setAxisXBottom(null);
            data1.setAxisYLeft(null);
        }
        axisX1.setValues(mAxisXValues1);
        chart1.setColumnChartData(data1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
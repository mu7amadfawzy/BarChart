package com.widget.chart;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.widget.barchart.R;
import com.widget.barchart.databinding.ChartBarBinding;
import com.widget.barchart.databinding.ChartViewBinding;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.MathObservable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Fwazy on 02,April,2019.
 * ma7madfawzy@gmail.com
 */

public class BarChartView extends RelativeLayout {
    private static final short DEFAULT_COLUMN_MAX_VALUE = 100;
    private ArrayList<BarChartModel> columnsBars, rowsBars;
    private ChartViewBinding binding;
    private BarsAdapter adapter;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    private int parentHeight = -1;
    private float maxColumnValue;
    private boolean showLines, showPercentages;
    private TypedArray attributesArray;
    private Drawable column_background;
    private int columnColor;
    private int column_label_color, x_line_color;
    private float gap;
    private int rowsNumber = -1;

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        createView(context);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        createView(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        attributesArray = context.obtainStyledAttributes(attrs, R.styleable.BarChartView);
        column_background = attributesArray.getDrawable(R.styleable.BarChartView_column_background);
        columnColor = attributesArray.getColor(R.styleable.BarChartView_column_color, 0);
        column_label_color = attributesArray.getColor(R.styleable.BarChartView_column_label_color, Color.BLACK);
        x_line_color = attributesArray.getColor(R.styleable.BarChartView_x_line_color, Color.BLACK);
        gap = attributesArray.getDimension(R.styleable.BarChartView_gap, getResources().getDimension(R.dimen._5sdp));
        showLines = attributesArray.getBoolean(R.styleable.BarChartView_showLines, false);
        showPercentages = attributesArray.getBoolean(R.styleable.BarChartView_showPercentages, false);
    }

    public void createView(Context context) {
        renderView(LayoutInflater.from(context), this);
    }

    private void renderView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chart_view, container, true);
        binding.maxValueLayout.valueTV.setTextColor(x_line_color);
        binding.setShowPercentages(showPercentages);
        calculateParentHeight();
        setColumnsRecycler();
    }

    private void setColumnsRecycler() {
        binding.chartRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapter = new BarsAdapter(new ArrayList<>(), column_background, column_label_color, columnColor);
        binding.chartRecycler.setAdapter(adapter);
    }

    private void calculateParentHeight() {
        layoutListener = () -> {
            parentHeight = binding.parentLayout.getHeight();
            binding.parentLayout.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
//            draw();
        };
        binding.parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }

    public void drawChart(List<BarChartModel> columnsBars) {
        drawChart(columnsBars, -1);
    }

    public void drawChart(List<BarChartModel> columnsBars, List<BarChartModel> rowsBars) {
        this.rowsBars = new ArrayList<>(rowsBars);
        drawChart(columnsBars, -1);
    }

    public void drawChart(List<BarChartModel> columnsBars, int rowsNumber) {
        this.columnsBars = new ArrayList<>(columnsBars);
        this.rowsNumber = rowsNumber;
        setMaxValue();
    }

    private void setMaxValue() {
        Observable<Integer> observable = Observable.from(columnsBars)
                .map(BarChartModel::getPercent);

        MathObservable.max(observable)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    maxColumnValue = integer;
                    if (maxColumnValue <= 0)
                        maxColumnValue = DEFAULT_COLUMN_MAX_VALUE;
                    binding.setMaxColumnValue(Math.round(maxColumnValue));
                    draw();
                });
    }

    private void draw() {
        if (columnsBars == null || parentHeight == -1)
            return;
        addColumns();
        handleRows();
    }

    private void handleRows() {
        if (rowsBars != null && !rowsBars.isEmpty())//in case rowsModelList was provided
            addRows();
        else generateRows(rowsNumber);
    }

    private void generateRows(int rowsNumber) {
        if (rowsNumber <= 0)
            return;
        rowsBars = new ArrayList<>();
        rowsBars.add(new BarChartModel(Math.round(maxColumnValue)));//the top bar
        rowsBars.add(new BarChartModel(0));// the bottom bar

        int leftRows = rowsNumber - 2;//defines how many rows left to be added

        float average = maxColumnValue / (leftRows + 1);
//
        for (int i = 0; i < leftRows; i++) {
            rowsBars.add(new BarChartModel(Math.round((i + 1) * average)));
        }
        addRows();
    }

    private void addColumns() {
        Observable.from(columnsBars).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(barChartModel -> new ChartModel(barChartModel.getPercent(), getHeightRatio(parentHeight
                        , barChartModel.getPercent()), barChartModel.getLabel()
                        , Math.round(gap)))
                .toList().subscribe(barModels -> adapter.setDataList(barModels));
    }

    private void addRows() {
        Observable.from(rowsBars).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(barChartModel -> new ChartModel(barChartModel.getPercent(), getHeightRatioReversed(parentHeight, barChartModel.getPercent())
                        , barChartModel.getLabel()))
                .toList().subscribe(this::addRowBar);
    }

    private void addRowBar(List<ChartModel> chartModels) {
        Log.d("LogTag", "rowsCount: " + chartModels.size());
        for (ChartModel chartModel : chartModels) {
            inflateRowView(binding.xAxisContainer, chartModel);
        }
    }

    private void inflateRowView(FrameLayout container, ChartModel chartModel) {
        ChartBarBinding barBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext())
                , R.layout.chart_bar, container, true);
        barBinding.setLabel(chartModel.getGivenValue() + "");
        barBinding.setShowLine(showLines);
        barBinding.setShowPercentages(showPercentages);
        if (x_line_color != 0) {
            barBinding.line.setBackgroundColor(x_line_color);
            barBinding.valueTV.setTextColor(x_line_color);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) barBinding.getRoot().getLayoutParams();
        layoutParams.topMargin = chartModel.getAbsoluteValue() - getViewHeight(barBinding.parent);
    }

    private int getViewHeight(View view) {
        view.measure(WRAP_CONTENT, WRAP_CONTENT);
        return view.getMeasuredHeight();
    }

    private int getHeightRatio(int parentHeight, Integer barHeight) {
        return Math.round(((barHeight / maxColumnValue)) * (parentHeight - getYBarLabelHeight()));
    }

    private int getHeightRatioReversed(int parentHeight, Integer barHeight) {
        return Math.round((((maxColumnValue - barHeight) / maxColumnValue)) * (parentHeight - getYBarLabelHeight()));
    }

    private float getYBarLabelHeight() {
        return getResources().getDimension(R.dimen.ybar_label_height);
    }

}
package com.widget.barchart.chart;

/**
 * Created by Fawzy on 02,April,2019
 * ma7madfawzy@gmail.com
 */
public class BarChartModel {
    private String label;
    private int percent;

    public BarChartModel(String label, int percent) {
        this.label = label;
        this.percent = percent;
    }

    public BarChartModel(int percent) {
        this.percent = percent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}

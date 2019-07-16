package com.widget.barchart.chart;

/**
 * Created by Fawzy on 31,March,2019
 * ma7madfawzy@gmail.com
 */
public class ChartModel {
    private int absoluteValue, givenValue;
    private int barGap;
    private String label;

    public ChartModel(int givenValue, int absoluteValue, String label) {
        setGivenValue(givenValue);
        setAbsoluteValue(absoluteValue);
        setLabel(label);
    }

    public ChartModel(int givenValue, int absoluteValue, String label, int barGap) {
        setGivenValue(givenValue);
        setAbsoluteValue(absoluteValue);
        setLabel(label);
        setBarGap(barGap);
    }

    public int getBarGap() {
        return barGap;
    }

    public void setBarGap(int barGap) {
        this.barGap = barGap;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getGivenValue() {
        return givenValue;
    }


    public void setGivenValue(int givenValue) {
        this.givenValue = givenValue;
    }

    public int getAbsoluteValue() {
        return absoluteValue;
    }

    public void setAbsoluteValue(int absoluteValue) {
        this.absoluteValue = absoluteValue;
    }
}

package com.widget.barchart.chart;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by fawzy on 16,July,2019
 * ma7madfawzy@gmail.com
 **/
public class BindingUtils {
    @BindingAdapter({"layout_marginStart", "layout_marginEnd"})
    public static void setLayoutMarginStart(LinearLayout view, int marginStart, int marginEnd) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(marginStart, 0, marginEnd, 0);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("layout_height")
    public static void setLayoutHeight(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("layout_width")
    public static void setLayoutWidth(View view, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("bg_color")
    public static void setBgColor(View view, int colorRes) {
        if (colorRes == 0)
            return;
        view.setBackgroundColor(colorRes);
    }

    @BindingAdapter("bg_drawable")
    public static void setBgColor(View view, Drawable drawable) {
        if (drawable == null)
            return;
        view.setBackground(drawable);
    }
}

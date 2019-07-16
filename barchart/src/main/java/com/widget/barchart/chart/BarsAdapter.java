package com.widget.barchart.chart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.widget.barchart.R;
import com.widget.barchart.databinding.ChartColumnBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fawzy on 31,March,2019
 * ma7madfawzy@gmail.com
 */
public class BarsAdapter extends RecyclerView.Adapter<BarsAdapter.ViewHolder> {
    private final Drawable columnBG;
    private final int columnLabelColor;
    private ArrayList<ChartModel> dataList;
    private LayoutInflater layoutInflater;

    public BarsAdapter(List<ChartModel> dataList, Drawable columnBG, int columnLabelColor) {
        super();
        this.dataList = new ArrayList<>(dataList);
        this.columnBG = columnBG;
        this.columnLabelColor = columnLabelColor;
    }

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

    public void setDataList(List<ChartModel> list) {
        this.dataList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public List<ChartModel> getData() {
        return dataList;
    }

    @Override
    public BarsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ChartColumnBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.chart_column, parent, false);
        return new BarsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BarsAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ChartColumnBinding binding;

        ViewHolder(ChartColumnBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            this.binding.setModel(dataList.get(position));
            binding.title.setTextColor(columnLabelColor != 0 ? columnLabelColor : Color.RED);
        }

    }


}


package com.widget.chart;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
    private Drawable columnBG;
    private int columnLabelColor, columnColor;
    private ArrayList<ChartModel> dataList;
    private LayoutInflater layoutInflater;

    public BarsAdapter(List<ChartModel> dataList, Drawable columnBG, int columnLabelColor, int columnColor) {
        super();
        this.dataList = new ArrayList<>(dataList);
        this.columnBG = columnBG;
        this.columnLabelColor = columnLabelColor;
        this.columnColor = columnColor;
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
            binding.title.setTextColor(columnLabelColor);
            binding.title.setSelected(true);
            binding.setColumnColor(columnColor);
            binding.setColumnBG(columnBG);
            this.binding.setModel(dataList.get(position));
        }

    }


}


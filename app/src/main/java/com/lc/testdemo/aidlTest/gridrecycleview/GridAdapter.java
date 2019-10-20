package com.lc.testdemo.aidlTest.gridrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lc.testdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 */

public class GridAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<ItemViewData> itemViewDataList;

    public GridAdapter(Context context, List<ItemViewData> itemViewDataList) {
        this.context = context;
        this.itemViewDataList = itemViewDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_grid,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == 0){
            holder.bind(itemViewDataList.get(position).getText());
        }
    }

    @Override
    public int getItemCount() {
        return itemViewDataList.size();
    }
}

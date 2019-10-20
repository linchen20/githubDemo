package com.lc.testdemo.aidlTest.gridrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lc.testdemo.R;

/**
 * Created by Administrator on 2019/3/11.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bind(String text){
        ((TextView)(itemView.findViewById(R.id.tvRvGrid))).setText(text);
    }
}

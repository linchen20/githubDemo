package com.lc.testdemo.aidlTest.gridrecycleview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class GridItemDecorationB extends RecyclerView.ItemDecoration {

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        GridLayoutManager.SpanSizeLookup sizeLookup = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup();
        int spanIndex = sizeLookup.getSpanIndex(itemPosition, spanCount);
        int spanSize = sizeLookup.getSpanSize(itemPosition);
        if (spanSize == 10) {
            if (spanIndex == 0) {
                outRect.left = /*DensityUtils.dp2px(14)*/28;
                outRect.bottom = 0;
                outRect.right = /*DensityUtils.dp2px(8)*/16;
                outRect.top = 0;
            } else {
                outRect.left = /*DensityUtils.dp2px(8)*/16;
                outRect.bottom = 0;
                outRect.right = /*DensityUtils.dp2px(14)*/28;
                outRect.top = 0;
            }
        } else {
            outRect.left = 0;
            outRect.bottom = 0;
            outRect.right = 0;
            outRect.top = 0;
        }
    }
}
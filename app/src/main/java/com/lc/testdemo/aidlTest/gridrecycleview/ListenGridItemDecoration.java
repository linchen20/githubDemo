package com.lc.testdemo.aidlTest.gridrecycleview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * @author Administrator
 */
public class ListenGridItemDecoration extends RecyclerView.ItemDecoration {

    private int padLR;
    private int space;

    public ListenGridItemDecoration(int padLR, int space) {
        this.padLR = padLR;
        this.space = space;
    }

    private int getSpanCount (RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        GridLayoutManager.SpanSizeLookup sizeLookup = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup();
        int spanIndex = sizeLookup.getSpanIndex(itemPosition, spanCount);
        int spanSize = sizeLookup.getSpanSize(itemPosition);
        if (spanSize == 2) {
            if (spanIndex == 0) {
                outRect.left = padLR;
                outRect.bottom = 0;
                outRect.right = space;
                outRect.top = 0;
            } else if (spanIndex == 1){
                outRect.left = 0;
                outRect.bottom = 0;
                outRect.right = 0;
                outRect.top = 0;
            } else {
                outRect.left = space;
                outRect.bottom = 0;
                outRect.right = padLR;
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
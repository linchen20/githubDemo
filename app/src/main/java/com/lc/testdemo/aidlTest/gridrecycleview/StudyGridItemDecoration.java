package com.lc.testdemo.aidlTest.gridrecycleview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class StudyGridItemDecoration extends RecyclerView.ItemDecoration {

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

        int centerSize1;
        int centerSize2;
        int borderSize;
        int bottomSize;
        int topSize;

            centerSize1 = 8;
            centerSize2 = 6;
            borderSize = 4;
            bottomSize = 0;
            topSize = 0;

        if (spanSize == 3) {
            outRect.left = borderSize;
            outRect.bottom = bottomSize;
            outRect.right = borderSize;
            outRect.top = topSize;
        } else if (spanSize == 2) {
            outRect.left = borderSize;
            outRect.bottom = bottomSize;
            outRect.right = centerSize2;
            outRect.top = topSize;
        } else {
            if (spanIndex == 0) {
                outRect.left = borderSize;
                outRect.bottom = bottomSize;
                outRect.right = centerSize1;
                outRect.top = topSize;
            } else if (spanIndex == 1) {
                outRect.left = centerSize2;
                outRect.bottom = bottomSize;
                outRect.right = centerSize2;
                outRect.top = topSize;
            } else {
                outRect.left = centerSize1;
                outRect.bottom = bottomSize;
                outRect.right = borderSize;
                outRect.top = topSize;
            }
        }
    }
}
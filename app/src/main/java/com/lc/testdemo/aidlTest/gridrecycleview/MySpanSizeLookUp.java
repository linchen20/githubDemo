package com.lc.testdemo.aidlTest.gridrecycleview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 */

public class MySpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {


    private List<ItemViewData> itemViewDataList;

    public MySpanSizeLookUp(List<ItemViewData> itemViewDataList) {
        this.itemViewDataList = itemViewDataList;
    }

    @Override
    public int getSpanSize(int position) {
        switch (itemViewDataList.get(position).getItemType()){
            case ItemViewData.ITEM_TYPE_A:
                return 5;
            case ItemViewData.ITEM_TYPE_B:
                return 15;
            case ItemViewData.ITEM_TYPE_C:
                return 3;
            case ItemViewData.ITEM_TYPE_D:
                return 10;
            default:
                return 7;
        }
    }
}

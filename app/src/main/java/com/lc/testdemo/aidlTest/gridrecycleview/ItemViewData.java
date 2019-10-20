package com.lc.testdemo.aidlTest.gridrecycleview;

/**
 * Created by Administrator on 2019/3/11.
 */

public class ItemViewData {

    public static final int ITEM_TYPE_A = 0;
    public static final int ITEM_TYPE_B = 1;
    public static final int ITEM_TYPE_C = 2;
    public static final int ITEM_TYPE_D = 3;
    private int ItemType;
    private String Text;

    public ItemViewData(int itemType, String text) {
        ItemType = itemType;
        Text = text;
    }

    public int getItemType() {
        return ItemType;
    }

    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}

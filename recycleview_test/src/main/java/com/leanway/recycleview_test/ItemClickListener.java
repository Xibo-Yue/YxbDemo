package com.leanway.recycleview_test;

import android.view.View;
import android.widget.TextView;

/**
 * @author Xibo_Yue
 * @time 2017/4/19 12:17
 */

public interface ItemClickListener {
    /**
     * Item的普通点击
     */
    public void onItemClick(View view, int position);

    /**
     * Item长按
     */
    public void onItemLongClick(View view, int position);

    /**
     * 子View点击事件
     * @param view
     * @param position
     */
    public void onItemSubViewClick(TextView view, int position);



    public void onItemCheckBoxClick(int position,boolean isCheck);
}

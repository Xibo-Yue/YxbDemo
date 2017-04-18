package com.leanway.recycleview_test.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.leanway.recycleview_test.TestBean;
import com.leanway.recycleview_test.holder.BaseHolderRV;
import com.leanway.recycleview_test.holder.TestHolder;

import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/4/18 15:02
 */

public class TestAdapter extends BaseAdapterRV<TestBean> {

    public TestAdapter(Context context, List<TestBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<TestBean> createViewHolder(Context context, ViewGroup parent, int
            viewType) {
        return new TestHolder(context,parent,this,viewType);
    }

}

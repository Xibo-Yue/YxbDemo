package com.leanway.recycleview_test.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leanway.recycleview_test.R;
import com.leanway.recycleview_test.TestBean;
import com.leanway.recycleview_test.adapter.BaseAdapterRV;

/**
 * @author Xibo_Yue
 * @time 2017/4/18 15:03
 */

public class TestHolder extends BaseHolderRV<TestBean> {


    private TextView nameTv;
    private TextView ageTv;
    private TextView cityTv;
    private TextView idcodeTv;


    public TestHolder(Context context, ViewGroup parent, BaseAdapterRV<TestBean> adapter, int
            itemType) {
        super(context, parent, adapter, itemType, R.layout.item_test);
    }

    @Override
    public void onFindViews(View itemView) {
        nameTv = (TextView) itemView.findViewById(R.id.name_tv);
        ageTv = (TextView) itemView.findViewById(R.id.age_tv);
        cityTv = (TextView) itemView.findViewById(R.id.city_tv);
        idcodeTv = (TextView) itemView.findViewById(R.id.idcode_tv);
    }

    @Override
    protected void onRefreshView(TestBean bean, int position) {
        nameTv.setText(bean.getName());
        ageTv.setText(bean.getAge());
        cityTv.setText(bean.getCity());
        idcodeTv.setText(bean.getIdCode());
    }
}

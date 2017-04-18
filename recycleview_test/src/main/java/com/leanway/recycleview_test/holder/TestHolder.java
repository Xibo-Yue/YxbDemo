package com.leanway.recycleview_test.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.leanway.recycleview_test.R;
import com.leanway.recycleview_test.TestBean;
import com.leanway.recycleview_test.adapter.BaseAdapterRV;

/**
 * @author Xibo_Yue
 * @time 2017/4/18 15:03
 */

public class TestHolder extends BaseHolderRV<TestBean> implements View.OnClickListener {

    private CheckBox mCheckBox;
    private TextView nameTv;
    private TextView ageTv;
    private TextView cityTv;
    private TextView idcodeTv;
    private Context mContext;

    public TestHolder(Context context, ViewGroup parent, BaseAdapterRV<TestBean> adapter, int
            itemType) {
        super(context, parent, adapter, itemType, R.layout.item_test);
        mContext = context;
    }

    @Override
    public void onFindViews(View itemView) {
        mCheckBox = (CheckBox) itemView.findViewById(R.id.cb_box);
        nameTv = (TextView) itemView.findViewById(R.id.name_tv);
        ageTv = (TextView) itemView.findViewById(R.id.age_tv);
        cityTv = (TextView) itemView.findViewById(R.id.city_tv);
        idcodeTv = (TextView) itemView.findViewById(R.id.idcode_tv);
        nameTv.setOnClickListener(this);
        ageTv.setOnClickListener(this);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.setCheck(isChecked);
            }
        });
    }

    @Override
    protected void onRefreshView(TestBean bean, int position) {
        nameTv.setText(bean.getName());
        ageTv.setText(bean.getAge());
        cityTv.setText(bean.getCity());
        idcodeTv.setText(bean.getIdCode());
        mCheckBox.setChecked(bean.isCheck());
    }

    @Override
    protected void onItemClick(View itemView, int position, TestBean bean) {
        super.onItemClick(itemView, position, bean);
        Toast.makeText(mContext,"第"+position+"个item",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.name_tv:
                Toast.makeText(mContext,bean.getName()+"位置"+position,Toast.LENGTH_SHORT).show();
                break;
            case R.id.age_tv:
                Toast.makeText(mContext,bean.getAge()+"位置"+position,Toast.LENGTH_SHORT).show();
                break;

        }
    }


}

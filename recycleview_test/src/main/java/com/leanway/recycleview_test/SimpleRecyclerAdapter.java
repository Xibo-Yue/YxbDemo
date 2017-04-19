package com.leanway.recycleview_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/4/19 11:38
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.MyViewHolder> {

    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public SimpleRecyclerAdapter(List<TestBean> datas, Context context) {
        mDatas = datas;
        mContext = context;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public SimpleRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_test,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerAdapter.MyViewHolder holder, int position) {
        holder.nameTv.setText(mDatas.get(position).getName());
        holder.ageTv.setText(mDatas.get(position).getAge());
        holder.cityTv.setText(mDatas.get(position).getCity());
        holder.idcodeTv.setText(mDatas.get(position).getIdCode());
        holder.mCheckBox.setChecked(mDatas.get(position).isCheck());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox mCheckBox;
        TextView nameTv;
        TextView ageTv;
        TextView cityTv;
        TextView idcodeTv;

        public MyViewHolder(View view) {
            super(view);
            mCheckBox = (CheckBox) view.findViewById(R.id.cb_box);
            nameTv = (TextView) view.findViewById(R.id.name_tv);
            ageTv = (TextView) view.findViewById(R.id.age_tv);
            cityTv = (TextView) view.findViewById(R.id.city_tv);
            idcodeTv = (TextView) view.findViewById(R.id.idcode_tv);
        }
    }
}

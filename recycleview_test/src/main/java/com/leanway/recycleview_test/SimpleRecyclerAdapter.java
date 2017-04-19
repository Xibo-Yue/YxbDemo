package com.leanway.recycleview_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/4/19 11:38
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.MyViewHolder> {

    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Integer> height;
    private ItemClickListener itemClickListener;

    public SimpleRecyclerAdapter(List<TestBean> datas, Context context) {
        mDatas = datas;
        mContext = context;
        inflater=LayoutInflater.from(mContext);
        getRandomHeight(this.mDatas);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public SimpleRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_test,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SimpleRecyclerAdapter.MyViewHolder holder, final int position) {

        /**
         * 得到item的LayoutParams布局参数
         */
//        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//        params.height = height.get(position);//把随机的高度赋予item布局
//        holder.itemView.setLayoutParams(params);//把params设置item布局

        holder.nameTv.setText(mDatas.get(position).getName());
        holder.ageTv.setText(mDatas.get(position).getAge());
        holder.cityTv.setText(mDatas.get(position).getCity());
        holder.idcodeTv.setText(mDatas.get(position).getIdCode());
        holder.mCheckBox.setChecked(mDatas.get(position).isCheck());

        //为TextView添加监听回调
        holder.nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemSubViewClick(holder.nameTv, position);
                }
            }
        });
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

        public MyViewHolder(final View view) {
            super(view);
            mCheckBox = (CheckBox) view.findViewById(R.id.cb_box);
            nameTv = (TextView) view.findViewById(R.id.name_tv);
            ageTv = (TextView) view.findViewById(R.id.age_tv);
            cityTv = (TextView) view.findViewById(R.id.city_tv);
            idcodeTv = (TextView) view.findViewById(R.id.idcode_tv);


            //为item添加普通点击回调
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(view, getPosition());
                    }
                }
            });
            //为item添加长按回调
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemLongClick(view, getPosition());
                    }
                    return true;
                }
            });
        }
    }


    /**
     * 得到随机的Item的高度
     */
    private void getRandomHeight(List<TestBean> list) {
        height = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            height.add((int) (200 + Math.random() * 400));
        }
    }
}

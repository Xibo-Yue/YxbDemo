package com.leanway.recycleview_test;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/4/19 11:38
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Integer> height;
    private ItemClickListener itemClickListener;
    private LoadMoreListener mLoadMoreListener;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public SimpleRecyclerAdapter(List<TestBean> datas, Context context) {
        mDatas = datas;
        mContext = context;
        inflater=LayoutInflater.from(mContext);
        //getRandomHeight(this.mDatas);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.mLoadMoreListener = loadMoreListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_test,parent,false);
            MyViewHolder holder= new MyViewHolder(view);
            return holder;
        }
        else if (viewType == TYPE_FOOTER) {
            View view1 = inflater.inflate(R.layout.footerview,parent,false);
            view1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view1);
        }

        return null;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).nameTv.setText(mDatas.get(position).getName());
            ((MyViewHolder) holder).nameTv.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            ((MyViewHolder) holder).nameTv.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
            ((MyViewHolder) holder).ageTv.setText(mDatas.get(position).getAge());
            ((MyViewHolder) holder).cityTv.setText(mDatas.get(position).getCity());
            ((MyViewHolder) holder).idcodeTv.setText(mDatas.get(position).getIdCode());
            ((MyViewHolder) holder).mCheckBox.setChecked(mDatas.get(position).isCheck());

            //为TextView添加监听回调
            ((MyViewHolder) holder).nameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemSubViewClick(((MyViewHolder) holder).nameTv, position);
                    }
                }
            });
        }else if(holder instanceof FooterViewHolder){
            mLoadMoreListener.loadMore();
        }
        /**
         * 得到item的LayoutParams布局参数
         */
//        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//        params.height = height.get(position);//把随机的高度赋予item布局
//        holder.itemView.setLayoutParams(params);//把params设置item布局

    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    class MyViewHolder extends ViewHolder {

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

            //姓名点击事件
            nameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemSubViewClick((TextView) v,getPosition());
                    }
                }
            });

            //CheckBox点击事件
//            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (itemClickListener != null) {
//                        itemClickListener.onItemCheckBoxClick((CheckBox) buttonView,getPosition(),isChecked);
//                    }
//                }
//            });

            mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemCheckBoxClick(getPosition(),((CheckBox) v).isChecked());
                    }
                }
            });



            //为item添加普通点击回调
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getPosition());
                        //itemClickListener.onItemCheckBoxClick(getPosition(),!mDatas.get(getPosition()).isCheck());
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

    class FooterViewHolder extends ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
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

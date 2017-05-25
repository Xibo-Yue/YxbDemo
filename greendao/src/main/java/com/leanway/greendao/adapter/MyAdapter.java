package com.leanway.greendao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leanway.greendao.R;
import com.leanway.greendao.bean.User;

import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/5/22 17:15
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<User> mList;

    public MyAdapter(Context context, List<User> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = (LinearLayout) LayoutInflater.from(mContext).inflate(
                    R.layout.item, null);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.age = (TextView) convertView.findViewById(R.id.age);
            viewHolder.sex = (TextView) convertView.findViewById(R.id.sex);
            viewHolder.city = (TextView) convertView.findViewById(R.id.city);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.name.setText(mList.get(position).getName());
        viewHolder.sex.setText((mList.get(position).getIsBoy()? "男" :"女"));
        viewHolder.age.setText(mList.get(position).getAge()+"");
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView age;
        TextView sex;
        TextView city;
    }







}

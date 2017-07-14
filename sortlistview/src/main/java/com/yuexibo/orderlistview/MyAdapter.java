package com.yuexibo.orderlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/5/22 17:15
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<TestDate> mList;

    public MyAdapter(Context context, List<TestDate> list) {
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
            viewHolder.city = (TextView) convertView.findViewById(R.id.city);
            viewHolder.drawNum = (TextView) convertView.findViewById(R.id.draw_num);
            viewHolder.data = (TextView) convertView.findViewById(R.id.data);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.name.setText(mList.get(position).getName());
        viewHolder.city.setText(mList.get(position).getCity());
        viewHolder.drawNum.setText(mList.get(position).getDrawNum());
        viewHolder.data.setText(mList.get(position).getDate());


        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView city;
        TextView drawNum;
        TextView data;
    }


}

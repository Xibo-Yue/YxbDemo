package com.yuexibo.orderlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<TestDate> mList = null;
    private MyAdapter mAdapter;


    private TextView ordername;
    private TextView ordercity;
    private TextView orderdrawnum;
    private TextView orderdata;
    private ListView contentList;


    private boolean name_bool = true;
    private boolean city_bool = true;
    private boolean data_bool = true;
    private boolean drawnum_bool = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentList = (ListView) findViewById(R.id.content_list);
        ordername = (TextView) findViewById(R.id.ordername);
        ordercity = (TextView) findViewById(R.id.ordercity);
        orderdrawnum = (TextView) findViewById(R.id.orderdrawnum);
        orderdata = (TextView) findViewById(R.id.orderdata);
        contentList = (ListView) findViewById(R.id.content_list);

        orderdata.setOnClickListener(this);
        ordername.setOnClickListener(this);
        orderdrawnum.setOnClickListener(this);
        ordercity.setOnClickListener(this);

        mList = new ArrayList<TestDate>();
        initData();
        mAdapter = new MyAdapter(this, mList);
        contentList.setAdapter(mAdapter);
    }

    private void initData() {
        mList.add(new TestDate("2012-12-12 12:30", "zhangsan", "123", "北京DG001"));
        mList.add(new TestDate("2012-12-12 10:20", "lisi", "456", "上海"));
        mList.add(new TestDate("2012-12-11 10:21", "lisi", "456", "广州"));
        mList.add(new TestDate("2012-12-11 10:20", "lisi", "456", "AS深圳"));
        mList.add(new TestDate("2012-12-13 01:03", "wangwu", "23", "AD深圳"));
        mList.add(new TestDate("2012-12-10 02:04", "zhaoliu", "235", "北京DG002"));
        mList.add(new TestDate("2012-12-15 23:00", "tianqi", "d45324", "长沙"));
        mList.add(new TestDate("2012-11-12 22:30", "wangwu", "d45345", "合肥"));
        mList.add(new TestDate("2012-12-17 08:24", "shimei", "5635", "北京DG003"));
        mList.add(new TestDate("2012-11-10 11:10", "shisanmei", "75354", "南京"));
        mList.add(new TestDate("2012-12-18 16:50", "wangan", "ss7898.", "北京DH003"));
        mList.add(new TestDate("2012-12-19 18:00", "wangjiu", "sd7896", "B郑州"));
        mList.add(new TestDate("2012-12-20 19:30", "wusi", "ss7896", "B太原"));
        mList.add(new TestDate("2012-12-20 19:30", "wusi", "ss7897", "青岛"));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderdata:
                data_bool = (data_bool ? false : true);

                Collections.sort(mList, new Comparator<TestDate>() {

                    @Override
                    public int compare(TestDate lhs, TestDate rhs) {
                        Date date1 = stringToDate(lhs.getDate());
                        Date date2 = stringToDate(rhs.getDate());
                        if (date1.before(date2)) {
                            return (data_bool ? 1 : -1);
                        }
                        return (data_bool ? -1 : 1);
                    }
                });

                mAdapter.notifyDataSetChanged();
                break;
            case R.id.ordername:

                name_bool = (name_bool ? false : true);

                Collections.sort(mList, new Comparator<TestDate>() {

                    @Override
                    public int compare(TestDate lhs, TestDate rhs) {

                        if (name_bool) {
                            return lhs.getName().compareTo(rhs.getName());
                        } else {
                            return rhs.getName().compareTo(lhs.getName());
                        }
                    }
                });

                mAdapter.notifyDataSetChanged();
                break;
            case R.id.orderdrawnum:

                drawnum_bool = (drawnum_bool ? false : true);

                Collections.sort(mList, new Comparator<TestDate>() {

                    @Override
                    public int compare(TestDate lhs, TestDate rhs) {

                        if (drawnum_bool) {
                            return lhs.getDrawNum().compareTo(rhs.getDrawNum());
                        } else {
                            return rhs.getDrawNum().compareTo(lhs.getDrawNum());
                        }


                    }
                });

                mAdapter.notifyDataSetChanged();
                break;

            case R.id.ordercity:
                city_bool = (city_bool ? false : true);
                Collections.sort(mList, new Comparator<TestDate>() {

                    @Override
                    public int compare(TestDate lhs, TestDate rhs) {
                        if (city_bool) {
                            return lhs.getCity().compareTo(rhs.getCity());
                        } else {
                            return rhs.getCity().compareTo(lhs.getCity());
                        }
                    }
                });

                mAdapter.notifyDataSetChanged();
                break;

        }
    }


    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }
}

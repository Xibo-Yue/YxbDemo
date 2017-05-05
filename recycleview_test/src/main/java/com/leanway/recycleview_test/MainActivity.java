package com.leanway.recycleview_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private ArrayList<TestBean> mTestBeens;

    private TextView name_tv_top;

    private CheckBox cb_box_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_box_top = (CheckBox) findViewById(R.id.cb_box_top);
        name_tv_top = (TextView) findViewById(R.id.name_tv_top);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initData();

        //RecycleView分隔线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this,
                LinearLayoutManager.HORIZONTAL));

        //RecycleView Adapter
        final SimpleRecyclerAdapter recyclerAdapter = new SimpleRecyclerAdapter(mTestBeens,
                MainActivity.this);

        //RecycleView布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        //mRecyclerView.setAdapter(new TestAdapter(MainActivity.this, mTestBeens));

        //当不需要动态计算item高度时，需要加上这句话
        mRecyclerView.setHasFixedSize(true);

        //全选checkbox按钮
        cb_box_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TestBean bean : mTestBeens) {
                    bean.setCheck(cb_box_top.isChecked());
                }
                recyclerAdapter.notifyDataSetChanged();
            }
        });


        name_tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TestBean bean : mTestBeens) {

                }
            }
        });


        recyclerAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.show(getApplication(), position + "");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.show(getApplication(), position + "");
            }

            @Override
            public void onItemSubViewClick(TextView view, int position) {
                ToastUtil.show(getApplication(), view.getText().toString());
            }

            @Override
            public void onItemCheckBoxClick(int position, boolean isCheck) {
                mTestBeens.get(position).setCheck(isCheck);
                if (isCheck) {
                    for (TestBean bean : mTestBeens) {
                        if (bean.isCheck() == !isCheck) {
                            return;
                        }
                    }
                    cb_box_top.setChecked(isCheck);
                } else {
                    cb_box_top.setChecked(isCheck);
                }
            }
        });
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    private void initData() {
        mTestBeens = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mTestBeens.add(new TestBean("李四" + i, 19 + i + "", "北京" + i, "123" + i, false));
        }
    }


}

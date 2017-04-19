package com.leanway.recycleview_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private ArrayList<TestBean> mTestBeens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initData();
        mRecyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this,LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //mRecyclerView.setAdapter(new TestAdapter(MainActivity.this, mTestBeens));
        mRecyclerView.setAdapter(new SimpleRecyclerAdapter(mTestBeens,MainActivity.this));
    }

    private void initData() {
        mTestBeens = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mTestBeens.add(new TestBean("李四"+i,19+i+"","北京"+i,"123"+i,false));
        }
    }
}

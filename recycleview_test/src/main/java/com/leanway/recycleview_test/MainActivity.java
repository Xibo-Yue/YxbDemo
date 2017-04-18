package com.leanway.recycleview_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.leanway.recycleview_test.adapter.TestAdapter;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getApplicationContext(),LinearLayoutManager.VERTICAL,5));
        mRecyclerView.setAdapter(new TestAdapter(MainActivity.this, mTestBeens));
    }

    private void initData() {
        mTestBeens = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mTestBeens.add(new TestBean("张三"+i,19+i+"","上海"+i,"123"+i));
        }
    }
}
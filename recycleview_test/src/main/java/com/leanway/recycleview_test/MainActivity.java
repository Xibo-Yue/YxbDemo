package com.leanway.recycleview_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

        SimpleRecyclerAdapter recyclerAdapter = new SimpleRecyclerAdapter(mTestBeens,MainActivity.this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        //mRecyclerView.setAdapter(new TestAdapter(MainActivity.this, mTestBeens));
        mRecyclerView.setHasFixedSize(true);//当不需要动态计算item高度时，需要加上这句话


        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i("page", currentPage+"");
                simulateLoadMoreData();
            }
        });






        recyclerAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemSubViewClick(View view, int position) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(recyclerAdapter);

    }


    /**
     * 加载更多
     */
    private void simulateLoadMoreData() {
        Toast.makeText(getApplicationContext(),"加载更多",Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        mTestBeens = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mTestBeens.add(new TestBean("李四"+i,19+i+"","北京"+i,"123"+i,false));
        }
    }


}

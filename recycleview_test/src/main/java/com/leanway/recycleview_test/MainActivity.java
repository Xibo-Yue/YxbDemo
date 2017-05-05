package com.leanway.recycleview_test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;

    private ArrayList<TestBean> mTestBeens;

    private TextView name_tv_top;

    private CheckBox cb_box_top;

    private SwipeRefreshLayout swipe_refresh;

    private int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_box_top = (CheckBox) findViewById(R.id.cb_box_top);
        name_tv_top = (TextView) findViewById(R.id.name_tv_top);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorAccent));
        swipe_refresh.setOnRefreshListener(this);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
//        swipe_refresh.setProgressViewOffset(false, 200, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources()
//                        .getDisplayMetrics()));
        initData();

        //RecycleView分隔线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this,
                LinearLayoutManager.HORIZONTAL));

        //RecycleView Adapter
        final SimpleRecyclerAdapter recyclerAdapter = new SimpleRecyclerAdapter(mTestBeens,
                MainActivity.this);
        //RecycleView布局管理器
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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


//        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView,
//                                             int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItem + 1 == recyclerAdapter.getItemCount()) {
//                    swipe_refresh.setRefreshing(true);
//                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    mHandler.sendEmptyMessageDelayed(0, 3000);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//            }
//
//        });

        recyclerAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                ToastUtil.show(getApplicationContext(),"加载更多");
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
        for (int i = 0; i < 10; i++) {
            mTestBeens.add(new TestBean("李四" + i, 19 + i + "", "北京" + i, "123" + i, false));
        }
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    swipe_refresh.setRefreshing(false);
                    break;
            }
        }
    } ;
}

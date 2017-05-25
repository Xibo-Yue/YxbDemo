package com.leanway.greendao.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.leanway.greendao.R;
import com.leanway.greendao.application.GreendaoApp;
import com.leanway.greendao.bean.City;
import com.leanway.greendao.bean.Head;
import com.leanway.greendao.bean.User;
import com.leanway.greendao.db.DBHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private Button insertOne;
    private Button insertMore;
    private Button deleteData;
    private Button updateData;
    private Button searchAll;
    private Button searchPart;
    private ListView userlist;


    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        insertOne = (Button) findViewById(R.id.insert_one);
        insertOne.setOnClickListener(this);
        insertMore = (Button) findViewById(R.id.insert_more);
        insertMore.setOnClickListener(this);
        deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(this);
        updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(this);
        searchAll = (Button) findViewById(R.id.search_all);
        searchAll.setOnClickListener(this);
        searchPart = (Button) findViewById(R.id.search_part);
        searchPart.setOnClickListener(this);


        userlist = (ListView) findViewById(R.id.userlist);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.lg);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSearchAutoComplete.isShown()) {
                    try {
                        mSearchAutoComplete.setText("");
                        Method method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(GreendaoApp.getInstance(), "返回", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //creatDir();
        initData();

    }

    /**
     * 创建文件夹
     */
//    private void creatDir() {
//        String status = Environment.getExternalStorageState();
//        if (status.equals(Environment.MEDIA_MOUNTED)) {
//            Toast.makeText(GreendaoApp.getInstance(),"有SD卡",Toast.LENGTH_SHORT).show();
//            File file = new File(Environment.getExternalStorageDirectory().getPath()+"/aaa");
//            Log.i("sd卡",file.getAbsolutePath());
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//        } else {
//            Toast.makeText(GreendaoApp.getInstance(),"没有SD卡",Toast.LENGTH_SHORT).show();
//        }
//    }

    private void initData() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(null,"北京",1));
        cities.add(new City(null,"上海",1));
        cities.add(new City(null,"广州",1));
        DBHelper.getDaoSession().getCityDao().insertOrReplaceInTx(cities);

        ArrayList<Head> heads = new ArrayList<>();
        heads.add(new Head(null,"头像1"));
        heads.add(new Head(null,"头像2"));
        heads.add(new Head(null,"头像3"));
        heads.add(new Head(null,"头像4"));
        heads.add(new Head(null,"头像5"));
        DBHelper.getDaoSession().getHeadDao().insertOrReplaceInTx(heads);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        //通过MenuItem得到SearchView
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id
                .search_src_text);
        mSearchView.setQueryHint("搜索");

        //设置输入框提示文字样式
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_light));
        mSearchAutoComplete.setTextSize(14);
        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        mSearchAutoComplete.setThreshold(1);

        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);

        //修改搜索框控件间的间隔（这样只是为了更加接近网易云音乐的搜索框）
        LinearLayout search_edit_frame = (LinearLayout) mSearchView.findViewById(R.id
                .search_edit_frame);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) search_edit_frame
                .getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        search_edit_frame.setLayoutParams(params);

        //监听SearchView的内容
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                //                Cursor cursor = TextUtils.isEmpty(s) ? null : queryData(s);
                //
                //                //                if (mSearchView.getSuggestionsAdapter() ==
                // null) {
                //                //                    mSearchView.setSuggestionsAdapter(new
                // SimpleCursorAdapter(SearchViewActivity2.this, R.layout.item_layout, cursor,
                // new String[]{"name"}, new int[]{R.id.text1}));
                //                //                } else {
                //                //                    mSearchView.getSuggestionsAdapter()
                // .changeCursor(cursor);
                //                //                }
                //                setAdapter(cursor);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",
                            Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert_one:
                User user = new User(null,"张三",12,false,1l,1l);
                DBHelper.getDaoSession().getUserDao().insertOrReplace(user);
                break;

            case R.id.insert_more:

                break;

            case R.id.delete_data:

                break;

            case R.id.update_data:

                break;


            case R.id.search_part:
                List<User> users = DBHelper.getDaoSession().getUserDao().queryBuilder().build().list();

                Log.i("user城市",users.get(0).getMCity().getName());
                Log.i("user头像",users.get(0).getMHead().getHeadUrl());
                break;

        }
    }
}

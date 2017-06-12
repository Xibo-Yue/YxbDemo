package com.yuexibo.treeview.tree_view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.yuexibo.treeview.R;
import com.yuexibo.treeview.bean.Bean;
import com.yuexibo.treeview.bean.FileBean;
import com.yuexibo.treeview.tree.bean.Node;
import com.yuexibo.treeview.tree.bean.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
	private List<Bean> mDatas = new ArrayList<Bean>();
	private List<FileBean> mDatas2 = new ArrayList<FileBean>();
	private ListView mTree;
	private TreeListViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initDatas();
		mTree = (ListView) findViewById(R.id.id_tree);
		try
		{
			mAdapter = new SimpleTreeAdapter<Bean>(mTree, this, mDatas, 10);
			mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener()
			{
				@Override
				public void onClick(Node node, int position)
				{
					if (node.isLeaf())
					{
						Toast.makeText(getApplicationContext(), node.getName(),
								Toast.LENGTH_SHORT).show();
					}
				}

			});

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		mTree.setAdapter(mAdapter);

	}

	private void initDatas()
	{
		mDatas.add(new Bean(1, 0, "根目录1"));
		mDatas.add(new Bean(2, 0, "根目录2"));
		mDatas.add(new Bean(3, 0, "根目录3"));
		mDatas.add(new Bean(4, 0, "根目录4"));
		mDatas.add(new Bean(5, 1, "子目录1-1"));
		mDatas.add(new Bean(6, 1, "子目录1-2"));

		mDatas.add(new Bean(7, 5, "子目录1-1-1"));
		mDatas.add(new Bean(8, 2, "子目录2-1"));

		mDatas.add(new Bean(9, 4, "子目录4-1"));
		mDatas.add(new Bean(10, 4, "子目录4-2"));

		mDatas.add(new Bean(11, 10, "子目录4-2-1"));
		mDatas.add(new Bean(12, 10, "子目录4-2-3"));
		mDatas.add(new Bean(13, 10, "子目录4-2-2"));
		mDatas.add(new Bean(14, 9, "子目录4-1-1"));
		mDatas.add(new Bean(15, 9, "子目录4-1-2"));
		mDatas.add(new Bean(16, 9, "子目录4-1-3"));

		mDatas.add(new Bean(19, 16, "子目录4-1-3-1"));
		mDatas.add(new Bean(20, 19, "子目录4-1-3-1-1"));
	}

}

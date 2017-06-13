package com.yuexibo.treeview_check;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class TtDemoActivity extends Activity {


	int btSelectedId=1;
	/**
	 * 选中的节点 的button
	 */
	Button btSelected;
	private TreeListView listView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);//布局实际上没有用到

		btSelected=new Button(TtDemoActivity.this);
		//btSelected.setId(btSelectedId);
		btSelected.setText("显示选中数据");
		btSelected.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				StringBuilder sb=new StringBuilder();
				List<Node>checks =new ArrayList<Node>()	;
				checks=listView.get();
				for (Node node : checks) {
					sb.append("id:"+node.getCurId()+";"+node.getValue()+"\n");
				}
				Toast.makeText(TtDemoActivity.this, "选中的个数："+checks.size()+"\n数据为：\n"+sb.toString(), Toast.LENGTH_SHORT).show();
			}
		});


		listView = new TreeListView(this, initNodeTree());

		RelativeLayout rl = new RelativeLayout(this);
		RelativeLayout.LayoutParams relLayoutParams=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);


		RelativeLayout.LayoutParams rlChild1=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		rlChild1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		btSelected.setLayoutParams(rlChild1);
		rl.addView(btSelected,rlChild1);




		RelativeLayout.LayoutParams rlChild2=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		rlChild2.addRule(RelativeLayout.BELOW,btSelectedId);
		rlChild2.addRule(RelativeLayout.ALIGN_LEFT, btSelectedId);
		listView.setLayoutParams(rlChild2);
		rl.addView(listView,rlChild2);



		setContentView(rl);
	}

	public List<NodeResource> initNodeTree() {
		List<NodeResource> list = new ArrayList<NodeResource>();
		NodeResource n1 = new NodeResource("" + -1, "" + 0, "根节点,自己是0", "dfs",
				R.drawable.outline_list_collapse);
		list.add(n1);
		NodeResource n2 = new NodeResource("" + -1, "" + 4, "根节点，自己是4", "dfs",
				R.drawable.outline_list_collapse);
		list.add(n2);
		NodeResource n3 = new NodeResource("" + 0, "" + 7, "父id是0，自己是7", "dfs",
				R.drawable.outline_list_collapse);
		list.add(n3);
		NodeResource n4 = new NodeResource("" + 7, "" + 10, "父id是7，自己是10",
				"dfs", R.drawable.outline_list_collapse);
		list.add(n4);
		NodeResource n5 = new NodeResource("" + 7, "" + 14, "父id是7，自己是14",
				"dfs", R.drawable.outline_list_expand);
		list.add(n5);
		NodeResource n6 = new NodeResource("" + 10, "" + 18, "父id是10，自己是18",
				"dfs", R.drawable.outline_list_expand);
		list.add(n6);
		NodeResource n7 = new NodeResource("" + 4, "" + 22, "父id是4，自己是22",
				"dfs", R.drawable.outline_list_expand);
		list.add(n7);
		return list;
	}

}

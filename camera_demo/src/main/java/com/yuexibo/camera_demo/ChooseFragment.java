package com.yuexibo.camera_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.yuexibo.camera_demo.adapter.UploadImageAdapter;

import java.util.LinkedList;


/**
 * Fragment中上传图片<br>
 */
public class ChooseFragment extends Fragment {
	
	/**
	 * 需要上传的图片路径  控制默认图片在最后面需要用LinkedList
	 */
	private LinkedList<String> dataList = new LinkedList<String>();
	
	/**
	 * 图片上传GridView
	 */
	private GridView uploadGridView;
	
	/**
	 * 图片上传Adapter
	 */
	private UploadImageAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choose, container, false);
		uploadGridView = (GridView) view.findViewById(R.id.grid_upload_pictures);
		dataList.addLast(null);// 初始化第一个添加按钮数据
		adapter = new UploadImageAdapter(getActivity(), dataList);
		uploadGridView.setAdapter(adapter);
		uploadGridView.setOnItemClickListener(mItemClick);
		uploadGridView.setOnItemLongClickListener(mItemLongClick);
		//设置监听
		((BaseActivity)getActivity()).setOnFragmentResult(mOnFragmentResult);
		return view;
	}
	
	/**
	 * 上传图片GridView Item单击监听
	 */
	private OnItemClickListener mItemClick = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if(parent.getItemAtPosition(position) == null){ // 添加图片
				//((BaseActivity)getActivity()).showPictureDailog();//Dialog形式
				((BaseActivity)getActivity()).showPicturePopupWindow();//PopupWindow形式
			}
		}
	};
	
	/**
	 * 上传图片GridView Item长按监听
	 */
	private OnItemLongClickListener mItemLongClick = new OnItemLongClickListener(){

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			if(parent.getItemAtPosition(position) != null){ // 长按删除
				dataList.remove(parent.getItemAtPosition(position));
				adapter.update(dataList); // 刷新图片
			}
			return true;
		}
	};

	private BaseActivity.OnFragmentResult mOnFragmentResult = new BaseActivity.OnFragmentResult() {

		@Override
		public void onResult(String mImagePath) {
			dataList.addFirst(mImagePath);
			adapter.update(dataList); // 刷新图片
		}
	};
}

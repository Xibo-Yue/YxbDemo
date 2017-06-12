package com.yuexibo.treeview.bean;


import com.yuexibo.treeview.tree.bean.TreeNodeId;
import com.yuexibo.treeview.tree.bean.TreeNodeLabel;
import com.yuexibo.treeview.tree.bean.TreeNodePid;

public class FileBean
{
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	private long length;
	private String desc;

	public FileBean(int _id, int parentId, String name)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
	}

}

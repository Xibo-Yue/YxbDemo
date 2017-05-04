package com.leanway.okhttp.okhttp.listener;

/**
 * @author 岳希波
 * @function 监听下载进度
 */
public interface DisposeDownloadListener extends DisposeDataListener {
	public void onProgress(int progrss);
}

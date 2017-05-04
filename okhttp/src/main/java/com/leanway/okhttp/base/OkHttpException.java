package com.leanway.okhttp.base;

/**********************************************************
 * @文件作者：岳希波
 * @创建时间：2015年8月19日 上午10:05:08
 * @文件描述：自定义异常类,返回ecode,emsg到业务层
 * @修改历史：2015年8月19日创建初始版本
 **********************************************************/
public class OkHttpException extends Exception {

	public static final int NETWORK_ERROR = -1; // 网络错误
	public static final int JSON_ERROR = -2; // JSON解析错误
	public static final int OTHER_ERROR = -3; // 其它未知错误
	public static final int LOGINOUT_ERROR = -4; // 登录超时




	private static final long serialVersionUID = 1L;

	/**
	 * the server return code
	 */
	private int ecode;

	/**
	 * the server return error message
	 */
	private Object emsg;

	public OkHttpException(int ecode, Object emsg) {
		this.ecode = ecode;
		this.emsg = emsg;
	}

	public int getEcode() {
		return ecode;
	}

	public Object getEmsg() {
		return emsg;
	}
}
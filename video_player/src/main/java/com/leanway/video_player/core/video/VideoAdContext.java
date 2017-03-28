package com.leanway.video_player.core.video;

import android.content.Intent;
import android.view.ViewGroup;

import com.leanway.video_player.core.AdContextInterface;
import com.leanway.video_player.module.AdValue;
import com.leanway.video_player.okhttp.HttpConstant;
import com.leanway.video_player.report.ReportManager;
import com.leanway.video_player.utils.Utils;
import com.leanway.video_player.widget.CustomVideoView;
import com.leanway.video_player.widget.adbrowser.AdBrowserActivity;


/**
 * @author: qndroid
 * @function: 管理slot, 与外界进行通信
 * @date: 16/6/1
 */
public class VideoAdContext implements VideoAdSlot.AdSDKSlotListener {

    //the ad container
    private ViewGroup mParentView;

    private VideoAdSlot mAdSlot;
    private AdValue mInstance = new AdValue("http://fairee.vicp.net:83/2016rm/0116/baishi160116.mp4");
    //the listener to the app layer
    private AdContextInterface mListener;
    private CustomVideoView.ADFrameImageLoadListener mFrameLoadListener;

    public VideoAdContext(ViewGroup parentView, String instance,
                          CustomVideoView.ADFrameImageLoadListener frameLoadListener) {
        this.mParentView = parentView;
//        this.mInstance = (AdValue) ResponseEntityToModule.
//                parseJsonToModule(instance, AdValue.class);
        this.mFrameLoadListener = frameLoadListener;
        load();
    }

    /**
     * init the ad,不调用则不会创建videoview
     */
    public void load() {
        if (mInstance != null && mInstance.resource != null) {
            mAdSlot = new VideoAdSlot(mInstance, this, mFrameLoadListener);
            //发送解析成功事件
            sendAnalizeReport(HttpConstant.Params.ad_analize, HttpConstant.AD_DATA_SUCCESS);
        } else {
            mAdSlot = new VideoAdSlot(null, this, mFrameLoadListener); //创建空的slot,不响应任何事件
            if (mListener != null) {
                mListener.onAdFailed();
            }
            sendAnalizeReport(HttpConstant.Params.ad_analize, HttpConstant.AD_DATA_FAILED);
        }
    }

    /**
     * release the ad
     */
    public void destroy() {
        mAdSlot.destroy();
    }

    public void setAdResultListener(AdContextInterface listener) {
        this.mListener = listener;
    }

    /**
     * 根据滑动距离来判断是否可以自动播放, 出现超过50%自动播放，离开超过50%,自动暂停
     */
    public void updateAdInScrollView() {
        if (mAdSlot != null) {
            mAdSlot.updateAdInScrollView();
        }
    }

    @Override
    public ViewGroup getAdParent() {
        return mParentView;
    }

    @Override
    public void onAdVideoLoadSuccess() {
        if (mListener != null) {
            mListener.onAdSuccess();
        }
        sendAnalizeReport(HttpConstant.Params.ad_load, HttpConstant.AD_PLAY_SUCCESS);
    }

    @Override
    public void onAdVideoLoadFailed() {
        if (mListener != null) {
            mListener.onAdFailed();
        }
        sendAnalizeReport(HttpConstant.Params.ad_load, HttpConstant.AD_PLAY_FAILED);
    }

    @Override
    public void onAdVideoLoadComplete() {
    }

    @Override
    public void onClickVideo(String url) {
        if (mListener != null) {
            mListener.onClickVideo(url);
        } else {
            Intent intent = new Intent(mParentView.getContext(), AdBrowserActivity.class);
            intent.putExtra(AdBrowserActivity.KEY_URL, url);
            mParentView.getContext().startActivity(intent);
        }
    }

    /**
     * 发送广告数据解析成功监测
     */
    private void sendAnalizeReport(HttpConstant.Params step, String result) {
        try {
            ReportManager.sendAdMonitor(Utils.isPad(mParentView.getContext().
                            getApplicationContext()), mInstance == null ? "" : mInstance.resourceID,
                    (mInstance == null ? null : mInstance.adid), Utils.getAppVersion(mParentView.getContext()
                            .getApplicationContext()), step, result);
        } catch (Exception e) {

        }
    }
}
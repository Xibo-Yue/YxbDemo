package com.leanway.baselibrary.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.leanway.baselibrary.utils.DialogHelp;
import com.leanway.baselibrary.utils.TDevice;
import com.umeng.analytics.MobclickAgent;


/**
 * baseActionBar Activity
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseViewInterface {


    protected LayoutInflater mInflater;
    private boolean _isVisible;
    private ProgressDialog _waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        _isVisible = true;
        init(savedInstanceState);
        initView();
        initData();
    }



    protected void init(Bundle savedInstanceState) {
    }

    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (this.isFinishing()){
            TDevice.hideSoftKeyboard(getCurrentFocus());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }



    public ProgressDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelp.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }


    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

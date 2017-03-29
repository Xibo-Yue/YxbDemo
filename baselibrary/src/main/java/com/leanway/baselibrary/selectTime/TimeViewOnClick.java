package com.leanway.baselibrary.selectTime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.leanway.baselibrary.R;

import java.util.Calendar;

/**
 * @author Xibo_Yue
 * @time 2016/11/17 15:16
 */
public class TimeViewOnClick implements View.OnClickListener {

    private TextView timeView;
    private Activity mContext;

    public TimeViewOnClick(TextView timeView, Activity context) {
        this.timeView = timeView;
        this.mContext = context;
    }

    @Override
    public void onClick(final View v) {


        {
            LayoutInflater timeInflater = LayoutInflater.from(mContext);
            View timepickerview = timeInflater.inflate(R.layout.timepicker, null);
            ScreenInfo screenInfo = new ScreenInfo(mContext);
            final WheelMain wheelMain = new WheelMain(timepickerview, 0);
            wheelMain.screenheight = screenInfo.getHeight();
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            wheelMain.initDateTimePicker(year, month, day, hour, min);
            new AlertDialog.Builder(mContext).setTitle("选择时间").setView(timepickerview)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            timeView.setText(wheelMain.getTime() + ":00");
                        }
                    }).setNegativeButton("取消", null).show();
        }

    }
}

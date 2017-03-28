package com.leanway.timepickdia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnDateSetListener,View.OnClickListener {



    TimePickerDialog mDialogAll;
    TimePickerDialog mDialogYearMonth;
    TimePickerDialog mDialogYearMonthDay;
    TimePickerDialog mDialogMonthDayHourMinute;
    TimePickerDialog mDialogHourMinute;
    TextView mTvTime;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("TimePicker")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(24)
                .build();

        //        mDialogAll = new TimePickerDialog.Builder()
        //                .setMinMillseconds(System.currentTimeMillis())
        //                .setThemeColor(R.color.colorPrimary)
        //                .setWheelItemTextSize(16)
        //                .setCallBack(this)
        //                .build();
        mDialogYearMonth = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setCallBack(this)
                .build();
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
        mDialogMonthDayHourMinute = new TimePickerDialog.Builder()
                .setType(Type.MONTH_DAY_HOUR_MIN)
                .setCallBack(this)
                .build();
        mDialogHourMinute = new TimePickerDialog.Builder()
                .setType(Type.HOURS_MINS)
                .setCallBack(this)
                .build();
    }

    void initView() {
        findViewById(R.id.btn_all).setOnClickListener(this);
        findViewById(R.id.btn_year_month_day).setOnClickListener(this);
        findViewById(R.id.btn_year_month).setOnClickListener(this);
        findViewById(R.id.btn_month_day_hour_minute).setOnClickListener(this);
        findViewById(R.id.btn_hour_minute).setOnClickListener(this);

        mTvTime = (TextView) findViewById(R.id.tv_time);

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        String text = getDateToString(millseconds);
        mTvTime.setText(text);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_all:
                mDialogAll.show(getSupportFragmentManager(), "all");
                break;
            case R.id.btn_year_month:
                mDialogYearMonth.show(getSupportFragmentManager(), "year_month");
                break;
            case R.id.btn_year_month_day:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
                break;
            case R.id.btn_month_day_hour_minute:
                mDialogMonthDayHourMinute.show(getSupportFragmentManager(), "month_day_hour_minute");
                break;
            case R.id.btn_hour_minute:
                mDialogHourMinute.show(getSupportFragmentManager(), "hour_minute");
                break;
        }

    }
}

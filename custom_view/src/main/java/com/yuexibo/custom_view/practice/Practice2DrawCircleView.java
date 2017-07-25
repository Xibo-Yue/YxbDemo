package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {
    Paint circlePaint;

    public Practice2DrawCircleView(Context context) {
        this(context, null);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(160, 160, 50, circlePaint);  //X圆心坐标,Y圆心坐标,半径,画笔
        circlePaint.setStyle(Paint.Style.STROKE); //设置不填充
        canvas.drawCircle(290, 160, 50, circlePaint);
        circlePaint.setStyle(Paint.Style.FILL);   //设置填充
        circlePaint.setColor(Color.BLUE);
        canvas.drawCircle(160, 290, 50, circlePaint);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(20f);
        circlePaint.setColor(Color.BLACK);
        canvas.drawCircle(290, 290, 50, circlePaint);
//        canvas.drawCircle(width / 2, height / 4, 200, circlePaint);
//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
    }
}

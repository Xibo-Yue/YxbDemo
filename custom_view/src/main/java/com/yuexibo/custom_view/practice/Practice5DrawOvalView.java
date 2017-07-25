package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice5DrawOvalView extends View {
    Paint circlePaint;

    public Practice5DrawOvalView(Context context) {
        this(context, null);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(20, 20, 300, 100); //已矩形为基础
        canvas.drawOval(rectF, circlePaint);

        circlePaint.setStyle(Paint.Style.STROKE);
        RectF rectF1 = new RectF(20, 200, 300, 500);
        canvas.drawOval(rectF1, circlePaint);

        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setStrokeWidth(30f);
        RectF rectF2 = new RectF(350, 20, 500, 100);
        canvas.drawOval(rectF2, circlePaint);
//        练习内容：使用 canvas.drawOval() 方法画椭圆
    }
}

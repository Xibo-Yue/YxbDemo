package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice6DrawLineView extends View {
    Paint circlePaint;

    public Practice6DrawLineView(Context context) {
        this(context, null);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circlePaint.setStrokeWidth(10f);
        canvas.drawLine(100, 100, 200, 200, circlePaint); //起始点X,Y轴坐标,终点X,Y轴坐标
        circlePaint.setStrokeWidth(20f);
        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120}; //四个一组
        canvas.drawLines(points, circlePaint);

//        练习内容：使用 canvas.drawLine() 方法画直线
    }
}

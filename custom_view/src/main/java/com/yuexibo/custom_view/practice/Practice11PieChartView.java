package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {
    Paint blackPaint;
    Paint paint;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(26f);
        blackPaint.setStrokeWidth(10f);
        blackPaint.setStyle(Paint.Style.STROKE);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        paint.setStyle(Paint.Style.FILL);
    }

    float x1;
    float y1;
    int currentAngle;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int widthCenter = canvas.getWidth() / 2;
        int heightCenter = canvas.getHeight() / 2;
        int radius = heightCenter / 2;
        RectF rectFRed = new RectF(widthCenter - radius - 10,
                heightCenter - radius - 10, widthCenter + radius - 10, heightCenter + radius - 10);

        RectF rectF = new RectF(widthCenter - radius,
                heightCenter - radius, widthCenter + radius, heightCenter + radius);
        //红色
        paint.setColor(Color.RED);
        canvas.drawArc(rectFRed, 180, 120, true, paint);
        //蓝色
        paint.setColor(Color.parseColor("#1E80F0"));
        canvas.drawArc(rectF, 75, 105, true, paint);
        //绿色
        paint.setColor(Color.parseColor("#118575"));
        canvas.drawArc(rectF, 20, 50, true, paint);
        //灰色
        paint.setColor(Color.parseColor("#8c8c8c"));
        canvas.drawArc(rectF, 10, 8, true, paint);
        //紫色
        paint.setColor(Color.parseColor("#830A9B"));
        canvas.drawArc(rectF, 0, 8, true, paint);
        //背景色
        paint.setColor(Color.parseColor("#506E7A"));
        canvas.drawArc(rectF, 355, 5, true, paint);
        //黄色
        paint.setColor(Color.parseColor("#FDB60D"));
        canvas.drawArc(rectF, 20, 20, true, paint);

        //建立在已X轴为基础的sin cos
        int startAngle = 20;
        int angle = 20;
        float arcCenterDegree = angle / 2;
//        //由于Math.sin(double a)中参数a不是度数而是弧度，所以需要将度数转化为弧度
//        //而Math.toRadians(degree)的作用就是将度数转化为弧度
        if (startAngle + angle >= 0 && startAngle + angle <= 90) {
            currentAngle = Math.abs(startAngle + angle / 2);
        } else if (startAngle + angle > 90 && startAngle + angle <= 180) {
            currentAngle = Math.abs(180 - startAngle - angle / 2);
        } else if (startAngle + angle > 180 && startAngle + angle <= 270) {
            currentAngle = Math.abs(180 - startAngle - angle / 2);
        } else {
            currentAngle = 180 - Math.abs((180 - startAngle - angle / 2));
        }

        if (startAngle + arcCenterDegree > 180 && startAngle + arcCenterDegree <= 270) {
            x1 = (float) (widthCenter - radius * Math.cos(Math.toRadians(currentAngle)));
            y1 = (float) (heightCenter - radius * Math.sin(Math.toRadians(currentAngle)));
        } else if (startAngle + arcCenterDegree >= 0 && startAngle + arcCenterDegree <= 90) {
            x1 = (float) (widthCenter + radius * Math.cos(Math.toRadians(currentAngle)));
            y1 = (float) (heightCenter + radius * Math.sin(Math.toRadians(currentAngle)));
        } else if (startAngle + arcCenterDegree > 90 && startAngle + arcCenterDegree <= 180) {
            x1 = (float) (widthCenter - radius * Math.cos(Math.toRadians(currentAngle)));
            y1 = (float) (heightCenter + radius * Math.sin(Math.toRadians(currentAngle)));
        } else {
            x1 = (float) (widthCenter + radius * Math.cos(Math.toRadians(currentAngle)));
            y1 = (float) (heightCenter - radius * Math.sin(Math.toRadians(currentAngle)));
        }
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x1 + 50, y1);
        canvas.drawPath(path, blackPaint);
//        canvas.drawText("asas", x1, y1, paint);
//        canvas.drawPath(path, paint);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
    }
}
